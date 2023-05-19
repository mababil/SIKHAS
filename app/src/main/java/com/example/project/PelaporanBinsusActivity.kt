package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*

class PelaporanBinsusActivity : AppCompatActivity() {

    private lateinit var listData: ListView
    private lateinit var ref : DatabaseReference
    private lateinit var anggotaList: MutableList<sppbinsus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelaporan_binsus)
        ref = FirebaseDatabase.getInstance().getReference("sppbinsus")
        listData = findViewById(R.id.lv_data)
        anggotaList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    anggotaList.clear()
                    for(h in p0.children){
                        val sppbinsus = h.getValue(sppbinsus::class.java)
                        if (sppbinsus != null) {
                            anggotaList.add(sppbinsus)
                        }
                    }

                    val adapter = binsusadapter(this@PelaporanBinsusActivity, R.layout.item_binsus, anggotaList)
                    listData.adapter = adapter

                }
            }
        })
    }
}