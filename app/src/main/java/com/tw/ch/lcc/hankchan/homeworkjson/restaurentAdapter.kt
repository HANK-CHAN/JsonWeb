package com.tw.ch.lcc.hankchan.homeworkjson

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso

class restaurentAdapter(private val layout:Int, private val data: ArrayList<foodItem>) : BaseAdapter(){
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val view = View.inflate(parent?.context,layout,null)

        // 從latout 中抓ID
        var name = view.findViewById<TextView>(R.id.title)
        var address = view.findViewById<TextView>(R.id.area)
        var time = view.findViewById<TextView>(R.id.time)
        var img = view.findViewById<ImageView>(R.id.movieImg)

        name.text = "餐廳名稱 : " + data[position].resname
        address.text ="地址  : " + data[position].resadd
        time.text = "營業時間 : " + data[position].restime

        // 使用 Picasso  的 Library (記得 build.gradle 要加入此模組)
        Picasso.get().load(data[position].resimg).into(img)

        name.setTextColor(android.graphics.Color.WHITE)
        name.setBackgroundColor(android.graphics.Color.BLACK)
        time.setTextColor(android.graphics.Color.RED)




        return view
    }
}