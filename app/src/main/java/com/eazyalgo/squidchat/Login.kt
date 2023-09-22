package com.eazyalgo.squidchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.button_login)
        btnSignup = findViewById(R.id.button_signup)

        btnSignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password: String)
    {
        //logic of logging in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for logging in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                    var inflater= LayoutInflater.from(this)
                    var popupview= inflater.inflate(R.layout.popup,null,false)
                    var imagee= popupview.findViewById<ImageView>(R.id.imageinpopup)
                    imagee.setImageResource(R.drawable.eco)
                    var close= popupview.findViewById<ImageView>(R.id.close)
                    var builder= PopupWindow(popupview,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,true)
                    builder.setBackgroundDrawable(getDrawable(R.drawable.background))
                    builder.animationStyle= R.style.DialogAnimation
                    builder.showAtLocation(this.findViewById(R.id.loginpage), Gravity.CENTER,0,0)

                } else {
                    // If sign in fails, display a message to the user
                    Toast.makeText(this@Login, "User does not Exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}