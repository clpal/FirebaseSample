package proguard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clpal.realtimefirebasedata.R
import clpal.realtimefirebasedata.databinding.ActivityProguardMainBinding

class ProguardMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProguardMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProguardMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textproguard.setText(R.string.progurard_note)
    }
}