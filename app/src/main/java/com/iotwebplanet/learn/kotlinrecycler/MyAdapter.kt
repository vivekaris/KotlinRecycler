package com.iotwebplanet.learn.kotlinrecycler

/**
 * Created by developer on 20/06/17.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class MyAdapter(val hostelList: ArrayList<Hostel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bindItems(hostelList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return hostelList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(hostel: Hostel) {
            val hname = itemView.findViewById(R.id.textViewHostelname) as TextView
            val hclass  = itemView.findViewById(R.id.textViewClass) as TextView

            val attan  = itemView.findViewById(R.id.attandant) as TextView
            hname.text = hostel.hostel_name
            hclass.text = hostel.hostel_description
            attan.text = hostel.hostel_attandent


            hname.setOnClickListener {
                Toast.makeText(itemView.context, "Position: " + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
            }
        }
    }
}