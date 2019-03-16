package com.anurag.onlinestoreecommerceapp

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")

        var brandURL ="http://192.168.88.1/OnlineStoreEcommerceApp/fetch_brand.php"

        var brandList = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this@Home)

        var jsonAR= JsonArrayRequest(Request.Method.GET,brandURL,
            null,Response.Listener { response ->//to get all the objects as array

                for(jsonObject in 0.until(response.length())) {
                    brandList.add(response.getJSONObject(jsonObject).getString("brand"))
                }
                var brandListAdaptation = ArrayAdapter(this,R.layout.brands_list_textview,brandList)
                brandslistview.adapter=brandListAdaptation

            },Response.ErrorListener { error ->

                val dialogBuilder= AlertDialog.Builder(this)
                dialogBuilder.setTitle("Error")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
          })
        requestQ.add(jsonAR)

        brandslistview.setOnItemClickListener { adapterView, view, i, l ->

            val tappedview= brandList.get(i)
            var intent = Intent(this, FetchEproductsActivity::class.java)
            intent.putExtra("BRAND",tappedview)
            startActivity(intent)
        }


   }
}
