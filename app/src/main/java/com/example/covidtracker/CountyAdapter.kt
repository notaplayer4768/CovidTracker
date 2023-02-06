



package com.example.covidtracker
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class CountyAdapter(var dataset : List<CountyData>)
:
RecyclerView.Adapter<CountyAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewTitle: TextView
        val textViewDate : TextView
        val textViewCases : TextView
        val layout : ConstraintLayout
        init {
            textViewTitle = view.findViewById(R.id.countyData_textView_title)
            textViewDate = view.findViewById(R.id.countyData_textView_date)
            textViewCases = view.findViewById(R.id.countyData_textView_cases)
            layout = view.findViewById(R.id.countyData_layout)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_county_data, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val countyData = dataset[position]
        viewHolder.textViewTitle.text = countyData.county
        viewHolder.textViewDate.text = countyData.lastUpdatedDate
        viewHolder.textViewCases.text = countyData.metrics.weeklyNewCasesPer100k.toString()
        viewHolder.layout.setOnClickListener{
            Toast.makeText(it.context, countyData.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}