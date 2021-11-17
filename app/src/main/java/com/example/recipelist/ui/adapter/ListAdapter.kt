package com.example.recipelist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelist.R
import com.example.recipelist.ui.ListFragmentDirections
import com.example.recipelist.data.model.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var RecipeList = emptyList<Recipe>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = RecipeList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_txt.text = currentItem.lastName
        holder.itemView.age_txt.text = currentItem.age.toString()

//        holder.itemView.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    fun setData(Recipe: List<Recipe>) {
        this.RecipeList = Recipe
        notifyDataSetChanged()
    }
}