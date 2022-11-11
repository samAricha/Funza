package teka.mobile.funzav1.viewTier.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import teka.mobile.funzav1.databinding.UnitsSearchRowBinding
import teka.mobile.funzav1.modelTier.models.SearchUnitsModel
import teka.mobile.funzav1.modelTier.models.UnitModel

class UnitsSearchAdapter: ListAdapter<UnitModel, UnitsSearchAdapter.MyHolder>(COMPARATOR) {

    private object COMPARATOR: DiffUtil.ItemCallback<UnitModel>(){
        override fun areItemsTheSame(oldItem: UnitModel, newItem: UnitModel): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: UnitModel, newItem: UnitModel): Boolean {
            return oldItem.unitCode == newItem.unitCode
        }

    }
    // An innerclass that maps data with the available views
    inner class MyHolder(private val binding: UnitsSearchRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(unit: UnitModel?) {
            binding.tvCode.text = unit?.unitCode
            binding.tvName.text = unit?.unitName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(UnitsSearchRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val unit = getItem(position)
        holder.bind(unit)
    }

}