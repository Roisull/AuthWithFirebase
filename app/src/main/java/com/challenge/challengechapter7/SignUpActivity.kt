package com.challenge.challengechapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.challenge.challengechapter7.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
         binding.btnApplySignUp.setOnClickListener {
             val email = binding.etInputEmailSignUp.text.toString()
             val pass = binding.etInputPassSignUp.text.toString()
             val rePass = binding.etInputRePassSignUp.text.toString()

             if (email.isNotEmpty() && pass.isNotEmpty() && rePass.isNotEmpty()){
                 if (pass == rePass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                 }else{
                     Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                 }
             }else{
                 Toast.makeText(this,"Fill all", Toast.LENGTH_SHORT).show()
             }
         }

        binding.tvHaveAnAccountSignUp.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}