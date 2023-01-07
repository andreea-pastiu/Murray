package com.example.murray

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.example.murray.adapters.ContactsAdapter
import com.example.murray.adapters.ContactsPatientAdapter
import com.example.murray.model.Contact

class ContactListPatientActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var contacts_listview: ListView
    private val contactsList = mutableListOf<Contact>() as ArrayList<Contact>
    private lateinit var contactsPatientAdapter: ContactsPatientAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list_patient)

        initializeViews()
        initializeContactsList()
        contactsPatientAdapter = ContactsPatientAdapter(this, contactsList)
        contacts_listview.adapter = contactsPatientAdapter
        setListeners()
    }

    private fun initializeViews() {
        contacts_listview = findViewById(R.id.contacts_listview)
    }

    private fun setListeners() {
        contacts_listview.setOnItemClickListener(this)
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val intent = Intent ( this, ContactPageActivity::class.java )
        intent.putExtra("name", contactsList[position].name)
        intent.putExtra("phone", contactsList[position].phone)
        intent.putExtra("address", contactsList[position].address)
        intent.putExtra("relationship", contactsList[position].relationship)
        intent.putExtra("index", position)
        startActivity(intent)
    }
}