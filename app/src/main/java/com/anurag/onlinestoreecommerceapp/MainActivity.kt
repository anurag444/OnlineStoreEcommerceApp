package com.anurag.onlinestoreecommerceapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Welcome")

        signupbtn.setOnClickListener {
            var intent = Intent(this,SignUp::class.java )
            startActivity(intent)
        }
        loginbtn.setOnClickListener {
            var intent = Intent(this,Login::class.java )
            startActivity(intent)
        }
    }
}
