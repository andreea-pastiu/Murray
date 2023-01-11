package com.example.murray

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
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
        val window = dialog.window

        val width = (resources.displayMetrics.widthPixels * 0.8f).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.7f).toInt()

        window?.setLayout(width, height)
        window?.setGravity(Gravity.CENTER)

        saveButton = dialog.findViewById(R.id.buttonAddToShoppingList)
        cancelButton = dialog.findViewById(R.id.buttonCancelShoppingList)
        newItem = dialog.findViewById(R.id.editTextEnterItem)

        saveButton.setOnClickListener {
            if (newItem.text.isEmpty()) {
                alert()
            } else {
                shoppingList.add(ShoppingListItem(newItem.text.toString()))
                dialog.dismiss()
            }
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun alert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@ViewShoppingListActivity)

        builder.setCancelable(true)
        builder.setTitle("Upsi...")
        builder.setMessage("You left the item field empty")

        builder.setNegativeButton("Ok",
            DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.cancel() })
        builder.show()
    }

    override fun onClick(v: View?) {
        when (v) {
            backButtonImageView -> {
                onBackPressed()
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