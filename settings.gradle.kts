rootProject.name = "preview-screenshot"

// Minimal standalone: plugin + validation-api + stubs.
// screenshot-validation-junit-engine is not included (depends on compose-preview-detector/renderer from full repo).
include(
    ":screenshot:screenshot-validation-api",
    ":screenshot:screenshot-test-gradle-plugin",
    ":screenshot:standalone-stubs",
)
