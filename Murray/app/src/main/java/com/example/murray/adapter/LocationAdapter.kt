package com.example.murray.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.murray.LocationFormActivity
import com.example.murray.QuizFormActivity
import com.example.murray.R
import com.example.murray.model.LocationModel
import com.example.murray.model.QuestionModel

class LocationAdapter(private val context: Context, private val dataSet: ArrayList<LocationModel>) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val locationNameTextView: TextView
        private val questionNumberTextView: TextView
        private val locationAddressTextView: TextView
        private val locationEditImageView: ImageView

        init {
            locationNameTextView = view.findViewById(R.id.textViewQuestionTitle)
            questionNumberTextView = view.findViewById(R.id.textViewQuestionNumber)
            locationAddressTextView = view.findViewById(R.id.textViewQuestionText)
            locationEditImageView = view.findViewById(R.id.imageViewEditButton)
            locationEditImageView.setOnClickListener(this)
        }

        fun bindData(locationModel: LocationModel) {
            locationNameTextView.text = locationModel.LocationName
            questionNumberTextView.text = ""
            locationAddressTextView.text = locationModel.LocationAddress
        }

        override fun onClick(p0: View?) {
            val intent = Intent (p0?.context, LocationFormActivity::class.java )
            intent.putExtra("location_name", locationNameTextView.text)
            intent.putExtra("location_address", locationAddressTextView.text)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = inflater.inflate(R.layout.quiz_question_item_layout, parent, false)

        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}