# Jetpack Compose Swap View

![Demo GIF](https://github.com/YKuvonchbek/SwapBarComposeExample/blob/main/demo.gif)

A customizable Jetpack Compose animation swap view, similar to the Google Translate language swap bar. This view provides a smooth animated rotation and icon swap effect for any content, perfect for toggling between two states.

## Example

1. Implement `Displayable` interface and override `displayName`:
   ```kotlin
   enum class LanguageType(override var displayName: String): Displayable {
       ENGLISH("English"), UZBEK("Uzbek");
   }
2. Configure the view
   ```kotlin
   SwapBarComposeView(
    options = LanguageType.entries.toList(),
    leftOption = LanguageType.ENGLISH,
    rightOption = LanguageType.UZBEK
   ) {
       Log.e("SwapBarCompose", it.displayName)
   }
