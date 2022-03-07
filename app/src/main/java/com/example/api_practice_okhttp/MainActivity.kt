package com.example.api_practice_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.api_practice_okhttp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
    }
}