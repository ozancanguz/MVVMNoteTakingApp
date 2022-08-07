package com.ozancanguz.notetakingapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")

class Note(val title:String,val description:String) {

    // id yi constructor a almadık

    @PrimaryKey(autoGenerate = true)
    var id=0




}