package com.clpal.khatabook.realtime

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {
         val userName=   binding.userName.text.toString()
          val firstName=  binding.firstName.text.toString()
          val lastname=  binding.lastname.text.toString()
          val age=  binding.age.text.toString()
            updataData(firstName,lastname,userName,age)
        }
    }

    private fun updataData(firstName: String, lastname: String, userName: String, age: String) {
database=FirebaseDatabase.getInstance().getReference("Users")
       val hashMap= mapOf("firstname" to firstName,
           "lastname" to lastname,"username" to userName,"age" to age
       )
database.updateChildren(hashMap).addOnSuccessListener {
    binding.userName.text.clear()
    binding.firstName.text.clear()
    binding.lastname.text.clear()
    binding.age.text.clear()
    Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()

}.addOnFailureListener {

    Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
}
    }
}