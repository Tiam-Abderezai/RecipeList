package com.example.recipelist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.repo.RecipeRepository
import com.example.recipelist.databinding.FragmentUpdateBinding
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_ADD
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_UPDATE
import com.example.recipelist.utils.Logger
import com.example.recipelist.viewmodel.RecipeViewModel


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory(RecipeRepository(requireActivity().application))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.apply {
            etUpdateName.setText(args.currentRecipe.name)
            etUpdateDescription.setText(args.currentRecipe.description)
            etUpdateIngredient1.setText(args.currentRecipe.ingredients[0])
            etUpdateIngredient2.setText(args.currentRecipe.ingredients[1])
            etUpdateIngredient3.setText(args.currentRecipe.ingredients[2])
            etUpdateIngredient4.setText(args.currentRecipe.ingredients[3])
            etUpdateIngredient5.setText(args.currentRecipe.ingredients[4])
            updateBtn.setOnClickListener {
                updateItem()
            }
        }

        setHasOptionsMenu(true)
        Log.d(TAG_FRAG_ADD, "onCreateView: ")
//        Logger.logd(TAG_FRAG_UPDATE, "onCreateView")

        return binding.root
    }

    private fun updateItem() {
        val name = binding.etUpdateName.text.toString()
        val description = binding.etUpdateDescription.text.toString()
        val ingredients = listOf(
            binding.etUpdateIngredient1.text.toString(),
            binding.etUpdateIngredient2.text.toString(),
            binding.etUpdateIngredient3.text.toString(),
            binding.etUpdateIngredient4.text.toString(),
            binding.etUpdateIngredient5.text.toString()
        )
        if (inputCheck(name, description)) {
            val updatedRecipe = Recipe(args.currentRecipe.id, name, description, ingredients)
            viewModel.updateRecipe(updatedRecipe)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(name: String, description: String) =
        !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description))


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteRecipe()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRecipe() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteRecipe(args.currentRecipe)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentRecipe.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentRecipe.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentRecipe.name}?")
        builder.create().show()
    }
}