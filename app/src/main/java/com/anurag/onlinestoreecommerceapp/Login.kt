package com.anurag.onlinestoreecommerceapp

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("Login")

        login.setOnClickListener {
            val loginURL="http://192.168.88.1/OnlineStoreEcommerceApp/login_app_user.php?email="+
                    loginemail.text.toString() +"&password=" + loginpassword.text.toString()
            val requestQ = Volley.newRequestQueue(this)
            var stringRequest=StringRequest(Request.Method.GET,loginURL,Response.Listener { response ->

                if (response.equals("User Exists")){

                    Person.email=loginemail.text.toString()
                  var intent= Intent(this@Login,Home::class.java)
                  startActivity(intent)

                }else{

                    val dialogBuilder= AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Error")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()
                }

            },Response.ErrorListener { error ->

                val dialogBuilder=AlertDialog.Builder(this)
                dialogBuilder.setTitle("Error")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()

            })
            requestQ.add(stringRequest)


        }
    }
}
