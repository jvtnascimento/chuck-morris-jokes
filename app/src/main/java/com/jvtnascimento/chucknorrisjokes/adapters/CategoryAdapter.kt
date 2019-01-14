package com.jvtnascimento.chucknorrisjokes.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jvtnascimento.chucknorrisjokes.R
import com.jvtnascimento.chucknorrisjokes.activities.JokeActivity
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = items[position]

        holder.itemName.text = category.capitalize()

        holder.itemView.setOnClickListener {
            val intent = Intent( context, JokeActivity::class.java)
            intent.putExtra("category", category)
            context.startActivity(intent)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val itemName = view.itemName!!
}