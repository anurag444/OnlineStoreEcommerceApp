package com.anurag.onlinestoreecommerceapp

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.eproduct_row.view.*

@Suppress("DEPRECATION")
class EproductsAdapter(var context:Context, var arrayList: ArrayList<Eproducts>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val productView =LayoutInflater.from(context).inflate(R.layout.eproduct_row,parent,false)
        return productViewHolder(productView)
     }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {

        (holder as productViewHolder).initializeUIcomponents(arrayList.get(p1).id,arrayList.get(p1).name,
            arrayList.get(p1).price)
     }

    inner class productViewHolder(pView: View):RecyclerView.ViewHolder(pView){
        fun initializeUIcomponents(id:Int,name:String,price:Int){
            itemView.txtname.text=name
            itemView.textid.text=id.toString()
            itemView.txtprice.text=price.toString()

            itemView.add.setOnClickListener {

                Person.addtocartID=id
                var amountFragment=AmountFragment()
                var fragmentManager =(itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager,"TAG")
            }
        }
    }
}