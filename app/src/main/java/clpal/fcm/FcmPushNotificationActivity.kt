package clpal.fcm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import clpal.realtimefirebasedata.databinding.ActivityFcmPushNotificationBinding

class FcmPushNotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFcmPushNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFcmPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}