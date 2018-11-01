package com.sandbox.vshcherbakov.coiny.ui.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sandbox.vshcherbakov.coiny.R
import org.jetbrains.anko.intentFor

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(intentFor<MainActivity>())
        finish()
    }
}
