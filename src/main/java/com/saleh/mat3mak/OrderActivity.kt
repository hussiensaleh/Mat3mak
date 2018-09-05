package com.saleh.mat3mak

import android.annotation.SuppressLint
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
import com.saleh.overthinking.DbManager
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.ticket.view.*


class OrderActivity : AppCompatActivity() {
    var adapter:ItemAdapter?=null
    var listOfPrices=ArrayList<Item>()
    var listOfSelected=ArrayList<String>()
    var OrderName=""
    var OrderPhone=""
    var OrderAddress=""
    var price=""
    var x=0
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.Order)


        var i =intent
        x=i.getIntExtra("x",0)
        when(x) {
            0->listOfPrices = PricesActivity().listOfPrices()
            1->offerOrder()
            else-> listOfPrices=PricesActivity().listOfPrices()
        }
        OrderNameText.setText(loadFromQuery("%"))
        adapter=ItemAdapter(this,listOfPrices)
        listView.adapter=adapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }

    fun loadFromQuery(Name: String) :String{
        var Db = DbManager(this)
        var name=""
        val selectionArgs = arrayOf(Name)
        val projection = arrayOf("name")
        var cursor = Db.Query(projection, "name Like ? ", selectionArgs, "name")
        if (cursor.moveToFirst()) {
            do {
                name=cursor.getString(cursor.getColumnIndex("name"))

            } while (cursor.moveToNext())
        }
        return name
    }


    fun SubmitButton(view:View){
        OrderName    = OrderNameText.text.toString()
        OrderPhone     = OrderPhoneText.text.toString()
        OrderAddress = OrderAddressText.text.toString()
        price = TotalPrice.text.toString()

        var check1=false
        for (index in 0 until listOfPrices.size){
            if (listOfPrices[index].check!!){
                check1=true
                break
            }
        }
        if(OrderNameText.text.isNotEmpty()
                &&OrderPhoneText.text.isNotEmpty()
                &&OrderAddressText.text.isNotEmpty()
                &&check1) {
            var i=Intent(this,OrderCheckActivity::class.java)
            i.putExtra("Name"   ,OrderName)
            i.putExtra("Phone"  ,OrderPhone)
            i.putExtra("Address",OrderAddress)
            i.putExtra("Price"  ,price)
            i.putExtra("MyOrder", listOfSelected)
            startActivity(i)
        }else{
            Toast.makeText(this,"empty fields",Toast.LENGTH_SHORT).show()
        }
    }

    fun offerOrder(){
        var i =intent
        var count =i.getIntExtra("count",0)
        for(index in 0 until count){
            var a=i.getStringExtra("OrderTitle$index"      )
            var b=i.getStringExtra("OrderDescription$index")
            var c=i.getIntExtra("OrderImage$index"        ,0)
            var d=i.getIntExtra("OrderPrice$index"        ,0)
            var e=i.getBooleanExtra("OrderCheck$index",false)
            listOfPrices.add(index,Item(a,b,c,d,false))
        }


    }

    inner class ItemAdapter : BaseAdapter {
        var context: Context? = null
        var listOfPrices = ArrayList<Item>()

        constructor(context: Context, listOfPrices:ArrayList<Item> ) : super() {
            this.context = context
            this.listOfPrices=listOfPrices
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var checkBox:CheckBox
            var myView = layoutInflater.inflate(R.layout.ticket, null)
            myView.Tittle_textView.text=listOfPrices[position].tittle!!
            myView.Description_textView.text=listOfPrices[position].description!!
            myView.Price_textView.text= listOfPrices[position].price!!.toString()+"${resources.getString(R.string.egp)}"
            myView.imageView.setImageResource(listOfPrices[position].image!!)
            myView.checkBox.isChecked=listOfPrices[position].check!!
            checkBox = myView.findViewById(R.id.checkBox)
            checkBox.visibility=View.VISIBLE
            myView.setOnClickListener {
                if (checkBox.isChecked){
                    checkBox.isChecked=false
                    listOfPrices[position].check=false
                    TotalPrice.text=(TotalPrice.text.toString().toInt()- listOfPrices[position].price!!).toString()
                    listOfSelected.remove(listOfPrices[position].tittle!!)
                }else{
                    checkBox.isChecked=true
                    listOfPrices[position].check=true
                    TotalPrice.text=(TotalPrice.text.toString().toInt()+ listOfPrices[position].price!!).toString()
                    listOfSelected.add(listOfPrices[position].tittle!!)
                }
                //listOfSelected= listOfSelected + listOfPrices[position].tittle + " و"
            }
            myView.checkBox.isChecked=listOfPrices[position].check!!

            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfPrices[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfPrices.size
        }


    }
}
