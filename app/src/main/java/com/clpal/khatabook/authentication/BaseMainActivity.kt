package com.clpal.khatabook.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.firestore.FirebaseCloudActivity
import com.clpal.khatabook.databinding.ActivityBaseMainBinding
import com.clpal.khatabook.storage.GalleryActivity
import com.clpal.khatabook.realtime.FirebaseRealTimeDatabaseActivity

class BaseMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBaseMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLogin.setOnClickListener {
            val intent=Intent(this, PhoneAuthenticationActivity::class.java)
            startActivity(intent)
        }
        binding.realtimedbsave.setOnClickListener {
            val intent=Intent(this, FirebaseRealTimeDatabaseActivity::class.java)
            startActivity(intent)
        }
        binding.firebaseStoreImage.setOnClickListener {
            val intent=Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
        binding.firebaseCloudStore.setOnClickListener {
            val intent=Intent(this, FirebaseCloudActivity::class.java)
            startActivity(intent)
        }
    }
}