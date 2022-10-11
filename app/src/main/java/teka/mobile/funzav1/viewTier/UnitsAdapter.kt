package teka.mobile.funzav1.viewTier

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.UnitModel

class UnitsAdapter: RecyclerView.Adapter<UnitsAdapter.MyViewHolder>() {

    lateinit var context: Context
    private var unitsList: MutableList<UnitModel>? = null
    fun setUnitsList(unitsList:MutableList<UnitModel>, context:Context){
        this.unitsList = unitsList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.unit_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(unitsList?.get(position)!!)
        holder.itemView.setOnClickListener{
            Toast.makeText(context, unitsList!![position].unitName, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return if (unitsList == null) 0
        else unitsList?.size!!
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val movieId = view.findViewById<TextView>(R.id.unitId)
        val movieTitle = view.findViewById<TextView>(R.id.unitTitle)
        fun bind(data:UnitModel){
            movieId.text = data.unitCode
            movieTitle.text = data.unitName
        }

    }

}