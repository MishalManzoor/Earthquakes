package com.earth1.earthquakes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.earth1.earthquakes.helperClass.properties
import com.earth1.earthquakes.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class AdapterClass(
    private var context: Context,
    private var mList: List<properties>,
    private var listener : OnClick
) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var magnitude: TextView = itemView.findViewById(R.id.mag)
        var place: TextView = itemView.findViewById(R.id.place)
        var date: TextView = itemView.findViewById(R.id.date)
        var time: TextView = itemView.findViewById(R.id.time)
        var card : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.design_items, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val view = mList[position]
        val list = view.properties

        holder.date.text = list.time.toString()
        holder.place.text = list.place

        // set Date
        val date = Date(list.time)
        val formatDate = formatDate(date)
        holder.date.text = formatDate

        // set time
        val formatTime = formatTime(date)
        holder.time.text = formatTime

        // set magnitude
        val formatMag = formatMagnitude(list.mag)
        holder.magnitude.text = formatMag

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        val magnitudeCircle: GradientDrawable = holder.magnitude.background as GradientDrawable

        // Get the appropriate background color based on the current earthquake magnitude
        val magnitudeColor = getMagnitudeColor(list.mag)

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor)

        holder.card.setOnClickListener {
            listener.onClickListener(list.url)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    interface OnClick{
        fun onClickListener(url : String)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(dateObject: Date): String {
        val formatDate = SimpleDateFormat("LLL dd, yyyy")
        return formatDate.format(dateObject)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatTime(dateObject: Date): String {
        val formatTime = SimpleDateFormat("h:mm a")
        return formatTime.format(dateObject)
    }

    private fun formatMagnitude(mag: Double): String {
        val magnitude = DecimalFormat("0.0")
        return magnitude.format(mag)
    }

    // color for the magnitude circle
    // based on the intensity of the earthquake.
    private fun getMagnitudeColor(mag: Double): Int {
        var magnitudeColor = 0
        when (floor(mag).toInt()) {
            0 -> {}

            1 -> {
                magnitudeColor = R.color.magnitude1
            }

            2 -> {
                magnitudeColor = R.color.magnitude2
            }

            3 -> {
                magnitudeColor = R.color.magnitude3
            }

            4 -> {
                magnitudeColor = R.color.magnitude4
            }

            5 -> {
                magnitudeColor = R.color.magnitude5
            }

            6 -> {
                magnitudeColor = R.color.magnitude6
            }

            7 -> {
                magnitudeColor = R.color.magnitude7
            }

            8 -> {
                magnitudeColor = R.color.magnitude8
            }

            9 -> {
                magnitudeColor = R.color.magnitude9
            }

            else -> {
                magnitudeColor = R.color.magnitude10plus
            }
        }

        return ContextCompat.getColor(context, magnitudeColor)
    }
}