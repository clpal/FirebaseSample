package clpal.FirebaseCloudStore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clpal.realtimefirebasedata.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadCloudActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityReadDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.readdataBtn.setOnClickListener {
            val userName = binding.etusername.text.toString()
            if (!userName.isEmpty()){
                readData(userName)
            }
            else{
                Toast.makeText(this,"PLease enter the Username",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun readData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.get().addOnSuccessListener {
            if (it.exists()) {
                val firstname = it.child("firstname").value
                val lastname = it.child("lastname").value
                val age = it.child("age").value
                binding.etusername.text.clear()
                binding.tvFirstName.text = firstname.toString()
                binding.tvLastName.text = lastname.toString()
                binding.tvAge.text = age.toString()
                Toast.makeText(applicationContext, "Read  Data Succesfully", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(applicationContext, "User does not exits", Toast.LENGTH_LONG).show()

            }

        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()

        }
    }

    companion object {
        const val TAG: String = "SaveDataMainActivity"
    }
}