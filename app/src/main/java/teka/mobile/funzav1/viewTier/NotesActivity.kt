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
    lateinit var recyclerView: RecyclerView
    lateinit var unitsAdapter: UnitsAdapter

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
        mDatabaseRef = database.getReference("root/Documents/Class/Units/")

        unitsList = arrayListOf()
        openUnits()
    }

    fun openUnits(){
        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.children
                for (unit in data){
                    val unitDetails = unit.getValue(UnitModel::class.java)
                    unitsList.add(unitDetails!!)
                }
                unitsAdapter = UnitsAdapter()
                unitsAdapter.setUnitsList(unitsList, this@NotesActivity)
                recyclerView.adapter = unitsAdapter
                unitsAdapter.setOnItemClickListener(object: UnitsAdapter.onItemClickListener{
                    override fun ontItemClick(position: Int) {
                        Toast.makeText(this@NotesActivity, unitsList[position].unitName, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@NotesActivity, ChaptersActivity::class.java)
                        intent.putExtra("unitName",unitsList[position].unitName)
                        startActivity(intent)
                    }

                })

            }

            override fun onCancelled(error: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException())
            }
        })
    }


}