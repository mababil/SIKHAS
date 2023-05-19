package com.example.project
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_reguler.*

class RegulerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editnama: EditText
    private lateinit var editkelas: EditText
    private lateinit var editnisn: EditText
    private lateinit var editdate: EditText
    private lateinit var editbulan: EditText
    private lateinit var editjumlah: EditText
    private lateinit var btnSend: Button
    private lateinit var listData: ListView
    private lateinit var ref: DatabaseReference

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channerName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reguler)
        buatNotifikasi()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)}
        val notificatin = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Data Masuk")
            .setContentText("Data Siswa berhasil masuk")
            .setSmallIcon(R.drawable.ic_notif)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notifikasiManager = NotificationManagerCompat.from(this)
        button5.setOnClickListener {
            notifikasiManager.notify(NOTIFICATION_ID, notificatin)
        }


        ref = FirebaseDatabase.getInstance().getReference("sppreguler")
        editnama = findViewById(R.id.editnama)
        editkelas = findViewById(R.id.editkelas)
        editnisn = findViewById(R.id.editnisn)
        editdate = findViewById(R.id.editTextDate)
        editbulan = findViewById(R.id.editbulan)
        editjumlah = findViewById(R.id.jmlh2)
        btnSend = findViewById(R.id.button5)
        btnSend.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        saveData()
    }


    fun buatNotifikasi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES .O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
                .apply {
                    lightColor = Color.GREEN
                    enableLights(true)
                }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
    private fun saveData(){
        val nama = editnama.text.toString().trim()
        val kelas = editkelas.text.toString().trim()
        val nisn = editnisn.text.toString().trim()
        val date = editdate.text.toString().trim()
        val bulan = editbulan.text.toString().trim()
        val jumlah = editjumlah.text.toString().trim()

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
            editdate.error = "Isi Tanggal Pembayaran !!!"
            return
        }

        if(bulan.isEmpty()){
            editbulan.error = "Isi Bulan !!!"
            return
        }
        if(jumlah.isEmpty()){
            editjumlah.error = "Isi Jumlah Pembayaran !!!"
            return
        }


        val dataId = ref.push().key

        val data = sppreguler(dataId!!,nama,kelas,nisn,date,bulan,jumlah)

        if (dataId != null){
            ref.child(dataId).setValue(data).addOnCompleteListener{
                val i=Intent(applicationContext,lapor::class.java)
                startActivity(i)

            }
        }
    }



}