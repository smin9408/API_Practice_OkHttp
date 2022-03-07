package com.example.api_practice_okhttp.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.api_practice_okhttp.datas.TopicData

class TopicAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<TopicData>
) : ArrayAdapter<TopicData>(mContext, resId, mList) {
}