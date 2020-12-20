package android.example.watermanager.ui.history

import android.content.Context
import android.content.res.Resources
import android.example.watermanager.R
import android.example.watermanager.data.Bottle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.history_row.view.*

class HistoryAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    var historyList: List<Bottle> = listOf()

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.history_row, parent, false)
        return CustomViewHolder(
            cellForRow
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    fun setHistoryData(historyList: List<Bottle>){
        this.historyList = historyList
        notifyDataSetChanged()
    }
}

class CustomViewHolder(val v: View): RecyclerView.ViewHolder(v) {
    fun bind(bottle: Bottle){
        if (bottle.amount.contains("-1")) {
            itemView.textView_amount.setTextColor(v.resources.getColor(android.R.color.holo_red_dark))
        } else{
            itemView.textView_amount.setTextColor(v.resources.getColor(android.R.color.holo_green_light))
        }
        itemView.textView_date_and_time.text = bottle.date
        itemView.textView_amount.text = bottle.amount
    }
}