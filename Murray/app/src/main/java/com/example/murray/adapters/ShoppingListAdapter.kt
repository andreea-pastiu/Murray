package com.example.murray.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.murray.R
import com.example.murray.model.ShoppingListItem

class ShoppingListAdapter(private val context: Context, private val dataSource: ArrayList<ShoppingListItem>): BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItem(p0: Int): ShoppingListItem {
        return dataSource[p0];
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = p1 ?: inflater.inflate(
            R.layout.list_shopping_item, p2, false
        )
        rowView.findViewById<TextView>(R.id.textViewShoppingItem).text = dataSource[p0].name
        val button = rowView.findViewById<ImageView>(R.id.imageViewDelete)
        button.setOnClickListener {
            dataSource.removeAt(p0)
            notifyDataSetChanged()
        }
        return rowView;
    }
}