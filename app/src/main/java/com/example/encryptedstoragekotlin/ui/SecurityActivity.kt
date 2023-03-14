package com.example.encryptedstoragekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.encryptedstoragekotlin.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("On create called")
    }
}
