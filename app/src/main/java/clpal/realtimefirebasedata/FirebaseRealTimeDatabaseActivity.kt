package clpal.realtimefirebasedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clpal.realtimedatabase.CreateDataActivity
import clpal.realtimedatabase.DeleteDataActivity
import clpal.realtimedatabase.ReadDataMainActivity
import clpal.realtimedatabase.UpdateDataActivity
import clpal.realtimefirebasedata.databinding.ActivityRealTimeFirebaseDatabaseBinding

class FirebaseRealTimeDatabaseActivity
    : AppCompatActivity() {
    private lateinit var binding:ActivityRealTimeFirebaseDatabaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRealTimeFirebaseDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createData.setOnClickListener {
            val intent= Intent(this,CreateDataActivity::class.java)
            startActivity(intent)
        }
        binding.readData.setOnClickListener {
            val intent=Intent(this, ReadDataMainActivity::class.java)
            startActivity(intent)
        }
        binding.updateData.setOnClickListener {
            val intent=Intent(this,UpdateDataActivity::class.java)
            startActivity(intent)
        }
        binding.deleteData.setOnClickListener {
            val intent=Intent(this, DeleteDataActivity::class.java)
            startActivity(intent)
        }

    }
}