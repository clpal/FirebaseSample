package com.clpal.khatabook.authentication

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.clpal.khatabook.databinding.PhoneAuthenticationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class PhoneAuthenticationActivity : AppCompatActivity() {
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    private var getotpclick: Boolean = false
    private lateinit var countdowntimer: TextView
    private lateinit var dialog: Dialog
    lateinit var firebase: FirebaseUser

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private lateinit var binding: PhoneAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhoneAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        //auth = Firebase.auth
        initElement()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(Utils.TAG, "onVerificationCompleted:$credential")
                // signInWithPhoneAuthCredential(credential)
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.d(Utils.TAG, "onVerificationCompleted Success")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(Utils.TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(Utils.TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                val intent = Intent(applicationContext, OtpActivity::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(Utils.TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(Utils.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }
    }

    private fun initElement() {

        binding.otpSent.isVisible = false

        binding.txtPhone.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                if (s.length == 10) {

                    binding.btnLogin.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.btnLogin.isClickable = true
                } else {
                    binding.btnLogin.setTextColor(Color.parseColor("#C0BEBE"))
                    binding.btnLogin.isClickable = false
                }
            }
        })
        binding.resendOtp.setOnClickListener {
            loginOnClick()
        }
        binding.btnLogin.setOnClickListener {
            loginOnClick()
        }
        binding.btnLogin.setTextColor(Color.parseColor("#C0BEBE"))
        binding.getOtp.setOnClickListener {
            getotpOnClick()
        }
    }

    private fun loginOnClick() {
        var number: String = binding.txtPhone.text.toString().trim()



        if (number.length == 10) {

            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            /*val dialog = Dialog(applicationContext)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_wait)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()*/
            number = "+91$number"

            verifyCode(number)
        } else {
            if (number.isEmpty() || number.length < 10) {
                binding.txtPhone.setError("valid number is required")
                binding.txtPhone.requestFocus()
            }
        }
    }

    private fun verifyCode(phoneNumber: String) {
        binding.otpSent.isVisible = true
        binding.otpSent
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d(Utils.TAG, "Auth started")

    }

    private fun getotpOnClick() {

    }


}
