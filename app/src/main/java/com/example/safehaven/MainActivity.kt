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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var SignUp: TextView
    lateinit var Email: EditText
    private lateinit var Password: EditText
    lateinit var btn_login: Button
    lateinit var auth: FirebaseAuth
    lateinit var ForgotPassword:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)


        SignUp = findViewById(R.id.SignUp)
        btn_login = findViewById(R.id.btn_login)
        Email = findViewById(R.id.Email)
        Password = findViewById(R.id.Password)
        ForgotPassword=findViewById(R.id.ForgotPassword)
        auth = Firebase.auth

        btn_login.setOnClickListener {
            login()
        }

        SignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        ForgotPassword.setOnClickListener{
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login() {
        val email = Email.text.toString()
        val pass = Password.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_LONG).show()
                val intent= Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_LONG).show()
        }
    }


}
