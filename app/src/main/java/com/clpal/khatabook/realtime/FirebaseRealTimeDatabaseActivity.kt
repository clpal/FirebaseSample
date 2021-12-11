package com.clpal.khatabook.realtime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActivityRealTimeFirebaseDatabaseBinding

class FirebaseRealTimeDatabaseActivity
    : AppCompatActivity() {
    private lateinit var binding: ActivityRealTimeFirebaseDatabaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRealTimeFirebaseDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createData.setOnClickListener {
            val intent= Intent(this, CreateDataActivity::class.java)
            startActivity(intent)
        }
        binding.readData.setOnClickListener {
            val intent=Intent(this, ReadDataMainActivity::class.java)
            startActivity(intent)
        }
        binding.updateData.setOnClickListener {
            val intent=Intent(this, UpdateDataActivity::class.java)
            startActivity(intent)
        }
        binding.deleteData.setOnClickListener {
            val intent=Intent(this, DeleteDataActivity::class.java)
            startActivity(intent)
        }

    }
}