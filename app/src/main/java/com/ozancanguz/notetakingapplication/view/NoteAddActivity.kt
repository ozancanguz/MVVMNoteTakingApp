package com.ozancanguz.notetakingapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ozancanguz.notetakingapplication.R

class NoteAddActivity : AppCompatActivity() {



    lateinit var editTextTitle:EditText
    lateinit var editTextDescription:EditText
    lateinit var buttonCancel:Button
    lateinit var buttonSave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        supportActionBar?.title="Add note"

        editTextTitle=findViewById(R.id.editTextNoteTitle)
        editTextDescription=findViewById(R.id.editTextNoteDescription)
        buttonCancel=findViewById(R.id.buttonCancel)
        buttonSave=findViewById(R.id.buttonSave)

        buttonCancel.setOnClickListener {

            Toast.makeText(this,"nothing saved",Toast.LENGTH_LONG).show()
            finish()

        }

        buttonSave.setOnClickListener {

            saveNote()
        }


    }

    fun saveNote() {
       val noteTitle=editTextTitle.text.toString()
        val noteDescription=editTextDescription.text.toString()

        val intent= Intent()
        intent.putExtra("title",noteTitle)
        intent.putExtra("description",noteDescription)
        setResult(RESULT_OK,intent)
        finish()

    }


}