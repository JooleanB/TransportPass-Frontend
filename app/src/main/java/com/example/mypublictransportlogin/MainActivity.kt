package com.example.mypublictransportlogin;

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mypublictransportlogin.ForgotPasswordActivity
import com.example.mypublictransportlogin.R
import com.example.mypublictransportlogin.SignUpActivity

class MainActivity : AppCompatActivity() {
    private var passwordEditText: EditText? = null
    private var keyIconImageView: ImageView? = null
    private var isPasswordVisible = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        passwordEditText = findViewById(R.id.passwordEditText)
        keyIconImageView = findViewById(R.id.keyIconImageView)

        // Set OnClickListener for keyIconImageView to toggle password visibility
        keyIconImageView?.setOnClickListener {
            togglePasswordVisibility()
        }

        // Set OnClickListener for createAccount TextView
        findViewById<TextView>(R.id.createAccount).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.forgotPasswordTextView).setOnClickListener {
            val intent1 = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent1)
        }

        // Apply window insets to handle system UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start animation
        val relativeLayout = findViewById<RelativeLayout>(R.id.main)
        val animationDrawable = relativeLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(3500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
    }

    private fun togglePasswordVisibility() {
        // Toggle password visibility
        isPasswordVisible = !isPasswordVisible
        val transformationMethod = if (isPasswordVisible)
            HideReturnsTransformationMethod.getInstance()
        else
            PasswordTransformationMethod.getInstance()

        passwordEditText?.transformationMethod = transformationMethod

        // Move cursor to the end of the password
        passwordEditText?.setSelection(passwordEditText?.text?.length ?: 0)

        // Update key icon
        val iconResId = if (isPasswordVisible)
            R.drawable.key_icon
        else
            R.drawable.baseline_key_off_24
        keyIconImageView?.setImageResource(iconResId)
    }
}