package teka.mobile.funzav1.viewTier.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.google.firebase.database.*
import teka.mobile.funzav1.R
import teka.mobile.funzav1.databinding.ActivitySearchBinding
import teka.mobile.funzav1.modelTier.models.SearchUnitsModel
import teka.mobile.funzav1.modelTier.models.UnitModel
import teka.mobile.funzav1.viewTier.Adapters.UnitsSearchAdapter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    lateinit var mDatabaseRef: DatabaseReference

    private val searchAdapter: UnitsSearchAdapter by lazy {
        UnitsSearchAdapter()
    }
    lateinit var unitsList: MutableList<UnitModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initializing databaseReference
        val database = FirebaseDatabase.getInstance()
        mDatabaseRef = database.getReference("root/Documents/Class/Units/")

        unitsList = arrayListOf()


        binding.unitsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadUnits(query!!)
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return false
            }
        })

    }

    private fun loadUnits(name: String) {
        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Checking if the value exists
                if(snapshot.exists()){
                    unitsList.clear()
                    // looping through to values
                    for(i in snapshot.children){
                        val unit = i.getValue(UnitModel::class.java)
                        // checking if the name searched is available and adding to the array list
                        if (unit!!.unitName == name){
                            unitsList.add(unit)
                        }

                    }
                    //setting data to recyclerview
                    searchAdapter.submitList(unitsList)
                    binding.recyclerUnits.adapter = searchAdapter
                } else{
                    Toast.makeText(applicationContext, "Data does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    // filter by specified value
    private  fun filter(e: String) {
        //Declaring the array list that holds the filtered values
        val filteredItem = ArrayList<UnitModel>()
        // looping through the array list to obtain the required value
        for (item in unitsList) {
            if (item.unitName!!.toLowerCase().contains(e.toLowerCase())) {
                filteredItem.add(item)
            }
        }
        // adding the filted value to adapter
        searchAdapter.submitList(filteredItem)
    }
}