package com.gobinda.admob.nativetemplate

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.material.card.MaterialCardView

class LargeAdActivity : AppCompatActivity() {

    private lateinit var adViewHolder: MaterialCardView
    private lateinit var templateView: NativeTemplateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_ad)

        adViewHolder = findViewById(R.id.ad_view_holder)
        templateView = findViewById(R.id.template_view)

        loadNativeAd()
    }

    override fun onDestroy() {
        // As per admob document - we should destroy native ad when we are done showing
        // our native ad, so that it could be properly garbage collected.
        templateView.destroyNativeAd()
        super.onDestroy()
    }

    /**
     * While loading the new add, we first of all making the ad contain or ad view holder
     * INVISIBLE cause before downloading the actual ad, it will be invalid.
     */
    private fun loadNativeAd() {
        adViewHolder.visibility = View.GONE
        val adLoader = AdLoader.Builder(this, MyConst.AD_ID)
            .forNativeAd { showNativeAdInTemplateView(it) }
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }

    /**
     * Here in this function we are setting the native ad to template view. The reason
     * why I have used try-catch block here is, this function could get invoked when the
     * activity is actually destroyed. In that case templateView & adViewHolder will be
     * null and we will get an exception. Also before setting the newly downloaded native
     * ad, we have to destroyed the previous native ad (If there is any). And finally
     * making the ad view holder visible cause it contains a valid ad now.
     */
    private fun showNativeAdInTemplateView(mNativeAd: NativeAd) {
        try {
            templateView.destroyNativeAd()
            templateView.applyStyles(getStyleForNativeAd())
            templateView.setNativeAd(mNativeAd)
            adViewHolder.visibility = View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Here we are creating a style object for native template view. I have taken all the
     * colors from the ad view holder (MaterialCardView), so that the ad could look like
     * similar with it's container or holder.
     */
    @SuppressLint("ResourceType")
    fun getStyleForNativeAd(): NativeTemplateStyle {
        val mSurface = MyColorScanner.scanSurfaceColor(adViewHolder)
        val mOnSurface = MyColorScanner.scanOnSurfaceColor(adViewHolder)
        val mPrimary = MyColorScanner.scanPrimaryColor(adViewHolder)
        val mOnPrimary = MyColorScanner.scanOnPrimaryColor(adViewHolder)
        return NativeTemplateStyle.Builder()
            .withMainBackgroundColor(ColorDrawable(mSurface))
            .withPrimaryTextColor(mPrimary)
            .withPrimaryTextBackgroundColor(ColorDrawable(mSurface))
            .withSecondaryTextColor(mOnSurface)
            .withCallToActionBackgroundColor(ColorDrawable(mPrimary))
            .withCallToActionTextColor(mOnPrimary)
            .build()
    }
}