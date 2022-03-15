package com.example.api_practice_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.api_practice_okhttp.databinding.ActivityEditReplyBinding

class EditReplyActivity : BaseActivity() {

    lateinit var binding: ActivityEditReplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_reply)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
    }
}