package com.example.githubrepo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepo.R
import com.example.githubrepo.data.Items

class RecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ReposViewHolder>() {

    private var items = ArrayList<Items>()

    fun setData(items: ArrayList<Items>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ReposViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val projectNameTv: TextView = view.findViewById(R.id.project_name_tv)
        private val starsCountTv: TextView = view.findViewById(R.id.stars_count_tv)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(data: Items) {
            projectNameTv.text = data.name
            starsCountTv.text = data.stargazers_count.toString()
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            listener.onItemClick(items, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_list_row, parent, false)
        return ReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener {
        fun onItemClick(items: ArrayList<Items>, position: Int)
    }
}