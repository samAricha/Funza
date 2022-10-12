package teka.mobile.funzav1.viewTier

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.ChapterModel
import teka.mobile.funzav1.modelTier.models.UnitModel


class NotesActivity : AppCompatActivity() {

    lateinit var mDatabaseRef: DatabaseReference
    lateinit var unitsList: MutableList<UnitModel>
   lateinit var chaptersList: MutableList<ChapterModel>
    lateinit var recyclerView: RecyclerView
    lateinit var unitsAdapter: UnitsAdapter
    lateinit var chaptersAdapter: ChaptersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

    }

    override fun onStart() {
        super.onStart()

        recyclerView = findViewById(R.id.rView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@NotesActivity)
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        mDatabaseRef = database.getReference("root/Documents/Class/Units")

        unitsList = arrayListOf()
        //chaptersList = arrayListOf()

        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.children
                for (unit in data){
                    var unitDetails = unit.getValue(UnitModel::class.java)
                    unitsList.add(unitDetails!!)
                }
                unitsAdapter = UnitsAdapter()
                unitsAdapter.setUnitsList(unitsList, this@NotesActivity)
                recyclerView.adapter = unitsAdapter
                unitsAdapter.setOnItemClickListener(object: UnitsAdapter.onItemClickListener{
                    override fun ontItemClick(position: Int) {
                        //chaptersList = unitsList[position].chapters as MutableList<ChapterModel>
                        Toast.makeText(this@NotesActivity, unitsList[position].unitName, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@NotesActivity, ReadPdfActivity::class.java)
                        startActivity(intent)
                        //openChapters()
                    }

                })

            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException())
            }
        })

    }

    fun openChapters(){
        chaptersAdapter = ChaptersAdapter()
        chaptersAdapter.setChaptersList(chaptersList)
        recyclerView.adapter = chaptersAdapter

        chaptersAdapter.setOnChapterClickListener(object :ChaptersAdapter.onChapterClickListener{
            override fun ontItemClick(position: Int) {
                Toast.makeText(this@NotesActivity, chaptersList[position].chapterName, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@NotesActivity, ReadPdfActivity::class.java)
                startActivity(intent)
            }
        })
    }
}