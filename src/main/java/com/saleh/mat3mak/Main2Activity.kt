package com.saleh.mat3mak

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.saleh.overthinking.DbManager
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
var MyName: String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyName=loadFromQuery()
        if(MyName!=""){
            var i=Intent(this,MainActivity::class.java)
            startActivity(i)
            this.finish()
        }
        setContentView(R.layout.activity_main2)

        button2.setOnClickListener{
            MyName=editText.text.toString()
            var i=Intent(this,MainActivity::class.java)
            if (addTODatabase(MyName) > 0) {
                startActivity(i)
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                this.finish()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }


    }
    fun addTODatabase(Name:String):Long{
        var Db= DbManager(this)
        var values= ContentValues()
        values.put("name",Name)

            val ID = Db.Insert(values)

        return ID
    }

    fun loadFromQuery() :String{
        var Db = DbManager(this)
        var name=""
        val projection = arrayOf("name")
        var cursor = Db.Query(null, null, null, "name")
        if (cursor.moveToFirst()) {
            do {
                name=cursor.getString(cursor.getColumnIndex("name"))

            } while (cursor.moveToNext())
        }
        return name
    }


}
