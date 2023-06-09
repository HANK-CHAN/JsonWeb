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

 lateinit var relist:ListView

class Restaurent : AppCompatActivity() {

    val url = "https://media.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json"
    val client = OkHttpClient()

    var reitem = ArrayList<foodItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurent)

        relist = findViewById(R.id.relist)

        relist.setOnItemClickListener {
                parent,view,position,id->

            val bundle = Bundle()
            bundle.putString("name",reitem[position].resname)
            bundle.putString("add",reitem[position].resadd)
            bundle.putString("tel",reitem[position].restel)
            bundle.putString("time",reitem[position].restime)
            bundle.putString("descr",reitem[position].resdescr)
            bundle.putString("img",reitem[position].resimg)

            val it = Intent(this,restContent::class.java)

            it.putExtras(bundle)
            startActivity(it)
        }


        getRestaurent()
    }

    fun getRestaurent(){
        val request = Request.Builder().url(url).build()

        // 連線
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("LCC",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val content = response.body?.string()
                if (content != null) {
                    Log.d("LCC",content)
                }
                var restaurent = content.toString()
                var rest : JSONObject = JSONObject(restaurent)
                var rest1 = rest.toString()
                var json : JSONObject = JSONObject(rest1)
                    .getJSONObject("XML_Head")
                    .getJSONObject("Infos")

                var info = json["Info"].toString()

                val jsonArray : JSONArray = JSONArray(info)
                var contxt : JSONObject = JSONObject()
                var msg = ""
                for(i in 0 until jsonArray.length()){
                    contxt = jsonArray.getJSONObject(i)

                    var name = contxt.getString("Name")
                    var add  = contxt.getString("Add")
                    var tel = contxt.getString("Tel")
                    var time = contxt.getString("Opentime")
                    var descr = contxt.getString("Description")
                    var img = contxt.getString("Picture1")

//                    msg += "${name}\n${add}\n${tel}\n${time}\n${descr}\n${img}\n\n"
                    msg += "${img}\n"
                    reitem.add(foodItem(name,add,tel,time,descr,img))
                }

                Log.d("MM",msg)
                runOnUiThread{

                    relist.adapter = restaurentAdapter(R.layout.movelayout,reitem)
                }
            }


        })
    }
}

 data class foodItem(
     val resname : String,
     val resadd : String,
     val restel : String,
     val restime : String,
     val resdescr : String,
     val resimg : String,
 )
