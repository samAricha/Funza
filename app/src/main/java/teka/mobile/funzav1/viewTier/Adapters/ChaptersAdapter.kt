package teka.mobile.funzav1.viewTier.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.ChapterModel

class ChaptersAdapter: RecyclerView.Adapter<ChaptersAdapter.MyViewHolder>() {
    //INTERFACE class that defines the click listener
    interface onChapterClickListener{
        fun ontItemClick(position: Int)
    }

    private var chaptersList: MutableList<ChapterModel>? = null
    private lateinit var mListener: onChapterClickListener



    //setting the on item click listener
    fun setOnChapterClickListener(listener: onChapterClickListener){
        mListener = listener
    }
    //setting the list of chapters per unit
    fun setChaptersList(chaptersList: MutableList<ChapterModel>){
        this.chaptersList = chaptersList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chapter_item, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(chaptersList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (chaptersList == null) 0
        else chaptersList?.size!!
    }


    /*
    * VIEWHOLDER CLASS
    * */
    class MyViewHolder(view: View, listener: onChapterClickListener):RecyclerView.ViewHolder(view) {

        val chapterName = view.findViewById<TextView>(R.id.chapterTitle)

        init {
            itemView.setOnClickListener {
                listener.ontItemClick(adapterPosition)
            }
        }

        fun bind(data: ChapterModel){
            chapterName.text = data.chapterName
        }

    }
}