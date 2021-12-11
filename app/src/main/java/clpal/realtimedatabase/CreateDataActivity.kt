package clpal.realtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import clpal.realtimefirebasedata.databinding.ActivitySaveDataBinding
import com.google.firebase.database.*

// https://www.youtube.com/playlist?list=PLQ9S01mirRdVX6qb0uDYUZUuZg1rgL1IV
class CreateDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* // Write a message to the database
         val database1 = Firebase.database
         val myRef = database1.getReference("message")
         myRef.setValue("Hello, World!")*/
        binding.registerBtn.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val age = binding.age.text.toString()
            val userName = binding.userName.text.toString()
           // database = FirebaseDatabase.getInstance("https://dynamic-amulet-123805-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(userName, firstName, lastName, age)
           // database.child(userName).setValue(user).addOnSuccessListener {
            database.setValue(user).addOnSuccessListener {
                binding.userName.text.clear()
                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.age.text.clear()
                Toast.makeText(applicationContext, "Success saved Data", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

        }

    }
    companion object{
        const val TAG:String="SaveDataActivity"
    }
}