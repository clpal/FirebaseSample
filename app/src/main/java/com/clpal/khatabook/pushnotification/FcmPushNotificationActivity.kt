package com.clpal.khatabook.pushnotification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActivityFcmPushNotificationBinding

class FcmPushNotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFcmPushNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFcmPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}