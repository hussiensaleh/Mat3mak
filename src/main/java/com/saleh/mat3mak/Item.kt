package com.saleh.mat3mak

class Item(){
    var tittle:String?=null
    var description:String?=null
    var image:Int?=null
    var price:Int?=null
    var oldPrice:Int?=null
    var check:Boolean?=null
    var quantity:String?=null

    constructor(tittle:String,description:String,image:Int,price:Int,check:Boolean) : this() {
        this.description=description
        this.tittle=tittle
        this.image=image
        this.price=price
        this.check=check
    }
    constructor(tittle:String,description:String,image:Int,price:Int,oldPrice:Int,check:Boolean) : this() {
        this.description=description
        this.tittle=tittle
        this.image=image
        this.price=price
        this.oldPrice=oldPrice
        this.check=check
    }
    constructor(tittle:String,description:String,image:Int,price:Int,quantity:String) : this() {
        this.description=description
        this.tittle=tittle
        this.image=image
        this.price=price
        this.quantity=quantity
    }

}