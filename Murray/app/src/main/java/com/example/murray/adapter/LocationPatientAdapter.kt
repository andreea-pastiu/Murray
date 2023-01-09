package com.example.murray.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.LocationFormActivity
import com.example.murray.LocationPatientFormActivity
import com.example.murray.R
import com.example.murray.model.LocationModel

class LocationPatientAdapter(private val context: Context, private val dataSet: ArrayList<LocationModel>) : RecyclerView.Adapter<LocationPatientAdapter.LocationPatientViewHolder>() {

    class LocationPatientViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val locationNameTextView: TextView
        private lateinit var locationAddress: String

        init {
            locationNameTextView = view.findViewById(R.id.textViewLocationName)
            view.setOnClickListener(this)
        }

        fun bindData(locationModel: LocationModel) {
            locationNameTextView.text = locationModel.LocationName
            locationAddress = locationModel.LocationAddress
        }

        override fun onClick(p0: View?) {
            val intent = Intent (p0?.context, LocationPatientFormActivity::class.java )
            intent.putExtra("location_name", locationNameTextView.text)
            intent.putExtra("location_address", locationAddress)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationPatientViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.location_item_layout, parent, false)

        return LocationPatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationPatientViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}