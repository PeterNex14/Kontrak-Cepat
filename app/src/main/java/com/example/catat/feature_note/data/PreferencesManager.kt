package com.example.catat.feature_note.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "notes_prefs", Context.MODE_PRIVATE
    )

    fun saveNote(noteId: String, noteContent: String) {
        prefs.edit().putString(noteId, noteContent).apply()
    }

    fun getNoteContent(noteId: String): String? {
        return prefs.getString(noteId, null)
    }

    fun deleteNoteContent(noteId: String) {
        prefs.edit().remove(noteId).apply()
    }
}
