package teka.mobile.funzav1.viewTier

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.ChapterModel
import teka.mobile.funzav1.modelTier.models.UnitModel

class ChaptersActivity : AppCompatActivity() {

    lateinit var mDatabaseRef: DatabaseReference
    private lateinit var unitName: String
    lateinit var chaptersList: MutableList<ChapterModel>
    lateinit var recyclerView: RecyclerView
    lateinit var chaptersAdapter: ChaptersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapters)
        unitName = intent.getStringExtra("unitName")!!



    }

    override fun onStart() {
        super.onStart()
        recyclerView = findViewById(R.id.chaptersRView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@ChaptersActivity)
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        mDatabaseRef = database.getReference("root/Documents/Class/Units")

        chaptersList = arrayListOf()
        openChapters(unitName)
    }


    fun openChapters(unitName:String){
        //Toast.makeText(this@ChaptersActivity, unitName+"2", Toast.LENGTH_SHORT).show()
        mDatabaseRef.child(unitName).child("chapters").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val data = snapshot.children
                for (unit in data){
                    val chapterDetails = unit.getValue(ChapterModel::class.java)
                    chaptersList.add(chapterDetails!!)
                }
                chaptersAdapter = ChaptersAdapter()
                chaptersAdapter.setChaptersList(chaptersList)
                recyclerView.adapter = chaptersAdapter

                chaptersAdapter.setOnChapterClickListener(object :ChaptersAdapter.onChapterClickListener{
                  override fun ontItemClick(position: Int) {
                      Toast.makeText(this@ChaptersActivity, chaptersList[position].chapterName, Toast.LENGTH_SHORT).show()
                      val intent = Intent(this@ChaptersActivity, ReadPdfActivity::class.java)
                      startActivity(intent)
                  }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", error.toException())
            }
        })


    }
}