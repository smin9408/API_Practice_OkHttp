package com.example.api_practice_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.api_practice_okhttp.databinding.ActivitySignUpBinding
import com.example.api_practice_okhttp.utils.ServerUtil

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

         binding.btnSignUP.setOnClickListener {

             val inputEmail = binding.edtEmail.text.toString()
             val inputPw = binding.edtPassword.text.toString()
             val inputNickname = binding.edtNickname.text.toString()

             ServerUtil.

         }
    }

    override fun setValues() {
    }



}