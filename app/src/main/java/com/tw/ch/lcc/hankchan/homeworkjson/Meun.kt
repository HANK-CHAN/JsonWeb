package com.tw.ch.lcc.hankchan.homeworkjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btn1 : Button
lateinit var btn2 : Button
lateinit var btn3 : Button

class Meun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meun)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)


        btn1.setOnClickListener {
            var it = Intent(this,MainActivity::class.java)
            startActivity(it)
        }
        btn2.setOnClickListener {
            var it = Intent(this,historic_site::class.java)
            startActivity(it)
        }

        btn3.setOnClickListener {
            var it = Intent(this,Restaurent::class.java)
            startActivity(it)
        }


    }
}