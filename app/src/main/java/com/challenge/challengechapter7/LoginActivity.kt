package com.challenge.challengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.challenge.challengechapter7.databinding.ActivityLoginBinding
import com.challenge.challengechapter7.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnApplyLogin.setOnClickListener {
            val email = binding.etInputEmailLogin.text.toString()
            val pass = binding.etInputPassLogin.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Fill all", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvDontHaveAnAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}