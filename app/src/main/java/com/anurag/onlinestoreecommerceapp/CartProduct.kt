package com.anurag.onlinestoreecommerceapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_product.*
import kotlinx.android.synthetic.main.eproduct_row.*
import kotlinx.android.synthetic.main.eproduct_row.view.*

class CartProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)
        setTitle("Cart")

        var cartURL="http://192.168.88.1/OnlineStoreEcommerceApp/fetch_temporary_order.php?email=${Person.email}"
        var productsList=ArrayList<String>()
        var requestQ=Volley.newRequestQueue(this)
        var jsonAR=JsonArrayRequest(Request.Method.GET,cartURL,null,Response.Listener { response ->

            for (JOIndex in 0.until(response.length())){
                productsList.add("${response.getJSONObject(JOIndex).getInt("id")}"+
                        "\n${response.getJSONObject(JOIndex).getString("name")}"+
                        "\n${response.getJSONObject(JOIndex).getInt("price")}"+
                        "\n${response.getJSONObject(JOIndex).getString("email")}"+
                        "\n${response.getJSONObject(JOIndex).getInt("amount")}")
            }
            var cartProductAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,productsList)
            cartlist.adapter=cartProductAdapter
        },Response.ErrorListener { error ->


        })
        requestQ.add(jsonAR)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.cart_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==R.id.continueshopping){
            var intent= Intent(this,Home::class.java)
            startActivity(intent)
        }else if (item?.itemId==R.id.removieitem){
            val removeURL="http://192.168.88.1/OnlineStoreEcommerceApp/delete_temporary_order.php?email=${Person.email}"
            val requestQ=Volley.newRequestQueue(this)
            var stringRequest=StringRequest(Request.Method.GET,removeURL,Response.Listener { response ->

                var intent= Intent(this,Home::class.java)
                startActivity(intent)
            },Response.ErrorListener { error ->

            })
            requestQ.add(stringRequest)


        }else if(item?.itemId==R.id.verifyitem){

            var verifyURL="http://192.168.88.1/OnlineStoreEcommerceApp/verify_order.php?email=${Person.email}"
            var requestQ=Volley.newRequestQueue(this)
            var stringRequest=StringRequest(Request.Method.GET,verifyURL,Response.Listener { response ->

                var intent= Intent(this,FinalizeShoppingList::class.java)
                intent.putExtra("LATEST_INVOICE",response)
                startActivity(intent)
            },Response.ErrorListener { error ->  })
            requestQ.add(stringRequest)
        }
        return super.onOptionsItemSelected(item)
    }
}
