package com.example.catat.feature_note.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import com.example.catat.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val hour = intent.getIntExtra("hour", -1)
        Toast.makeText(context, "Sekarang Jam $hour triggered", Toast.LENGTH_SHORT).show()

        val mediaPlayer = MediaPlayer.create(context, R.raw.alarmku)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
        }
    }
}
