package com.saleh.mat3mak

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_meal.*

class mealActivity : AppCompatActivity() {
    var name ="null"
    var description="null"
    var image=0
    var price=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val i =intent
        name=i.getStringExtra("OrderTitle")
        description=i.getStringExtra("OrderDescription")
        image=i.getIntExtra("OrderImage",0)
        price=i.getIntExtra("OrderPrice",0)
        supportActionBar!!.title = name
        meal_tittle_textView.text=name
        meal_des_textView.text=description
        meal_imageView.setImageDrawable(resources.getDrawable(image))
        meal_price_textView.text = "$price ${resources.getString(R.string.egp)}"



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }

}
