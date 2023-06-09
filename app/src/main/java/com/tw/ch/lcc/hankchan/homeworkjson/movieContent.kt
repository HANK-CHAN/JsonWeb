package com.tw.ch.lcc.hankchan.homeworkjson

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

lateinit var title2 : TextView
lateinit var money : TextView
lateinit var info : TextView

class movieContent : AppCompatActivity() {

    private var title : String = ""
    private var price : String = ""
    private var context : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_content)

        title2 = findViewById(R.id.title2)
        money = findViewById(R.id.money)
        info = findViewById(R.id.info)
        // 得到 上一頁傳來的值
        intent?.extras?.let {
            title = it.getString("title","")
            price = it.getString("price","")
            context = it.getString("contenthtml","")

        }
        // 將上一頁傳來的文字放入TextView 裡面顯示
        title2.setText("電影標題 : "+title)
        money.setText("入場費用 : "+price)
        info.setText("詳細描述 :\n"+context)

        // 設定View的 背景 或是 字體 顏色
        title2.setTextColor(android.graphics.Color.BLUE)
        money.setBackgroundColor(android.graphics.Color.RED)
        info.setBackgroundColor(android.graphics.Color.GREEN)

        // MovementMethod = ScrollingMovementMethod() => 設定View可以滑動，顯示完整資料
        info.movementMethod = ScrollingMovementMethod()

    }
}