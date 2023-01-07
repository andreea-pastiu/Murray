package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.adapters.ActivitiesAdapter
import com.example.murray.adapters.ShoppingListAdapter
import com.example.murray.model.Activity
import com.example.murray.model.ShoppingListItem

class ViewShoppingListActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var backButtonImageView: ImageView
    private lateinit var addShoppingListItemButton: Button
    private lateinit var accountType: String
    private lateinit var listView: ListView
    private lateinit var shoppingAdapter: ShoppingListAdapter
    private var shoppingList = mutableListOf<ShoppingListItem>() as ArrayList<ShoppingListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_list)
        initializeViews()
        setListeners()
        setListView()
    }

    private fun initializeViews() {
        addShoppingListItemButton = findViewById(R.id.imageViewAddShoppingItemButton)
        listView = findViewById(R.id.listViewActivities)
        backButtonImageView = findViewById(R.id.imageViewBackButton)

    }

    private fun setListeners() {
        addShoppingListItemButton.setOnClickListener(this)
        backButtonImageView.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageViewBackButton -> {

            }
            R.id.imageViewAddActivityButton -> {
                //dialog box
            }
        }
    }

    private fun setListView() {
        shoppingAdapter = ShoppingListAdapter(this, shoppingList)
        listView.adapter = shoppingAdapter
    }
}