package com.example.recipelist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipelist.ui.adapter.ListAdapter
import com.example.recipelist.viewmodel.RecipeViewModel
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.databinding.FragmentListBinding
import kotlinx.coroutines.flow.map

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.apply {
            adapter = ListAdapter()
            layoutManager = LinearLayoutManager(requireContext())
        }
        // RecipeViewModel
//        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
//        mRecipeViewModel.readAllData.observe(viewLifecycleOwner, Observer { Recipe ->
//            adapter.setData(Recipe)
//        })


        // Add menu
        setHasOptionsMenu(true)


        return binding.root
    }
///////////////////////
// OVERRIDE METHODS  //
///////////////////////

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readAllData.map {
                recipe ->
            ListAdapter().setData(recipe)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllRecipes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllRecipes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
//            mRecipeViewModel.deleteAllRecipe()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()

    }
}