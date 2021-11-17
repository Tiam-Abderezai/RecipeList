package com.example.recipelist.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelist.R
import com.example.recipelist.ui.ListFragmentDirections
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.utils.Globals.Companion.TAG_ADAP_LIST
import kotlinx.android.synthetic.main.recipe_item.view.*


class ListAdapter(
    private val recipes: List<Recipe>
) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipes[position]
        Log.d(TAG_ADAP_LIST, "onBindViewHolder: $currentItem")
        holder.itemView.tv_item_id.text = currentItem.id.toString()
        holder.itemView.tv_item_name.text = currentItem.name
        holder.itemView.tv_item_description.text = currentItem.description
//
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
}