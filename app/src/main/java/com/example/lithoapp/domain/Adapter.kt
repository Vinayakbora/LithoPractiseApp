package com.example.lithoapp.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lithoapp.R
import com.example.lithoapp.repository.PersonalizationSequence

class Adapter(private val postList: List<PersonalizationSequence>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.post_item_layout,
            parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text= postList[position].widgetId
        holder.txtWidgetName.text = postList[position].widgetName
        holder.txtUrl.text = postList[position].jsonURL
        holder.txtPriority.text = postList[position].priority
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.txtWidgetId)
        val txtWidgetName: TextView = view.findViewById(R.id.txtWidgetName)
        val txtUrl: TextView = view.findViewById(R.id.txtUrl)
        val txtPriority: TextView = view.findViewById(R.id.txtPriority)
    }
}