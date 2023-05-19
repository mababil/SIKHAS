package com.example.project


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity:AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            //validasi Email
            if (email.isEmpty()){
                binding.edtEmailLogin.error = "Email Harus Diisi"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailLogin.error = "Email Tidak Valid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }
            //validasi Password
            if (password.isEmpty()){
                binding.edtPasswordLogin.error = "Password Harus Diisi"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }


            LoginFirebase(email,password)
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang Di Sikhas $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeFragment::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun keHome(view: View?) {
        val i = Intent(applicationContext, HomeFragment::class.java)
        startActivity(i)

    }
}