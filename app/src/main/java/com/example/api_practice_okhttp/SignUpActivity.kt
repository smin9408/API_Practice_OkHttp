package com.example.api_practice_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.api_practice_okhttp.databinding.ActivitySignUpBinding
import com.example.api_practice_okhttp.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnEmailCheck.setOnClickListener {

//            입력 이메일 값 추출
            val inputEamil = binding.edtEmail.text.toString()

//            서버의 죽복확인 기능 (/User_check - GET) API 활용 => ServerUtil에 함수 추가, 가져다 활용
//            그 응답 code값에 따라 다른 문구 배치.

            ServerUtil.GetRequestDuplicatedCheck("EMAIL", inputEamil, null)

        }

        binding.btnSignUP.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNickname,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

//                        회원가입 성공 / 실패 분기
                        val code = jsonObj.getInt("code")

                        if(code == 200){

//                            가입한 사람의 닉네임 추출 > ~~님, 가입을 축하합니다! 토스트
//                            회원가입화면 종료 > 로그인 화면 복귀
                            val dataObj = jsonObj.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")

                            val nickname = userObj.getString("nick_name")

                            runOnUiThread{
                                Toast.makeText(mContext,"${nickname}님, 가입을 축하합니다다",Toast.LENGTH_SHORT).show()
                            }
//                            화면 종료 : 객체 소멸 (UI 동작이 아님)
                            finish()
                       }
                        else{
                            val message = jsonObj.getString("message")

                            runOnUiThread{
                                Toast.makeText(mContext,"실패 사유 : ${message}", Toast.LENGTH_SHORT).show()
                            }

                        }

                    }

                }
            )

        }
    }

    override fun setValues() {
    }


}