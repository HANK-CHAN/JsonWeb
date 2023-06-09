package com.tw.ch.lcc.hankchan.homeworkjson

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.PointF.length
import android.opengl.Matrix.length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import okhttp3.*
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject

//lateinit var moviebtn : Button
//lateinit var movietext : TextView
lateinit var movielist : ListView
class MainActivity : AppCompatActivity() {

    val url = "https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=8"
    val client = OkHttpClient()

    var item = ArrayList<movieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movielist = findViewById(R.id.movielist)

        movielist.setOnItemClickListener {
            parent,view,position,id->

            val bundle = Bundle()
            bundle.putString("title",item[position].movietitle)
            bundle.putString("price",item[position].price)
            bundle.putString("contenthtml",item[position].context)

            val it = Intent(this,movieContent::class.java)

            it.putExtras(bundle)
            startActivity(it)
        }


        getmovie()

    }

    fun getmovie(){
        val request = Request.Builder().url(url).build()

        // 連線
        client.newCall(request).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
                Log.e("LCC",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val content = response.body?.string()
                if (content != null) {
                    Log.d("Lcc",content)
                }
                var movie = content.toString()

                val jsonArray : JSONArray = JSONArray(movie)
                var json : JSONObject = JSONObject()
                var msg = ""
                var price =""
                for (i in 0 until jsonArray.length()) {
                    json = jsonArray.getJSONObject(i)
                    var title = json.getString("title")
                    var contenthtml = json.getString("descriptionFilterHtml")
                    var jsontext = json.getString("showInfo")

                    var info = jsontext.toString()
                    val jsonArray2 : JSONArray = JSONArray(info)
                    for (j in 0 until jsonArray2.length()){
                        json = jsonArray2.getJSONObject(j)
                        var time = json.getString("time")
                        var location = json.getString("location")
                        if(price != "")
                            price = json.getString("price")
                        else{
                            price = "無"
                        }
                        msg += "${title}\n${location}\n${price}\n${time}\n\n"
                        item.add(movieItem(title,location,time,price,contenthtml))
                    }

                }
                Log.d("LCC",msg)
                runOnUiThread{
//                    movietext.text = msg
                    movielist.adapter = movieAdapter(R.layout.movelayout,item)
                }
            }


        })
    }

}

data class movieItem(
    val movietitle : String,
    val movielocation : String,
    val movietime : String,
    val price : String,
    val context: String
)

