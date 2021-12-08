package clpal.realtimefirebasedata

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

// https://www.geeksforgeeks.org/phone-number-verification-using-firebase-in-android/?ref=lbp
class PhoneNumberActivity : AppCompatActivity() {
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var txtPhone: EditText

    //  private lateinit var otp: EditText
    private lateinit var login: Button
    private lateinit var getotp: TextView
    private lateinit var setotp: TextView
    private lateinit var resendOtp: TextView
    private var getotpclick: Boolean = false
    private lateinit var countdowntimer: TextView
    private lateinit var dialog: Dialog
    lateinit var firebase: FirebaseUser

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
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
                Log.d(TAG, "onVerificationCompleted:$credential")
                // signInWithPhoneAuthCredential(credential)
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.d(TAG, "onVerificationCompleted Success")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

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
                Log.d(TAG, "onCodeSent:$verificationId")

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
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI

                }
            }
    }

    private fun initElement() {

        txtPhone = findViewById(R.id.txtPhone)
        getotp = findViewById(R.id.get_otp)
        login = findViewById(R.id.btnLogin)
        resendOtp = findViewById(R.id.resend_otp)
        setotp = findViewById(R.id.otp_sent)
        setotp.isVisible = false
        countdowntimer = findViewById(R.id.countdown_timer)
        txtPhone.addTextChangedListener(object : TextWatcher {

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
                    //loginOnClick()
                     login.setTextColor(Color.parseColor("#FFFFFF"))
                    login.isClickable=true
                }
                else{
                    login.setTextColor(Color.parseColor("#C0BEBE"))
                    login.isClickable=false
                }
            }
        })
        resendOtp.setOnClickListener {
            loginOnClick()
        }
        login.setOnClickListener {
            loginOnClick()
        }
        login.setTextColor(Color.parseColor("#C0BEBE"))
        getotp.setOnClickListener {
            getotpOnClick()
        }
    }

    private fun loginOnClick() {
        var number: String = txtPhone.text.toString().trim()



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
                txtPhone.setError("valid number is required")
                txtPhone.requestFocus()
            }
        }
    }

    private fun verifyCode(phoneNumber: String) {
        setotp.isVisible = true
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d(TAG, "Auth started")

    }

    private fun getotpOnClick() {

    }

    companion object {
        private const val TAG: String = "PhoneNumberActivity"
    }

}
