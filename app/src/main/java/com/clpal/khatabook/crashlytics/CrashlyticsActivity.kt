package com.clpal.khatabook.crashlytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clpal.khatabook.R
import com.clpal.khatabook.databinding.ActivityCrashlyticsBinding
import java.lang.RuntimeException

class CrashlyticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrashlyticsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCrashlyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.crashbutton.setOnClickListener {
            throw  RuntimeException("Application has been crash")
        }
    }
}