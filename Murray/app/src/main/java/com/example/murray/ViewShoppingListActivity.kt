package com.example.murray

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.adapters.ShoppingListAdapter
import com.example.murray.model.ShoppingListItem

class ViewShoppingListActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var backButtonImageView: ImageView
    private lateinit var addShoppingListItemButton: ImageView
    private lateinit var listView: ListView
    private lateinit var shoppingAdapter: ShoppingListAdapter
    private var shoppingList = mutableListOf<ShoppingListItem>() as ArrayList<ShoppingListItem>
    private lateinit var cancelButton: Button
    private lateinit var saveButton: Button
    private lateinit var newItem: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        initializeViews()
        setListeners()
        setListView()
    }

    private fun initializeViews() {
        addShoppingListItemButton = findViewById(R.id.imageViewAddShoppingItemButton)
        listView = findViewById(R.id.listViewShopping)
        backButtonImageView = findViewById(R.id.imageViewBackButton)

    }

    private fun setListeners() {
        addShoppingListItemButton.setOnClickListener(this)
        backButtonImageView.setOnClickListener(this)

    }

    private fun showDialog() {
        val dialog = Dialog(this)
//        val window = dialog.window
//        val layoutParams = WindowManager.LayoutParams()
//        layoutParams.copyFrom(window?.attributes)
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        window?.attributes = layoutParams
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_custom_add_shopping_item)

        saveButton = dialog.findViewById(R.id.buttonAddToShoppingList)
        cancelButton = dialog.findViewById(R.id.buttonCancelShoppingList)
        newItem = dialog.findViewById(R.id.editTextEnterItem)

        saveButton.setOnClickListener {
            shoppingList.add(ShoppingListItem(newItem.text.toString()))
            dialog.dismiss()
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onClick(v: View?) {
        when (v) {
            backButtonImageView -> {

            }
            addShoppingListItemButton -> {
                showDialog()
            }
        }
    }

    private fun setListView() {
        shoppingAdapter = ShoppingListAdapter(this, shoppingList)
        listView.adapter = shoppingAdapter
    }
}