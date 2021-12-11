package com.clpal.khatabook.storage

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clpal.khatabook.databinding.ActivityImageStoreBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class GalleryActivity : AppCompatActivity() {
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private lateinit var binding: ActivityImageStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = firebaseStore!!.reference
        binding.save.isEnabled = false
        binding.image
        binding.upload.setOnClickListener {

            launchGallery()
        }
        binding.save.setOnClickListener {
            uploadImage()
        }


    }


    private fun launchGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            Utils.PickImageRequest
        )


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utils.PickImageRequest && resultCode == Activity.RESULT_OK) {
            binding.upload.isEnabled = false
            binding.save.isEnabled = true

            if (data == null || data.data == null) {
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                binding.image.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            val imageRef = storageReference?.child("uploads/pic.jpg" + UUID.randomUUID().toString())
            imageRef?.putFile(filePath!!)?.addOnSuccessListener { p ->
                pd.dismiss()
                Toast.makeText(this, "File Uploaded", Toast.LENGTH_SHORT).show()
            }?.addOnFailureListener { p ->
                Toast.makeText(this, p.message, Toast.LENGTH_SHORT).show()
            }?.addOnProgressListener { p ->
                var progress: Double = (100.0 * p.bytesTransferred) / p.totalByteCount
                pd.setMessage("Uploaded${progress.toInt()}%")

            }


        } else {
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }
}