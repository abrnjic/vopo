package com.vopo.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.firestore.FirebaseFirestore
import com.vopo.domain.model.LicenseStatus
import com.vopo.domain.repository.LicenseRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import com.vopo.domain.model.RemoteProviderConfig

private val Context.licenseDataStore: DataStore<Preferences> by preferencesDataStore(name = "license_prefs")

@Singleton
class LicenseRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firestore: FirebaseFirestore
) : LicenseRepository {

    private val DEVICE_ID_KEY = stringPreferencesKey("device_id")

    override fun getDeviceId(): Flow<String> {
        return context.licenseDataStore.data.map { preferences ->
            preferences[DEVICE_ID_KEY] ?: ""
        }
    }

    override fun getLicenseStatus(): Flow<LicenseStatus> = callbackFlow {
        val deviceId = getDeviceId().first()
        if (deviceId.isEmpty()) {
            trySend(LicenseStatus.Unregistered)
            close()
            return@callbackFlow
        }
        
        // Timeout check (if offline and cache empty)
        val initialJob = launch {
            kotlinx.coroutines.delay(3000)
            trySend(LicenseStatus.Unregistered)
        }

        val listener = firestore.collection("licenses").document(deviceId)
            .addSnapshotListener { snapshot, error ->
                initialJob.cancel()
                if (error != null || snapshot == null || !snapshot.exists()) {
                    trySend(LicenseStatus.Unregistered)
                    return@addSnapshotListener
                }

                val statusStr = snapshot.getString("status")?.lowercase() ?: "trial"
                val createdAtObj = snapshot.get("createdAt")
                val createdAt = when (createdAtObj) {
                    is com.google.firebase.Timestamp -> createdAtObj.toDate().time
                    is Number -> createdAtObj.toLong()
                    else -> System.currentTimeMillis()
                }
                val isLifetime = snapshot.getBoolean("isLifetime") ?: false
                
                val xtreamConfigMap = snapshot.get("xtreamConfig") as? Map<String, Any>
                val config = if (xtreamConfigMap != null) {
                    val url = xtreamConfigMap["url"] as? String
                    val user = xtreamConfigMap["username"] as? String
                    val pass = xtreamConfigMap["password"] as? String
                    if (url != null && user != null && pass != null) {
                        RemoteProviderConfig(url, user, pass)
                    } else null
                } else null

                if (isLifetime) {
                    trySend(LicenseStatus.Active(config))
                    return@addSnapshotListener
                }

                when (statusStr) {
                    "active", "lifetime" -> trySend(LicenseStatus.Active(config))
                    "trial" -> {
                        val daysElapsed = (System.currentTimeMillis() - createdAt) / (1000 * 60 * 60 * 24)
                        val daysRemaining = 3 - daysElapsed.toInt()
                        if (daysRemaining > 0) trySend(LicenseStatus.Trial(daysRemaining, config))
                        else trySend(LicenseStatus.Expired)
                    }
                    "unregistered" -> trySend(LicenseStatus.Unregistered)
                    else -> trySend(LicenseStatus.Expired)
                }
            }
            
        awaitClose { listener.remove() }
    }

    override suspend fun checkLicenseOnce(): LicenseStatus {
        val deviceId = getDeviceId().first()
        if (deviceId.isEmpty()) return LicenseStatus.Unregistered
        
        try {
            val docSnapshot = firestore.collection("licenses").document(deviceId).get().await()
            if (!docSnapshot.exists()) return LicenseStatus.Unregistered
            
            val statusStr = docSnapshot.getString("status")?.lowercase() ?: "trial"
            val createdAtObj = docSnapshot.get("createdAt")
            val createdAt = when (createdAtObj) {
                is com.google.firebase.Timestamp -> createdAtObj.toDate().time
                is Number -> createdAtObj.toLong()
                else -> System.currentTimeMillis()
            }
            val isLifetime = docSnapshot.getBoolean("isLifetime") ?: false
            
            val xtreamConfigMap = docSnapshot.get("xtreamConfig") as? Map<String, Any>
            val config = if (xtreamConfigMap != null) {
                val url = xtreamConfigMap["url"] as? String
                val user = xtreamConfigMap["username"] as? String
                val pass = xtreamConfigMap["password"] as? String
                if (url != null && user != null && pass != null) RemoteProviderConfig(url, user, pass) else null
            } else null

            if (isLifetime) return LicenseStatus.Active(config)

            return when (statusStr) {
                "active", "lifetime" -> LicenseStatus.Active(config)
                "trial" -> {
                    val daysElapsed = (System.currentTimeMillis() - createdAt) / (1000 * 60 * 60 * 24)
                    val daysRemaining = 3 - daysElapsed.toInt()
                    if (daysRemaining > 0) LicenseStatus.Trial(daysRemaining, config)
                    else LicenseStatus.Expired
                }
                "unregistered" -> LicenseStatus.Unregistered
                else -> LicenseStatus.Expired
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return LicenseStatus.Unregistered
        }
    }

    override suspend fun generateAndRegisterDeviceIdIfNeeded(): String {
        val currentId = getDeviceId().first()
        if (currentId.isNotEmpty()) return currentId

        // Generate 9 digit alphanumeric code: XXX-XXX-XXX
        val randomString = UUID.randomUUID().toString().replace("-", "").take(9).uppercase()
        val newDeviceId = "${randomString.substring(0, 3)}-${randomString.substring(3, 6)}-${randomString.substring(6, 9)}"
        
        context.licenseDataStore.edit { prefs ->
            prefs[DEVICE_ID_KEY] = newDeviceId
        }
        
        try {
            val docData = hashMapOf(
                "status" to "unregistered",
                "createdAt" to System.currentTimeMillis()
            )
            // Ne koristimo await() jer ako je uređaj offline, await() će blokirati izvršavanje zauvijek.
            // Samo ćemo pokušati zapisati (fire and forget).
            firestore.collection("licenses").document(newDeviceId).set(docData)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return newDeviceId
    }
}
