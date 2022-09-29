package com.example.safehaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var rEmail: EditText
    lateinit var username: EditText
    private lateinit var rPassword: EditText
    private lateinit var confirmPassword: EditText
    lateinit var alreadyregisterd: TextView
    lateinit var btn_register: Button
    private lateinit var Auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_register)

        btn_register = findViewById(R.id.btn_register)
        alreadyregisterd = findViewById(R.id.alreadyregistered)
        username = findViewById(R.id.Username)
        rPassword = findViewById(R.id.RPassword)
        confirmPassword = findViewById(R.id.ConfirmPassword)
        rEmail = findViewById(R.id.REmail)
        Auth = FirebaseAuth.getInstance()



        btn_register.setOnClickListener {
            signUpUser()
        }
        alreadyregisterd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val username = username.text.toString()
        val email = rEmail.text.toString()
        val pass = rPassword.text.toString()
        val confirmPass = confirmPassword.text.toString()

        if (username.isBlank()){
            Toast.makeText(this,"Cannot be blank",Toast.LENGTH_LONG).show()
        }
        if (email.isBlank() or pass.isBlank() or confirmPass.isBlank()) {
            Toast.makeText(this, "EmailPassword can't be blank", Toast.LENGTH_LONG).show()
            return
        }
        if (pass != confirmPass) {
            Toast.makeText(this, "Passwords do not match ", Toast.LENGTH_LONG).show()
        }

        Auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Failed to create", Toast.LENGTH_LONG).show()
                }
        }




    }
}