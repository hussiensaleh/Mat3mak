package com.saleh.mat3mak

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_offers.*
import kotlinx.android.synthetic.main.ticket3.view.*
import java.text.NumberFormat

class OffersActivity : AppCompatActivity() {
    var listOfOffers=ArrayList<Item>()
    var adapter:ItemAdapter?=null
    var listOfSelected=ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.Offers)
        listOfOffers=listOfOffers()
        adapter=ItemAdapter(this,listOfOffers)
        listView.adapter=adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }

    fun OffersButton(view:View){
        var i =Intent(this,OrderActivity::class.java)
        if (listOfOffers.size>=0) {
            for (index in 0 until listOfSelected.size) {
                i.putExtra("count",listOfSelected.size)
                i.putExtra("x",1)
                i.putExtra("OrderTitle$index"      , listOfSelected[index].tittle)
                i.putExtra("OrderDescription$index", listOfSelected[index].description)
                i.putExtra("OrderImage$index"      , listOfSelected[index].image)
                i.putExtra("OrderPrice$index"      , listOfSelected[index].price)
                i.putExtra("OrderCheck$index"      , listOfSelected[index].check)
            }

            startActivity(i)
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }

    }

    fun listOfOffers():ArrayList<Item>{
        var list=ArrayList<Item>()
        list.add(Item("وجبات 01 "," اكله 01\n اكله 02 \n اكله 03",R.drawable.ic_launcher_background,13,15,false))
        list.add(Item("وجبات 02 "," اكله 01\n اكله 02 \n اكله 03",R.drawable.ic_launcher_background,17,20,false))
        list.add(Item("وجبات 03 "," اكله 01\n اكله 02 \n اكله 03",R.drawable.ic_launcher_background,55,60,false))
        list.add(Item("وجبات 04 "," اكله 01\n اكله 02 \n اكله 03",R.drawable.ic_launcher_background,11,13,false))
        return list
    }
    inner class ItemAdapter : BaseAdapter {
        var context: Context? = null
        var listOfOffers = ArrayList<Item>()

        constructor(context: Context,listOfItems:ArrayList<Item> ) : super() {
            this.context = context
            this.listOfOffers=listOfItems
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var checkBox: CheckBox
            var myView = layoutInflater.inflate(R.layout.ticket3, null)
            myView.Tittle_textView.text=listOfOffers[position].tittle!!
            myView.Description_textView.text=listOfOffers[position].description!!
            myView.newPrice_textView.text= listOfOffers[position].price!!.toString()+"ج.م"
            myView.oldPrice_textView.text= listOfOffers[position].oldPrice!!.toString()+"ج.م"
            myView.imageView.setImageResource(listOfOffers[position].image!!)
            checkBox=myView.findViewById(R.id.offers_checkBox)
            myView.setOnClickListener {
                if (checkBox.isChecked){
                    checkBox.isChecked=false
                    listOfOffers[position].check=false
                    //TotalPrice.text=(TotalPrice.text.toString().toInt()- listOfPrices[position].price!!).toString()
                    listOfSelected!!.remove(listOfOffers[position])
                }else{
                    checkBox.isChecked=true
                    listOfOffers[position].check=true
                    //TotalPrice.text=(TotalPrice.text.toString().toInt()+ listOfPrices[position].price!!).toString()
                    listOfSelected.add(listOfOffers[position])
                }
            }
            myView.offers_checkBox.isChecked=listOfOffers[position].check!!


            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfOffers[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfOffers.size
        }

    }

}
