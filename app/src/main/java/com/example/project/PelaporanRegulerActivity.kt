package com.example.project

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class PelaporanRegulerActivity : AppCompatActivity() {

    private lateinit var listData: ListView
    private lateinit var ref : DatabaseReference
    private lateinit var anggotaList: MutableList<sppreguler>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelaporan_binsus)
        ref = FirebaseDatabase.getInstance().getReference("sppreguler")
        listData = findViewById(R.id.lv_data)
        anggotaList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    anggotaList.clear()
                    for (h in p0.children) {
                        val sppreguler = h.getValue(sppreguler::class.java)
                        if (sppreguler != null) {
                            anggotaList.add(sppreguler)
                        }
                    }

                    val adapter = reguleradapter(
                        this@PelaporanRegulerActivity,
                        R.layout.item_reguler,
                        anggotaList
                    )
                    listData.adapter = adapter

                }
            }
        })
    }
    }