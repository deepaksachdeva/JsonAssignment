package com.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.pojo.Row
import java.util.ArrayList
import javax.inject.Inject

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private var data: MutableList<Row>
    private var clickListener: ClickListener

    @Inject
    constructor(clickListener: ClickListener){
        this.clickListener = clickListener
        data = ArrayList()
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_list_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.setText(data[position].title)
        holder.txtBirthYear.setText(data[position].description)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView
        val txtBirthYear: TextView
        val linearLayout: LinearLayout

        init {
            txtName = itemView.findViewById(R.id.txtName)
            txtBirthYear = itemView.findViewById(R.id.txtBirthYear)
            linearLayout = itemView.findViewById(R.id.constraintLayout)

            linearLayout.setOnClickListener {
                clickListener.launchIntent(
                    data[adapterPosition].title
                )
            }
        }
    }

    interface ClickListener {
        fun launchIntent(filmName: String)
    }

    fun setData(data: List<Row>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
