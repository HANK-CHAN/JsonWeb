package com.tw.ch.lcc.hankchan.homeworkjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

lateinit var rname : TextView
lateinit var radd : TextView
lateinit var rtel: TextView
lateinit var rtime : TextView
lateinit var rtxt : TextView
lateinit var resimg : ImageView

class restContent : AppCompatActivity() {

    private var name2 :String = ""
    private var address : String= ""
    private var phone : String = ""
    private var date2 : String = ""
    private var txt : String = ""
    private var img : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_content)

        rname = findViewById(R.id.rname)
        radd = findViewById(R.id.radd)
        rtel = findViewById(R.id.rtel)
        rtime = findViewById(R.id.rtime)
        rtxt = findViewById(R.id.rtxt)
        resimg = findViewById(R.id.resimg)
        // 得到 上一頁傳來的值
        intent?.extras?.let {
            name2 = it.getString("name","")
            address = it.getString("add","")
            phone = it.getString("tel","")
            date2 = it.getString("time","")
            txt = it.getString("descr","")
            img = it.getString("img","")

        }
        // 將上一頁傳來的文字放入TextView 裡面顯示
        rname.setText("餐廳名稱 : "+ name2)
        radd .setText("地址 : " + address)
        rtel.setText("電話 : " + phone)
        rtime.setText("營業時間 : " + date2 )
        rtxt.setText("介紹 : " + txt)

        rtxt.setBackgroundColor(android.graphics.Color.WHITE)
        rtxt.setTextColor(android.graphics.Color.BLACK)

        resimg.setBackgroundColor(android.graphics.Color.BLACK)

        // 使用 Picasso  的 Library (記得 build.gradle 要加入此模組)
        // 此模組專門將 圖片網址(String) 放入ImagesView 顯示
        Picasso.get().load(img).into(resimg)
        // MovementMethod = ScrollingMovementMethod() => 設定View可以滑動，顯示完整資料
        rtxt.movementMethod = ScrollingMovementMethod()
    }
}