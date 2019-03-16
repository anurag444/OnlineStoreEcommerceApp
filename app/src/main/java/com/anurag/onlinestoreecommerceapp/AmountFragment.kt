package com.anurag.onlinestoreecommerceapp


import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_amount.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("DEPRECATION")
class AmountFragment() : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var fragmentView= inflater.inflate(R.layout.fragment_amount, container, false)

        var amount= fragmentView.findViewById<EditText>(R.id.amount)
        var addtocart=fragmentView.findViewById<Button>(R.id.addtocart)
        addtocart.setOnClickListener {

         var proURL="http://192.168.88.1/OnlineStoreEcommerceApp/insert_temporary_order.php?email=${Person.email}&id=${Person.addtocartID}&amount=${amount.text}"
         var requestQ=Volley.newRequestQueue(activity)
            var stringRequest=StringRequest(Request.Method.GET,proURL,Response.Listener { response ->

              var intent=Intent(activity,CartProduct::class.java)
                startActivity(intent)
            },Response.ErrorListener { error->


            })
            requestQ.add(stringRequest)

        }

        return fragmentView
    }


}