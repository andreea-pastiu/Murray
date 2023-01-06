package com.example.murray

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.example.murray.adapters.ContactsAdapter
import com.example.murray.adapters.MedicinesAdapter
import com.example.murray.model.Contact
import com.example.murray.model.Medicine

class ContactListCaregiverActivity : AppCompatActivity(), AdapterView.OnItemClickListener, View.OnClickListener {
    private lateinit var contacts_listview: ListView
    private lateinit var buttonAddContact: Button
    private val contactsList = mutableListOf<Contact>() as ArrayList<Contact>
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list_caregiver)

        initializeViews()
        initializeContactsList()
        contactsAdapter = ContactsAdapter(this, contactsList)
        contacts_listview.adapter = contactsAdapter
        setListeners()
    }

    private fun initializeViews() {
        contacts_listview = findViewById(R.id.contacts_listview)
        buttonAddContact = findViewById(R.id.buttonAddContact)
    }

    private fun setListeners() {
        contacts_listview.setOnItemClickListener(this)
        buttonAddContact.setOnClickListener(this)
    }

    private fun initializeContactsList() {
        contactsList.clear()
        for (index in resources.getStringArray(R.array.contact_names).indices) {
            getDrawable(
                resources.obtainTypedArray(R.array.contact_images).getResourceId(index, 0)
            )?.let {
                Contact(
                    resources.getStringArray(R.array.contact_names)[index],
                    resources.getStringArray(R.array.contact_phones)[index],
                    resources.getStringArray(R.array.contact_addresses)[index],
                    resources.getStringArray(R.array.contact_relationships)[index],
                    it
                )
            }?.let {
                contactsList.add(
                    it
                )
            }
        }
    }

    override fun onClick(view: View?) {
        val intent = Intent ( this, AddContactActivity::class.java )
        startActivity(intent)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent ( this, AddContactActivity::class.java )
        intent.putExtra("edit", true)
        intent.putExtra("name", contactsList[position].name)
        intent.putExtra("phone", contactsList[position].phone)
        intent.putExtra("address", contactsList[position].address)
        intent.putExtra("relationship", contactsList[position].relationship)
        intent.putExtra("index", position)
        startActivity(intent)
    }
}