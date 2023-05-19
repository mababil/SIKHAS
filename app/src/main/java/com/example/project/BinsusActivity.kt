package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class BinsusActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editnama: EditText
    private lateinit var editkelas: EditText
    private lateinit var editnisn: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editbulan: EditText
    private lateinit var jmlh2: EditText
    private lateinit var btnSend: Button
    private lateinit var listData: ListView
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binsus)

        ref = FirebaseDatabase.getInstance().getReference("sppbinsus")
        editnama = findViewById(R.id.editnama)
        editkelas = findViewById(R.id.editkelas)
        editnisn = findViewById(R.id.editnisn)
        editTextDate = findViewById(R.id.editTextDate)
        editbulan = findViewById(R.id.editbulan)
        jmlh2 = findViewById(R.id.jmlh2)
        btnSend = findViewById(R.id.button5)
        btnSend.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        saveData()
    }


    private fun saveData(){
        val nama = editnama.text.toString().trim()
        val kelas = editkelas.text.toString().trim()
        val nisn = editnisn.text.toString().trim()
        val date = editTextDate.text.toString().trim()
        val bulan = editbulan.text.toString().trim()
        val jumlah = jmlh2.text.toString().trim()

        if(nama.isEmpty()){
            editnama.error = "Isi Nama !!!"
            return
        }

        if(kelas.isEmpty()){
            editkelas.error = "Isi Kelas !!!"
            return
        }

        if(nisn.isEmpty()){
            editnisn.error = "Isi NISN !!!"
            return

        }

        if(date.isEmpty()){
            editTextDate.error = "Isi Tanggal Pembayaran !!!"
            return
        }

        if(bulan.isEmpty()){
            editbulan.error = "Isi Bulan !!!"
            return
        }
        if(jumlah.isEmpty()){
            jmlh2.error = "Isi Jumlah Pembayaran !!!"
            return
        }


        val dataId = ref.push().key

        val data = sppbinsus(dataId!!,nama,kelas,nisn, date ,bulan,jumlah)

        if (dataId != null){
            ref.child(dataId).setValue(data).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data Telah Ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

}