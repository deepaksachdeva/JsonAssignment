package com.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.pojo.Row
import com.squareup.picasso.Picasso
import java.util.ArrayList
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor(picasso: Picasso, clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var data: MutableList<Row>? = null
    private var clickListener: ClickListener? = clickListener
    private var picasso: Picasso? = picasso

    init {
        this.picasso = picasso
        data = ArrayList()
    }

    override fun getItemCount(): Int {
        return data?.size!!
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
        holder.txtTitle.text = data!![position].title
        holder.txtDesc.text = data!![position].description

        Picasso.with(holder.imageView.context)
            .load(data!![position].imageHref)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)

        init {
            linearLayout.setOnClickListener {
                clickListener?.launchIntent(
                    data!![adapterPosition].title
                )
            }
        }
    }

    interface ClickListener {
        fun launchIntent(filmName: String)
    }

    fun setData(data: List<Row>) {
        this.data?.addAll(data)
        notifyDataSetChanged()
    }
}
