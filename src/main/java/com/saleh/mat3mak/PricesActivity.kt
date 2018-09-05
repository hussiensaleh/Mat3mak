package com.saleh.mat3mak

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_prices.*
import kotlinx.android.synthetic.main.ticket.view.*
import android.view.MenuInflater
import android.view.ContextMenu.ContextMenuInfo
import android.view.ContextMenu





class PricesActivity : AppCompatActivity() {
    var listOfPrices = ArrayList<Item>()
    var adapter: ItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prices)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.menu)
        listOfPrices = listOfPrices()
        adapter = ItemAdapter(this, listOfPrices)
        listViewPrices.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        if (item.itemId == R.id.menuOrderBu){
            val i = Intent(this,OrderActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu):Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun listOfPrices(): ArrayList<Item> {
        var list = ArrayList<Item>()
        list.add(Item("اكله 01 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 15, false))
        list.add(Item("اكله 02 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 20, false))
        list.add(Item("اكله 03 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 60, false))
        list.add(Item("اكله 04 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 13, false))
        list.add(Item("اكله 05 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 50, false))
        list.add(Item("اكله 06 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 49, false))
        list.add(Item("اكله 07 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 20, false))
        list.add(Item("اكله 08 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 37, false))
        list.add(Item("اكله 09 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 38, false))
        list.add(Item("اكله 10 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 32, false))
        list.add(Item("اكله 11 ", " مكون 01\n مكون 02 \n مكون 03", R.drawable.ic_launcher_background, 42, false))
        return list
    }

    inner class ItemAdapter : BaseAdapter {
        var context: Context? = null
        var listOfPrices = ArrayList<Item>()

        constructor(context: Context, listOfItems: ArrayList<Item>) : super() {
            this.context = context
            this.listOfPrices = listOfItems
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket, null)
            myView.Tittle_textView.text = listOfPrices[position].tittle!!
            myView.Description_textView.text = listOfPrices[position].description!!
            myView.Price_textView.text = listOfPrices[position].price!!.toString() + "ج.م"
            myView.imageView.setImageResource(listOfPrices[position].image!!)
            myView.setOnClickListener {
                var i=Intent(context,mealActivity::class.java)
                i.putExtra("OrderTitle"      , listOfPrices[position].tittle)
                i.putExtra("OrderDescription", listOfPrices[position].description)
                i.putExtra("OrderImage"      , listOfPrices[position].image)
                i.putExtra("OrderPrice"      , listOfPrices[position].price)


                startActivity(i)
            }
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


