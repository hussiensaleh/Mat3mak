package com.saleh.mat3mak

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity() {
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Main2Activity().finish()
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)

    }

    fun Offers(view: View) {
        var i = Intent(this, OffersActivity::class.java)
        startActivity(i)
    }

    fun Order(view: View) {
        var i = Intent(this, OrderActivity::class.java)
        startActivity(i)
    }

    fun Prices(view: View) {
        var i = Intent(this, PricesActivity::class.java)
        startActivity(i)
    }

    fun Gallary(view: View) {
        var i = Intent(this, GallaryActivity::class.java)
        startActivity(i)
    }

    fun monasbatBu(view: View) {
        var i = Intent(this, MonasbatActivity::class.java)
        startActivity(i)
    }

    fun myOrdersBu(view: View) {
        var i = Intent(this, MyOrdersActivity::class.java)
        startActivity(i)
    }


}
