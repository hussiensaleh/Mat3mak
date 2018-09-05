package com.saleh.mat3mak

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_monasbat.*
import kotlinx.android.synthetic.main.ticket2.view.*

class MonasbatActivity : AppCompatActivity() {
    var listOfMonasbat = ArrayList<Item>()
    var adapter: ItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monasbat)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.Monasabat)
        listOfMonasbat.add(Item("وجبة فرح 1", "قطعة جلاش \nقطعة لحمة \nسيخ كفتة \nرز بسمتي", R.drawable.ic_launcher_background, 800, "100-300"))
        listOfMonasbat.add(Item("وجبة فرح 2", "قطعة جلاش \nقطعة لحمة \nسيخ كفتة \nرز بسمتي", R.drawable.ic_launcher_background, 800, "100-300"))
        adapter = ItemAdapter(this, listOfMonasbat)
        monasbatListView.adapter = adapter
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
        var listOfOffers = ArrayList<Item>()

        constructor(context: Context, listOfItems: ArrayList<Item>) : super() {
            this.context = context
            this.listOfOffers = listOfItems
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket2, null)
            myView.Tittle_textView.text = listOfOffers[position].tittle!!
            myView.Description_textView.text = listOfOffers[position].description!!
            myView.newPrice_textView.text = listOfOffers[position].price.toString()
            myView.newPrice_textView.setTextColor(resources.getColor(R.color.black))
            myView.oldPrice_textView.text = listOfOffers[position].quantity
            myView.oldPrice_textView.setTextColor(resources.getColor(R.color.black))
            myView.quantity.visibility = View.VISIBLE
            myView.priceTxt.visibility = View.VISIBLE


            myView.imageView.setImageResource(listOfOffers[position].image!!)


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
