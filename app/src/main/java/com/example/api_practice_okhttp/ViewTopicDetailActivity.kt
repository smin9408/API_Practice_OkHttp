package com.example.api_practice_okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.api_practice_okhttp.databinding.ActivityViewTopicDetailBinding
import com.example.api_practice_okhttp.datas.TopicData
import com.example.api_practice_okhttp.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding: ActivityViewTopicDetailBinding

    //    보여주게 될 토론 주제 데이터 > 이벤트처리, 데이터 표현 등 여러 함수에서 사용
    lateinit var mTopicData: TopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_topic_detail)
        mTopicData = intent.getSerializableExtra("topic") as TopicData
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        btnVote1 클릭 => 첫 진영의 id값을 찾아서, 거기에 투표
//        서버에 전달 => API활용.
        binding.btnVote1.setOnClickListener {

//            서버의 투표 API 호출
            ServerUtil.postRequestVote(mContext, mTopicData.sideList[0].id, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    토스트로, 서버가 알려준 현재 상황 (신규 투표 or 재투표 or 취소 등)
                    val message = jsonObj.getString("message")

                    runOnUiThread {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

//                    변경된 득표 현황을 다시 불러오자.
                    getTopicDetailFromServer()
                }

            })



//           투표 현황 새로고침 (응답)

        }

    }

    override fun setValues() {

        setTopicDataToUi()

        getTopicDetailFromServer()
    }

    fun setTopicDataToUi(){
//        토론 주제에 대한 데이터들을, UI에 반영하는 함수.
//

        binding.txtTitle.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopicBackground)

//        1번진영 제목, 2반진영 제목
        binding.txtSide01.text = mTopicData.sideList[0].title
        binding.txtSide02.text = mTopicData.sideList[1].title

//        1번진영 특표 수, 2번진영 특표 수
        binding.txtVoteCount1.text = "${mTopicData.sideList[0].voteCount}표"
        binding.txtVoteCount2.text = "${mTopicData.sideList[1].voteCount}표"

    }

    fun getTopicDetailFromServer() {

        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                토론 정보 JSONObjec (topicObj) => TopicData() 형태로 변환
                val topicData = TopicData.getTopicDataFromJson(topicObj)

//                변환된 객체를, mTopicData로 다시 대입. => UI 반영도 다시 실행.
                mTopicData = topicData

                runOnUiThread {
                    setTopicDataToUi()
                }



            }
        })
    }
}