package com.ozancanguz.notetakingapplication.view

import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozancanguz.notetakingapplication.Model.Note
import com.ozancanguz.notetakingapplication.NoteApplication
import com.ozancanguz.notetakingapplication.R
import com.ozancanguz.notetakingapplication.ViewModel.NoteViewModel
import com.ozancanguz.notetakingapplication.ViewModel.NoteViewModelFactor
import com.ozancanguz.notetakingapplication.adapters.NoteAdapter

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel


    private lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        registerActivityResultLauncher()

        val recyclerView:RecyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val noteAdapter = NoteAdapter()
        recyclerView.adapter=noteAdapter


               val viewModelfactory=NoteViewModelFactor((application as NoteApplication).repository)
                 noteViewModel=ViewModelProvider(this,viewModelfactory).get(NoteViewModel::class.java)
        noteViewModel.myAllNotes.observe(this, Observer {notes->

            // update UI

            noteAdapter.setNote(notes)
        }

            )

        // for delete
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0
            ,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))

            }

        }).attachToRecyclerView(recyclerView)

    }



    fun registerActivityResultLauncher(){

        addActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
            , ActivityResultCallback { resultAddNote ->

                val resultCode = resultAddNote.resultCode
                val data = resultAddNote.data

                if (resultCode == RESULT_OK && data != null){

                    val noteTitle : String = data.getStringExtra("title").toString()
                    val noteDescription : String = data.getStringExtra("description").toString()

                    val note = Note(noteTitle,noteDescription)
                    noteViewModel.insert(note)

                }

            })}
  override  fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.new_menu,menu)
        return true

    }


      override fun onOptionsItemSelected(item: MenuItem): Boolean {

             when(item.itemId){
                 R.id.item_add_note -> {

                     val intent= Intent(this,NoteAddActivity::class.java)
                     addActivityResultLauncher.launch(intent)
                 }

                 R.id.item_delete -> showDialogMessage()
             }
        return true
    }
    fun showDialogMessage(){

        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Delete All Notes")
        dialogMessage.setMessage("If click Yes all notes will delete" +
                ", if you want to delete a specific note, please swipe left or right.")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

            dialog.cancel()

        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

            noteViewModel.deleteAllNotes()

        })

        dialogMessage.create().show()





}}