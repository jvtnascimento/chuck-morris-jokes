package com.jvtnascimento.chucknorrisjokes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jvtnascimento.chucknorrisjokes.R

class CategoryAdapter(
    context: Context,
    private val dataSource: ArrayList<String>) : BaseAdapter(){

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.item_category, parent, false)
        val itemName = view.findViewById(R.id.itemName) as TextView

        val category = getItem(position) as String
        itemName.text = category

        return view
    }
}