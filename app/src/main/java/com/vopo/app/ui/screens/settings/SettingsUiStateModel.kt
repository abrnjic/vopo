package com.vopo.app.ui.screens.settings

import com.vopo.app.ui.model.LiveTvChannelMode
import com.vopo.app.ui.model.LiveTvQuickFilterVisibilityMode
import com.vopo.app.ui.model.VodViewMode
import com.vopo.domain.manager.BackupImportPlan
import com.vopo.domain.manager.BackupPreview
import com.vopo.domain.manager.DriveAuthState
import com.vopo.domain.manager.DriveSignInRequest
import com.vopo.domain.manager.DriveSyncStatus
import com.vopo.domain.manager.ProviderCredentials
import com.vopo.domain.model.ActiveLiveSource
import com.vopo.domain.model.AppHomeDashboardShelf
import com.vopo.domain.model.AppLandingDestination
import com.vopo.domain.model.AppTopLevelDestination
import com.vopo.domain.model.AppTimeFormat
import com.vopo.domain.model.AudioOutputPreference
import com.vopo.domain.model.Category
import com.vopo.domain.model.CategorySortMode
import com.vopo.domain.model.ChannelNumberingMode
import com.vopo.domain.model.CombinedM3uProfile
import com.vopo.domain.model.ContentType
import com.vopo.domain.model.DecoderMode
import com.vopo.domain.model.EpgResolutionSummary
import com.vopo.domain.model.GroupedChannelLabelMode
import com.vopo.domain.model.LiveChannelGroupingMode
import com.vopo.domain.model.LiveVariantPreferenceMode
import com.vopo.domain.model.PlaybackBufferMode
import com.vopo.domain.model.VodDuplicateHandlingMode
import com.vopo.domain.model.VodHttpProtocolMode
import com.vopo.domain.model.ExternalPlaybackMode
import com.vopo.domain.model.PlayerSurfaceMode
import com.vopo.domain.model.Provider
import com.vopo.domain.model.RecordingItem
import com.vopo.domain.model.RecordingStorageState
import com.vopo.domain.model.RemoteShortcutPreferences
import com.vopo.domain.model.VodVariantPreferenceMode

data class CrashReportUiModel(
    val timestamp: String = "",
    val exception: String = "",
    val fileName: String = "",
    val content: String = ""
) {
    val hasReport: Boolean
        get() = content.isNotBlank()
}

