package com.saleh.overthinking

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast


class DbManager{
    val DbName="Mat3mk"
    val Dbtable="Name"
    val col_ID="ID"
    val col_Name="name"
    val DbVersion =1
    val mySqlStatment= "create table IF NOT EXISTS $Dbtable ($col_ID INTEGER primary Key ,$col_Name TEXT);"
    var sqlDB:SQLiteDatabase?=null
    constructor(context: Context){
        var db=DbHelper(context)
        sqlDB=db.writableDatabase
    }

    inner class DbHelper : SQLiteOpenHelper{

        var context:Context?=null
        constructor(context: Context):super(context,DbName,null,DbVersion){

            this.context=context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(mySqlStatment)
            Toast.makeText(context,"DataBase Created",Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table if EXISTS Dbtable")
        }
    }
    fun Insert(content:ContentValues):Long {
        val ID=sqlDB!!.insert(Dbtable,"",content)
        return ID

    }

    fun  Query(projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sorOrder:String): Cursor {
        val qb= SQLiteQueryBuilder()
        qb.tables=Dbtable
        var cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
        return cursor

    }

    fun Update(values:ContentValues,selection:String,selectionargs:Array<String>):Int{

        val count=sqlDB!!.update(Dbtable,values,selection,selectionargs)
        return count
    }



}