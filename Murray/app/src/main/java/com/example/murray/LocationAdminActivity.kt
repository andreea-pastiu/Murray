package com.example.murray

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.adapter.LocationAdapter
import com.example.murray.model.LocationModel

class LocationAdminActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var locationListView: RecyclerView
    private val dataSource = mutableListOf<LocationModel>() as ArrayList<LocationModel>
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var backButtonImageView: ImageView
    private lateinit var addLocationButtonImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDatasource()
        locationAdapter = LocationAdapter(baseContext, dataSource)
        setContentView(R.layout.activity_location_admin)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        locationListView = findViewById(R.id.recylerViewObjectList)
        locationListView.layoutManager = LinearLayoutManager(this)
        locationListView.adapter = locationAdapter
        backButtonImageView = findViewById(R.id.imageViewBackButton)
        addLocationButtonImageView = findViewById(R.id.imageViewAddLocation)
    }

    private fun setListeners() {
        backButtonImageView.setOnClickListener(this)
        addLocationButtonImageView.setOnClickListener(this)
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
            addLocationButtonImageView -> addLocationButton()
        }
    }

    private fun addLocationButton() {
        startActivity(Intent(this, LocationFormActivity::class.java))
    }
}