package com.example.murray

import android.app.Activity.RESULT_OK
import android.app.appsearch.AppSearchResult.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import com.example.murray.adapters.ActivitiesAdapter
import com.example.murray.model.Activity

class ViewListActivitiesActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var backButtonImageView: ImageView
    private lateinit var addActivityButton: ImageView
    private lateinit var accountType: String
    private lateinit var listView: ListView
    private lateinit var activitiesAdapter: ActivitiesAdapter
    private var activitiesList = mutableListOf<Activity>() as ArrayList<Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_list)
        accountType = intent.getStringExtra("type").toString()
        initializeViews()
        setListeners()
        setListView()
    }

    private fun initializeViews() {
        addActivityButton = findViewById(R.id.imageViewAddActivityButton)
        listView = findViewById(R.id.listViewActivities)
        backButtonImageView = findViewById(R.id.imageViewBackButton)

    }

    private fun setListeners() {
        addActivityButton.setOnClickListener(this)
        backButtonImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageViewBackButton -> {

            }
            R.id.imageViewAddActivityButton -> {
                val intent = Intent(this, AddActivityActivity::class.java)
                intent.putExtra("activities", activitiesList)
                startActivityForResult(intent, 1)
            }
        }
    }

    private fun setListView() {
        activitiesAdapter = ActivitiesAdapter(this, activitiesList)
        listView.adapter = activitiesAdapter
        listView.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val intent = Intent(this@ViewListActivitiesActivity, ViewActivityActivity::class.java)
                intent.putExtra("activity_name",activitiesList[p2].name)
                intent.putExtra("activity_description",activitiesList[p2].details)
                intent.putExtra("activity_time",activitiesList[p2].time)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 1) {
            val stringData = data?.extras?.get("activities") as java.util.ArrayList<Activity>
            activitiesList = stringData
            setListView()
        }
    }
}