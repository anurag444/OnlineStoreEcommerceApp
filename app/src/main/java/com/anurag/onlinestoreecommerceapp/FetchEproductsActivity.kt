package com.anurag.onlinestoreecommerceapp

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_eproducts.*

class FetchEproductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val selectedBrand = intent.getStringExtra("BRAND")
        brandname.text="Products of $selectedBrand"

        var productsList=ArrayList<Eproducts>()
        val productsURL = "http://192.168.88.1/OnlineStoreEcommerceApp/fetch_brand_products.php?brand=$selectedBrand"
        val requestQ = Volley.newRequestQueue(this)
        var jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null, Response.Listener { response ->

        for (productJOIndex in 0.until(response.length())){
            productsList.add(Eproducts(response.getJSONObject(productJOIndex).getInt("id"),
                response.getJSONObject(productJOIndex).getString("name"),
                response.getJSONObject(productJOIndex).getInt("price")))
        }
            val pAdapter = EproductsAdapter(this,productsList)
            productsRV.layoutManager=LinearLayoutManager(this)
            productsRV.adapter=pAdapter


        }, Response.ErrorListener { error ->

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Error")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })
        requestQ.add(jsonAR)
    }
}
