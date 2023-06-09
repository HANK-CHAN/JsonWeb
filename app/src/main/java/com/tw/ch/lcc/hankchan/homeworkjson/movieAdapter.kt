package com.tw.ch.lcc.hankchan.homeworkjson

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class movieAdapter constructor(private val layout:Int ,private val data:ArrayList<movieItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        // 重新定義 引用的 layout
        val view = View.inflate(parent?.context,layout,null)
        // 從latout 中抓ID
        var title = view.findViewById<TextView>(R.id.title)
        var location = view.findViewById<TextView>(R.id.area)
        var time = view.findViewById<TextView>(R.id.time)

        title.text = "電影標題 : " + data[position].movietitle
        location.text = "放映地點 : " + data[position].movielocation
        time.text = "放映時間 : " + data[position].movietime

        return view
    }
}