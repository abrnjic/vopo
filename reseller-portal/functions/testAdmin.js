const admin = require("firebase-admin");
admin.initializeApp({
  projectId: "vopoapp-86a75"
});
async function run() {
  try {
    const collections = await admin.firestore().listCollections();
    console.log("Success! Collections: ", collections.map(c => c.id));
  } catch (e) {
    console.error("Failed:", e.message);
  }
}
run();
