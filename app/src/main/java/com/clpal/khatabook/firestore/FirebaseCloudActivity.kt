package com.clpal.khatabook.firestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.MainAcitvityCloudBinding

class FirebaseCloudActivity : AppCompatActivity() {
    private lateinit var binding: MainAcitvityCloudBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=MainAcitvityCloudBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createData.setOnClickListener {
            val intent= Intent(this, CreateCloudDataActivity::class.java)
            startActivity(intent)
        }
        binding.readData.setOnClickListener {
            val intent=Intent(this, ReadCloudActivity::class.java)
            startActivity(intent)
        }
        binding.updateData.setOnClickListener {
            val intent=Intent(this, UpdateCloudDataActivity::class.java)
            startActivity(intent)
        }
        binding.deleteData.setOnClickListener {
            val intent=Intent(this, DeleteCloudDataActivity::class.java)
            startActivity(intent)
        }

    }
}