package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.adapter.LocationAdapter
import com.example.murray.adapter.LocationPatientAdapter
import com.example.murray.model.LocationModel

class LocationPatientActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var locationListView: RecyclerView
    private val dataSource = mutableListOf<LocationModel>() as ArrayList<LocationModel>
    private lateinit var locationAdapter: LocationPatientAdapter
    private lateinit var backButtonImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDatasource()
        locationAdapter = LocationPatientAdapter(baseContext, dataSource)
        setContentView(R.layout.activity_location_patient)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        locationListView = findViewById(R.id.recylerViewObjectList)
        locationListView.layoutManager = GridLayoutManager(this, 2)
        locationListView.adapter = locationAdapter
        backButtonImageView = findViewById(R.id.imageViewBackButton)
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
    }

    private fun initializeDatasource() {
        dataSource.clear()
        for(index in resources.getStringArray(R.array.location_name_array).indices){
            LocationModel(
                resources.getStringArray(R.array.location_name_array)[index],
                resources.getStringArray(R.array.location_address_array)[index]
            ).let{
                dataSource.add(
                    it
                )
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            backButtonImageView -> onBackPressed()
        }
    }
}