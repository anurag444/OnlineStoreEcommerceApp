package com.anurag.onlinestoreecommerceapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setTitle("Sign Up")

        signup.setOnClickListener {

            if (signuppassword.text.toString().equals(signupconfirmpassword.text.toString())){
                //Registeration Process
                val signupURl = "http://192.168.88.1/OnlineStoreEcommerceApp/join_new_user.php?email="+
                        signupemail.text.toString() +"&name="+signupusername.text.toString()+
                        "&password="+signuppassword.text.toString()
                val requestQ=Volley.newRequestQueue(this)
                var stringRequest=StringRequest(Request.Method.GET,signupURl,Response.Listener {
                    response ->
                    if (response.equals("A user with same Email already exists")) {
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Error")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }else{

                        Person.email=signupemail.text.toString()
                        var intent= Intent(this@SignUp,Home::class.java)
                        startActivity(intent)
                    }
                },Response.ErrorListener { error ->
                    val dialogBuilder=AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Error")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                })
                requestQ.add(stringRequest)
            }
            else{
                //Error Message
                val dialogBuilder=AlertDialog.Builder(this)
                dialogBuilder.setTitle("Error")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()
            }
        }
    }
}
