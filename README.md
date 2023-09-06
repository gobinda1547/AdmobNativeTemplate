# AdmobNativeTemplate

**AdmobNativeTemplate** library, which allows you to easily integrate and display AdMob native ads in your Android applications with customizable native ad templates.
This library is for native android developers and it's fully written in kotlin. Supported from android version 19.0 to 33.0+. 
The general purpose of this library is to easily display admob native ads within the app you are working on by maintaining your app's theme & look.

## Features

- Show small, medium, and large native ads effortlessly.
- Customize native ad template view by changing text typeface, color, size and more!
- Destroy native ad when you are done with showing your ads.

## Installation

1. Add the library to your project by including it in your app's `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.gobinda1547:AdmobNativeTemplate:1.0.0'
}
```

2. Also add this line of code in your project level `build.gradle` file

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

3. Sync your project and you are done!

## Usage

To show native ad you have to follow below steps-

1. **Prepare your app for showing admob ad ([Admob document](https://developers.google.com/admob/android/quick-start))**

2. **Add NativeTemplateView in your xml file (mention ad type otherwise default one will be shown). Default ad type is the small one.**

   - To show small size native ad, you can use below code in your xml layout.
     
     ```
     <com.gobinda.admob.nativetemplate.NativeTemplateView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:templateType="small" />
     ```
     
   - To show medium size native ad, you can use below code in your xml layout.
     
     ```
     <com.gobinda.admob.nativetemplate.NativeTemplateView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:templateType="medium" />
     ```
     
   - To show large native ad, you can use below code in your xml layout.
     
     ```
     <com.gobinda.admob.nativetemplate.NativeTemplateView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:templateType="large" />
     ```

3. **Try to load a native ad**

   ```kotlin
    fun loadNativeAd() {
        val adLoader = AdLoader.Builder(context, NATIVE_AD_ID)
            .forNativeAd { showNativeAdInTemplateView(it) }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }
    ```

4. **When the ad will be loaded then display it inside the NativeTemplateView**
   
   ```kotlin
   /**
    * It is possible that this function is being invoked when the activity or fragment
    * is destroyed already, In that case the binding object will be null. So we have to
    * handle this using try-catch block. Also it's a good practice to destroy previous
    * native ad while showing the new one to ignore memory leak.
    */
    private fun showNativeAdInTemplateView(mNativeAd: NativeAd) {
        try {
            binding.nativeAdTemplateView.destroyNativeAd()
            binding.nativeAdTemplateView.applyStyles(getStyleForNativeAd())
            binding.nativeAdTemplateView.setNativeAd(mNativeAd)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    ```

5. **Use your own style to customize the native ad (Here is an example)**

    ```kotlin
    @SuppressLint("ResourceType")
    fun getStyleForNativeAd(): NativeTemplateStyle {
        val mHolder = binding.nativeAdViewHolder
        val mSurface = MaterialColors.getColor(mHolder, R.attr.colorSurface)
        val mOnSurface = MaterialColors.getColor(mHolder, R.attr.colorOnSurface)
        val mPrimary = MaterialColors.getColor(mHolder, R.attr.colorPrimary)
        val mOnPrimary = MaterialColors.getColor(mHolder, R.attr.colorOnPrimary)
        return NativeTemplateStyle.Builder()
            .withMainBackgroundColor(ColorDrawable(mSurface))
            .withPrimaryTextColor(mPrimary)
            .withPrimaryTextBackgroundColor(ColorDrawable(mSurface))
            .withSecondaryTextColor(mOnSurface)
            .withCallToActionBackgroundColor(ColorDrawable(mPrimary))
            .withCallToActionTextColor(mOnPrimary)
            .build()
    }
    ```

6. **When you are done showing your ads, then destroy the native ad to avoid memory leak.**

    ```kotlin
    override fun onDestroyView() {
        binding.nativeAdTemplateView.destroyNativeAd()
        _binding = null
        super.onDestroyView()
    }
    ```

## License

This library is under [Apache License 2.0](https://github.com/gobinda1547/AdmobNativeTemplate/blob/master/LICENSE)
