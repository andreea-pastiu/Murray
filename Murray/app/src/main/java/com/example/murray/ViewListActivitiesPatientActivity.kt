package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.murray.adapters.ActivitiesAdapter
import com.example.murray.adapters.ActivitiesPatientAdapter
import com.example.murray.model.Activity

class ViewListActivitiesPatientActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var backButtonImageView: ImageView
    private lateinit var accountType: String
    private lateinit var listView: ListView
    private lateinit var activitiesAdapter: ActivitiesPatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities_list_patient)
        accountType = intent.getStringExtra("type").toString()
        initializeViews()
        setListeners()
        setListView()
    }

    private fun initializeViews() {
        listView = findViewById(R.id.listViewActivities)
        backButtonImageView = findViewById(R.id.imageViewBackButton)

    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            backButtonImageView -> {

            }
        }
    }

    private fun setListView() {
        activitiesAdapter = ActivitiesPatientAdapter(this, activitiesList)
        listView.adapter = activitiesAdapter
        listView.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val intent = Intent(this@ViewListActivitiesPatientActivity, ViewActivityActivity::class.java)
                intent.putExtra("activity_name",activitiesList[p2].name)
                intent.putExtra("activity_description",activitiesList[p2].details)
                intent.putExtra("activity_time",activitiesList[p2].time)
                intent.putExtra("activity_date", activitiesList[p2].date)
                intent.putExtra("activity_r", activitiesList[p2].recurrence as ArrayList<Int>)
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