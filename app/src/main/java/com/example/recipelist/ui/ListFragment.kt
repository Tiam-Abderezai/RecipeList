package com.example.recipelist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipelist.ui.adapter.ListAdapter
import com.example.recipelist.viewmodel.RecipeViewModel
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.repo.RecipeRepository
import com.example.recipelist.databinding.FragmentListBinding
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_LIST
import com.example.recipelist.utils.Logger
import kotlinx.coroutines.flow.map

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory(RecipeRepository(requireActivity().application))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            binding.addNewRecipe.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_addFragment)
            }
        }
        setHasOptionsMenu(true)
        Log.d(TAG_FRAG_LIST, "onCreateView: ")
//        Logger.logd(TAG_FRAG_LIST, "onCreateView")
        return binding.root
    }
///////////////////////
// OVERRIDE METHODS  //
///////////////////////

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            Log.d(TAG_FRAG_LIST, "onViewCreated: $recipes")
            binding.recyclerView.adapter = ListAdapter(recipes)
        })
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