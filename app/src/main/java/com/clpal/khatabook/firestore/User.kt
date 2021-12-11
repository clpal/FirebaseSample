package com.clpal.khatabook.firestore

import androidx.annotation.Keep

@Keep
data class User(val username:String?=null,val firstname:String?=null,val lastname:String?=null,val age:String?=null)
