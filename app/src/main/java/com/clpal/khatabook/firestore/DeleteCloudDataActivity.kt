package com.clpal.khatabook.firestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteCloudDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteBtn.setOnClickListener {
            val userName = binding.userName.text.toString()

            if (userName.isNotEmpty()) {
                deleteData(userName)
            } else {
                Toast.makeText(this, "PLease enter the Username", Toast.LENGTH_SHORT).show()

            }
        }

    }

    private fun deleteData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.removeValue().addOnSuccessListener {
            binding.userName.text.clear()
            Toast.makeText(this, "Successfuly Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Failed to delete Data", Toast.LENGTH_SHORT).show()

        }
    }
}