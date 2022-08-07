package com.ozancanguz.notetakingapplication

import android.app.Application
import com.ozancanguz.notetakingapplication.Repository.NoteRepository
import com.ozancanguz.notetakingapplication.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.supervisorScope

class NoteApplication:Application() {


    val applicationScope= CoroutineScope(SupervisorJob())

    // we created instance of from 2 class
    val database by lazy {NoteDatabase.getDatabase(this,applicationScope)}

    val repository by lazy { NoteRepository(database.getNoteDao()) }



}