data class SettingsUiState(
    val providers: List<Provider> = emptyList(),
    val combinedProfiles: List<CombinedM3uProfile> = emptyList(),
    val availableM3uProviders: List<Provider> = emptyList(),
    val activeProviderId: Long? = null,
    val activeLiveSource: ActiveLiveSource? = null,
    val isSyncing: Boolean = false,
    val syncProgress: String? = null,
    val syncingProviderName: String? = null,
    val userMessage: String? = null,
    val syncWarningsByProvider: Map<Long, List<String>> = emptyMap(),
    val advancedModeEnabled: Boolean = false,
    val xtreamLiveOnboardingPhaseByProvider: Map<Long, String> = emptyMap(),
    val xtreamLiveOnboardingByProvider: Map<Long, XtreamLiveOnboardingUiModel> = emptyMap(),
    val xtreamIndexSectionStatusByProvider: Map<Long, Map<String, ProviderCatalogCountStatus>> = emptyMap(),
    val diagnosticsByProvider: Map<Long, ProviderDiagnosticsUiModel> = emptyMap(),
    val databaseMaintenance: DatabaseMaintenanceUiModel? = null,
    val parentalControlLevel: Int = 0,
    val hasParentalPin: Boolean = false,
    val appLanguage: String = "system",
    val appLandingDestination: AppLandingDestination = AppLandingDestination.HOME,
    val appTopLevelDestinations: List<AppTopLevelDestination> = AppTopLevelDestination.defaultOrder,
    val appHomeDashboardShelves: List<AppHomeDashboardShelf> = AppHomeDashboardShelf.defaultOrder,
    val appTimeFormat: AppTimeFormat = AppTimeFormat.SYSTEM,
    val preferredAudioLanguage: String = "auto",
    val playerMediaSessionEnabled: Boolean = true,
    val playerFastRetryOnTransientFailures: Boolean = false,
    val playerDecoderMode: DecoderMode = DecoderMode.AUTO,
    val playerPlaybackBufferMode: PlaybackBufferMode = PlaybackBufferMode.AUTO,
    val playerAudioOutputPreference: AudioOutputPreference = AudioOutputPreference.AUTO,
    val playerCompatibilityMemoryEnabled: Boolean = true,
    val playerSurfaceMode: PlayerSurfaceMode = PlayerSurfaceMode.AUTO,
    val playerVodHttpProtocolMode: VodHttpProtocolMode = VodHttpProtocolMode.COMPATIBILITY_HTTP1,
    val playerPlaybackSpeed: Float = 1f,
    val playerExternalPlaybackMode: ExternalPlaybackMode = ExternalPlaybackMode.INTERNAL_PLAYER,
    val playerAudioVideoSyncEnabled: Boolean = false,
    val playerAudioVideoOffsetMs: Int = 0,
    val centerTwoSlotMultiviewLayout: Boolean = false,
    val multiViewRespectProviderConnectionLimit: Boolean = true,
    val playerControlsTimeoutSeconds: Int = 5,
    val playerLiveOverlayTimeoutSeconds: Int = 4,
    val playerNoticeTimeoutSeconds: Int = 6,
    val playerDiagnosticsTimeoutSeconds: Int = 15,
    val subtitleTextScale: Float = 1f,
    val subtitleTextColor: Int = 0xFFFFFFFF.toInt(),
    val subtitleBackgroundColor: Int = 0x80000000.toInt(),
    val playerLiveTranslationEnabled: Boolean = false,
    val playerLiveTranslationEndpoint: String = "",
    val wifiMaxVideoHeight: Int? = null,
    val ethernetMaxVideoHeight: Int? = null,
    val playerTimeshiftEnabled: Boolean = false,
    val playerTimeshiftDepthMinutes: Int = 30,
    val defaultStopPlaybackTimerMinutes: Int = 0,
    val defaultIdleStandbyTimerMinutes: Int = 0,
    val lastSpeedTest: InternetSpeedTestUiModel? = null,
    val isRunningInternetSpeedTest: Boolean = false,
    val isDeletingProvider: Boolean = false,
    val isImportingBackup: Boolean = false,
    val backupPreview: BackupPreview? = null,
    val pendingBackupUri: String? = null,
    val backupImportPlan: BackupImportPlan = BackupImportPlan(),
    // --- Drive sync (M2) ---
    val driveAuthState: DriveAuthState = DriveAuthState.SignedOut,
    val driveSyncStatus: DriveSyncStatus = DriveSyncStatus(),
    val driveLastPushAt: Long? = null,
    val driveLastPullAt: Long? = null,
    val drivePendingSignIn: DriveSignInRequest? = null,
    val driveIsBusy: Boolean = false,
    // M3 — credentials downloaded by pullBackup, waiting to be applied
    // to providers once the import confirm completes.
    val pendingDriveCredentials: List<ProviderCredentials>? = null,
    val recordingItems: List<RecordingItem> = emptyList(),
    val recordingStorageState: RecordingStorageState = RecordingStorageState(),
    val wifiOnlyRecording: Boolean = false,
    val recordingPaddingBeforeMinutes: Int = 0,
    val recordingPaddingAfterMinutes: Int = 0,
    val isIncognitoMode: Boolean = false,
    val useXtreamTextClassification: Boolean = true,
    val xtreamBase64TextCompatibility: Boolean = false,
    val liveTvChannelMode: LiveTvChannelMode = LiveTvChannelMode.PRO,
    val showLiveSourceSwitcher: Boolean = false,
    val showAllChannelsCategory: Boolean = true,
    val showRecentChannelsCategory: Boolean = true,
    val remoteShortcutPreferences: RemoteShortcutPreferences = RemoteShortcutPreferences(),
    val liveTvCategoryFilters: List<String> = emptyList(),
    val liveTvQuickFilterVisibilityMode: LiveTvQuickFilterVisibilityMode = LiveTvQuickFilterVisibilityMode.ALWAYS_VISIBLE,
    val hideDecorativeLiveRows: Boolean = true,
    val liveChannelNumberingMode: ChannelNumberingMode = ChannelNumberingMode.GROUP,
    val liveChannelGroupingMode: LiveChannelGroupingMode = LiveChannelGroupingMode.RAW_VARIANTS,
    val groupedChannelLabelMode: GroupedChannelLabelMode = GroupedChannelLabelMode.HYBRID,
    val liveVariantPreferenceMode: LiveVariantPreferenceMode = LiveVariantPreferenceMode.BALANCED,
    val vodViewMode: VodViewMode = VodViewMode.MODERN,
    val vodInfiniteScroll: Boolean = true,
    val vodDuplicateHandlingMode: VodDuplicateHandlingMode = VodDuplicateHandlingMode.SHOW_ALL,
    val vodVariantPreferenceMode: VodVariantPreferenceMode = VodVariantPreferenceMode.BALANCED,
    val guideDefaultCategoryId: Long = com.vopo.domain.model.VirtualCategoryIds.FAVORITES,
    val guideDefaultCategoryOptions: List<Category> = emptyList(),
    val preventStandbyDuringPlayback: Boolean = true,
    val zapAutoRevert: Boolean = true,
    val autoPlayNextEpisode: Boolean = true,
    val categorySortModes: Map<ContentType, CategorySortMode> = emptyMap(),
    val hiddenCategories: List<Category> = emptyList(),
    val epgSources: List<com.vopo.domain.model.EpgSource> = emptyList(),
    val epgSourceAssignments: Map<Long, List<com.vopo.domain.model.ProviderEpgSourceAssignment>> = emptyMap(),
    val epgResolutionSummaries: Map<Long, EpgResolutionSummary> = emptyMap(),
    val refreshingEpgSourceIds: Set<Long> = emptySet(),
    val epgPendingDeleteSourceId: Long? = null,
    val epgTimeShiftMinutesByProvider: Map<Long, Int> = emptyMap(),
    val autoCheckAppUpdates: Boolean = true,
    val autoDownloadAppUpdates: Boolean = false,
    val isCheckingForUpdates: Boolean = false,
    val appUpdate: AppUpdateUiModel = AppUpdateUiModel(),
    val crashReport: CrashReportUiModel = CrashReportUiModel(),
    val viewedCrashReport: CrashReportUiModel? = null
)
