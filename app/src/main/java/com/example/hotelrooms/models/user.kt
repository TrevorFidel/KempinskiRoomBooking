package com.example.hotelrooms.models

class User {
    var name : String=""
    var email :String=""
    var pass : String=""
    var userid: String=""

    constructor(name:String,email:String, pass:String, userid:String){
        this.name = name
        this.email=email
        this.pass=pass
        this.userid=userid
    }
    constructor()
}
