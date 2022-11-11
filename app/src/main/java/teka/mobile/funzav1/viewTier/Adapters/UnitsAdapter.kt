package teka.mobile.funzav1.viewTier.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.UnitModel

class UnitsAdapter: RecyclerView.Adapter<UnitsAdapter.MyViewHolder>() {
    //INTERFACE class that defines the click listener
    interface onItemClickListener{
        fun ontItemClick(position: Int)
    }

    lateinit var context: Context
    private var unitsList: MutableList<UnitModel>? = null

    private lateinit var mListener: onItemClickListener

    //setting the on item click listener
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    fun setUnitsList(unitsList:MutableList<UnitModel>, context:Context){
        this.unitsList = unitsList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unit_item, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(unitsList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (unitsList == null) 0
        else unitsList?.size!!
    }

    class MyViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val movieId = view.findViewById<TextView>(R.id.unitId)
        val movieTitle = view.findViewById<TextView>(R.id.unitTitle)

        init {
            itemView.setOnClickListener {
                listener.ontItemClick(adapterPosition)
            }
        }

        fun bind(data:UnitModel){
            movieId.text = data.unitCode
            movieTitle.text = data.unitName
        }

    }

}