package clpal.realtimefirebasedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clpal.FirebaseCloudStore.FirebaseCloudActivity
import clpal.firebaseStore.GalleryActivity
import clpal.realtimedatabase.FirebaseRealTimeDatabaseActivity
import clpal.realtimefirebasedata.databinding.ActivityBaseMainBinding

class BaseMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBaseMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBaseMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLogin.setOnClickListener {
            val intent=Intent(this,PhoneNumberActivity::class.java)
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