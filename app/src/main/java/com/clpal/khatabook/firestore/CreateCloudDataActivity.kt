package com.clpal.khatabook.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActvityCreateCloudBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.clpal.khatabook.realtime.User

class CreateCloudDataActivity : AppCompatActivity() {
    private lateinit var binding: ActvityCreateCloudBinding
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvityCreateCloudBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Firebase.firestore
        binding.registerBtn.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val age = binding.age.text.toString()
            val userName = binding.userName.text.toString()
            //val db = Firebase.firestore
            // val db1=FirebaseFirestore.getInstance()
            val userData = User(userName, firstName, lastName, age)
            val users: MutableMap<String, Any> = HashMap()
            users["firstname"] = firstName
            users["lastname"] = lastName
            users["age"] = age
            users["username"] = userName

            val user = hashMapOf(
                "firstname" to firstName,
                "lastname" to lastName,
                "age" to age,
                "username" to userName
            )
            db.collection("users").add(user).addOnSuccessListener {documentReference->
                binding.userName.text.clear()
                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.age.text.clear()
                Toast.makeText(applicationContext, "record added successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {e->
                Toast.makeText(applicationContext, "record failed to add", Toast.LENGTH_LONG).show()
            }
            readFireStoreData()
        }
        readFireStoreData()

    }
    fun readFireStoreData(){
        db.collection("users").get().addOnCompleteListener {it
            val result:StringBuffer= StringBuffer()
            if (it.isSuccessful)
                for (document in it.result!!){
                    result.append(document.data.getValue("firstname")).append(" ")
                        . append(document.data.getValue("lastname")).append(" ")
                        . append(document.data.getValue("age")).append(" ")
                        . append(document.data.getValue("username")).append("\n\n")
                }
            binding.result.text=result
        }

    }

}