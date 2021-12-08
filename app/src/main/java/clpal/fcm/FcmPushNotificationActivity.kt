package clpal.fcm

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clpal.realtimefirebasedata.R
import clpal.realtimefirebasedata.databinding.ActivityFcmPushNotificationBinding

class FcmPushNotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFcmPushNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFcmPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}