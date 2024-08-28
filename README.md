This project minimal reproducible example project for issue https://github.com/arkivanov/Decompose/issues/765

1. Open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.
2. Navigate from first to second screen.
3. Navigate back to first screen
4. Check print logs in browser console.

```
MainViewModel init
DetailsViewModel init
DetailsViewModel onCleared
DetailsViewModel init // issue
```