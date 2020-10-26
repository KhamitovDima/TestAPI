package com.h.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h.testapi.ui.list.ListFragment
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val mainFragment = ListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mainFragment)
                    .commit()
        }
    }
}