package com.saleh.mat3mak

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_order_check.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener



class OrderCheckActivity : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("Name")
    var OrderName=""
    var OrderPhone=""
    var OrderAddress=""
    var price=""
    var listOfSelected=""
    var list=HashMap<String,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_check)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title=resources.getString(R.string.OrderSummary)

        var i = intent
        OrderName = i.getSerializableExtra("Name").toString()
        OrderPhone = i.getSerializableExtra("Phone").toString()
        OrderAddress = i.getSerializableExtra("Address").toString()
        price = i.getSerializableExtra("Price").toString()
        listOfSelected = i.getSerializableExtra("MyOrder").toString()
        textViewName.text    = OrderName
        textViewPhone.text   = OrderPhone
        textViewPrice.text   = price
        textViewAddress.text = OrderAddress
        textViewOrder.text   = listOfSelected
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    fun sentOrder(view:View){
        list["name"] = OrderName
        list["phone"] = OrderPhone
        list["price"] = price
        list["address"] = OrderAddress
        list["myOrder"] = listOfSelected
        sentToFB1("",list)
    }

    fun sentToFB1(reference:String,value:HashMap<String,String>){
        myRef=database.getReference("Orders").child(OrderName).child(reference)
        //myRef = database.getReference(reference)
        myRef.setValue(value)
    }

    /*fun getFromFB(){
// Read from the database
        myRef=database.getReference("Orders").child(OrderName)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value as Ha<Item4>
                x=value.size
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                }
        })
    }*/
}
