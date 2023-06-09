package com.tw.ch.lcc.hankchan.homeworkjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

//lateinit var htbtn : Button
//lateinit var httext : TextView

lateinit var htlist : ListView

class historic_site : AppCompatActivity() {

    val url = "https://cloud.culture.tw/frontsite/trans/emapOpenDataAction.do?method=exportEmapJson&typeId=A&classifyId=1.1"
    val client = OkHttpClient()
    var item = ArrayList<historicitem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic_site)

        htlist = findViewById(R.id.htlist)

        htlist.setOnItemClickListener {
                parent, view,position,id ->

            val bundle = Bundle()
            bundle.putString("title",item[position].httitle)
            bundle.putDouble("lat", item[position].lat.toDouble())
            bundle.putDouble("lng",item[position].lng.toDouble())

            val it = Intent(this,HistoricMaps::class.java)

            it.putExtras(bundle)
            startActivity(it)

        }


        gethistoric()
    }


    fun gethistoric() {

        val request = Request.Builder().url(url).build()

        // 連線
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LCC", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val content = response.body?.string()
                if (content != null) {
                    Log.d("Lcc", content)
                }
                var historic = content.toString()

                val jsonArray: JSONArray = JSONArray(historic)
                var json: JSONObject = JSONObject()
                var msg = ""
                var lat = ""
                var lng = ""
                for (i in 0 until jsonArray.length()) {
                    json = jsonArray.getJSONObject(i)
                    var title = json.getString("name")
                    var location = json.getString("address")
                    var time = json.getString("registerDateValue")
                    var contenthtml = json.getString("srcWebsite")

                    if(lat.isEmpty())
                        lat = json.getString("latitude")

                    if(lng.isEmpty())
                        lng = json.getString("longitude")


//                    msg += "${title}\n${location}\n${time}\n${contenthtml}\n\n"
                    msg += "${lat}\n${lng}\n\n"
                    item.add(historicitem(title, location, time, contenthtml, lat, lng))
                }

                Log.d("LCC", msg)
                runOnUiThread {
                    htlist.adapter = historicAdapter(R.layout.historiclayout,item)
                }
            }

        })
    }
}
data class historicitem(
    val httitle : String,
    val htlocation : String,
    val httime : String,
    val hthtml : String,
    val lat : String,
    val lng : String,
)
