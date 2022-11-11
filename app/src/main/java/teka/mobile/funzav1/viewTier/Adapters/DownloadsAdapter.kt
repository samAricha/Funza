package teka.mobile.funzav1.viewTier.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import teka.mobile.funzav1.R
import teka.mobile.funzav1.modelTier.models.PdfModel

class DownloadsAdapter: RecyclerView.Adapter<DownloadsAdapter.MyViewHolder>() {

    //INTERFACE class that defines the click listener
    interface onDownloadClickListener{
        fun ontItemClick(position: Int)
    }

    private lateinit var mListener: ChaptersAdapter.onChapterClickListener
    private var pdfList: List<PdfModel>? = null


    //setting the on item click listener
    fun setOnDownloadClickListener(listener: ChaptersAdapter.onChapterClickListener){
        mListener = listener
    }

    //setting the list of chapters per unit
    fun setDownloadList(downloadList: List<PdfModel>){
        this.pdfList = downloadList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.download_item, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(pdfList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        return if (pdfList == null) 0
        else pdfList?.size!!
    }



    /*
       * VIEWHOLDER CLASS
       * */
    class MyViewHolder(view: View, listener: ChaptersAdapter.onChapterClickListener):RecyclerView.ViewHolder(view){

        val pdfName = view.findViewById<TextView>(R.id.pdf_name)

        init {
            itemView.setOnClickListener {
                listener.ontItemClick(adapterPosition)
            }
        }

        fun bind(data: PdfModel){
            pdfName.text = data.pdfName
        }
    }

}