package com.anurag.onlinestoreecommerceapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_finalize_shopping_list.*

class FinalizeShoppingList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_shopping_list)

        val payURL="http://192.168.88.1/OnlineStoreEcommerceApp/calculate_total_price.php?invoice_num=${intent.getStringExtra("LATEST_INVOICE")}"
        val requestQ=Volley.newRequestQueue(this)
        var stringRequest=StringRequest(Request.Method.GET,payURL,Response.Listener { response ->
            payment.text="Pay $response via PayPal"

        },Response.ErrorListener { error ->  })
        requestQ.add(stringRequest)



        }
}

