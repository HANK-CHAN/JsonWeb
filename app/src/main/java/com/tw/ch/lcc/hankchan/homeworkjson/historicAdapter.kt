package com.tw.ch.lcc.hankchan.homeworkjson

import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class historicAdapter(private val layout:Int, private val data: ArrayList<historicitem>) : BaseAdapter() {
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
        var title = view.findViewById<TextView>(R.id.name)
        var location = view.findViewById<TextView>(R.id.address)
        var infohtml = view.findViewById<TextView>(R.id.infohtml)
        var time = view.findViewById<TextView>(R.id.date)


        title.text = "古蹟名稱 : " + data[position].httitle
        location.text = "縣市地址 : " + data[position].htlocation
        infohtml.text = "詳細網址 : " + data[position].hthtml
        time.text = "宣告日期 : " + data[position].httime

        title.setTextColor(android.graphics.Color.RED)
        location.setBackgroundColor(android.graphics.Color.YELLOW)

        time.setTextColor(android.graphics.Color.WHITE)
        time.setBackgroundColor(android.graphics.Color.BLACK)

        /**
        要確認該 view 的 layout 中是否有 android:autoLink="web"
        才使用.movementMethod = LinkMovementMethod(); => 設定該View擁有超連結
        !!!不然無任何反應!!!
         **/

        infohtml.movementMethod = LinkMovementMethod()


        return view
    }
}