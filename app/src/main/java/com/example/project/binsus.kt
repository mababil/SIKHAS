package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityBinsusBinding
import com.example.project.databinding.ActivityRegulerBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class binsus:AppCompatActivity() {

    private lateinit var binding: ActivityBinsusBinding
    private lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBinsusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().getReference("sppbinsus")
        binding.button5.setOnClickListener {
            val nama = binding.editnama.text.toString().trim()
            val kelas = binding.editkelas.text.toString().trim()
            val nisn = binding.editnisn.text.toString().trim()
            val date = binding.editTextDate.text.toString().trim()
            val bulan = binding.editbulan.text.toString().trim()
            val jumlah = binding.jmlh2.text.toString().trim()

            val dataId = ref.push().key

            val data = sppreguler(dataId!!,nama,kelas,nisn,date,bulan,jumlah)

            if (dataId != null){
                ref.child(dataId).setValue(data).addOnCompleteListener{
                    Toast.makeText(applicationContext, "Data Telah Ditambahkan", Toast.LENGTH_SHORT).show()
                    val i = Intent(applicationContext, HomeFragment::class.java)
                    startActivity(i)
                }
            }
        }
    }
    fun keHome(view: View?) {
        val i = Intent(applicationContext, HomeFragment::class.java)
        startActivity(i)

    }
}