package com.saleh.mat3mak

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MyOrdersActivity : AppCompatActivity() {

    var listOfMyOrders=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "My Orders"


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    inner class ItemAdapter : BaseAdapter {
        var context: Context? = null
        var listOfMyOrders = ArrayList<Item>()

        constructor(context: Context,listOfItems:ArrayList<Item> ) : super() {
            this.context = context
            this.listOfMyOrders=listOfItems
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket, null)
            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfMyOrders[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfMyOrders.size
        }

    }

}
