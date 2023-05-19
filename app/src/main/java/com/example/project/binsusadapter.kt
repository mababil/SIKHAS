package com.example.project

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class binsusadapter (
    val anggotaContext: Context,
    val layoutResId: Int,
    val anggotaList: List<sppbinsus>
) : ArrayAdapter<sppbinsus>(anggotaContext, layoutResId, anggotaList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(anggotaContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val edt_nm: TextView = view.findViewById(R.id.editnama)
        val kelas: TextView = view.findViewById(R.id.editkelas)
        val nisn: TextView = view.findViewById(R.id.editnisn)
        val editTextDate: TextView = view.findViewById(R.id.editTextDate)
        val bulan: TextView = view.findViewById(R.id.editbulan)
        val jmlh2: TextView = view.findViewById(R.id.jmlh2)
        val imgEdit: ImageView = view.findViewById(R.id.icn_edit)

        val sppbinsus = anggotaList[position]

        imgEdit.setOnClickListener{
            updateDialog(sppbinsus)
        }

        edt_nm.text = "Nama : " + sppbinsus.edt_nm
        kelas.text = "Kelas : " + sppbinsus.kelas
        nisn.text = "NISN : " + sppbinsus.nisn
        editTextDate.text = "Tanggal Bayar : " + sppbinsus.editTextDate
        bulan.text = "Bulan : " + sppbinsus.bulan
        jmlh2.text = "Jumlah Pembayaran : " + sppbinsus.jmlh2

        return view
    }

    private fun updateDialog(anggota: sppbinsus){
        val builder = AlertDialog.Builder(anggotaContext)
        builder.setTitle("Update Data")
        val inflater = LayoutInflater.from(anggotaContext)
        val view = inflater.inflate(R.layout.activity_update_data, null)

        val edt_nm = view.findViewById<EditText>(R.id.editnama)
        val editkelas = view.findViewById<EditText>(R.id.editkelas)
        val editnisn = view.findViewById<EditText>(R.id.editnisn)
        val editTextDate = view.findViewById<EditText>(R.id.editTextDate)
        val editbulan = view.findViewById<EditText>(R.id.editbulan)
        val jmlh2 = view.findViewById<EditText>(R.id.jmlh2)


        edt_nm.setText(anggota.edt_nm)
        editkelas.setText(anggota.kelas)
        editnisn.setText(anggota.nisn)
        editTextDate.setText(anggota.editTextDate)
        editbulan.setText(anggota.bulan)
        jmlh2.setText(anggota.jmlh2)

        builder.setView(view)

        builder.setPositiveButton("ubah"){ p0, p1 ->
            val dbAnggota = FirebaseDatabase.getInstance().getReference("SPP Binsus")
            val nama = edt_nm.text.toString().trim()
            val kelas = editkelas.text.toString().trim()
            val nisn = editnisn.text.toString()
            val date =editTextDate.text.toString()
            val bulan = editbulan.text.toString()
            val jumlah =jmlh2.text.toString()

            if (nama.isEmpty() or kelas.isEmpty() or nisn.isEmpty() or date.isEmpty() or bulan.isEmpty() or jumlah.isEmpty()) {
                Toast.makeText(anggotaContext, "Isi data secara lengkap tidak boleh kosong",
                    Toast.LENGTH_SHORT)
                    .show()
                return@setPositiveButton
            }
            val anggota = sppbinsus(anggota.id, nama, kelas, nisn, date, bulan, jumlah)

            dbAnggota.child(anggota.id).setValue(anggota)
            Toast.makeText(anggotaContext, "Data berhasil di update", Toast.LENGTH_SHORT)
                .show()
        }

        builder.setNeutralButton("Batal") { p0, p1 -> }

        builder.setNegativeButton("Hapus") { p0, p1 ->
            val dbAnggota = FirebaseDatabase.getInstance().getReference("Spp Binsus")
                .child(anggota.id)
            val dbDetail = FirebaseDatabase.getInstance().getReference("Detail Pembayaran")
                .child(anggota.id)

            dbAnggota.removeValue()
            dbDetail.removeValue()

            Toast.makeText(anggotaContext, "Data berhasil di hapus", Toast.LENGTH_SHORT)
                .show()
        }

        val alert = builder.create()
        alert.show()
    }
}