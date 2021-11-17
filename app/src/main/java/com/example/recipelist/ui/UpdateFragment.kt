package com.example.recipelist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.databinding.FragmentUpdateBinding
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_ADD
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_UPDATE
import com.example.recipelist.utils.Logger
import com.example.recipelist.viewmodel.RecipeViewModel



class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mRecipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        binding.apply {
//            updateFirstNameEt = args.currentRecipe.firstName
//            updateLastNameEt = args.currentRecipe.lastName
//            updateAgeEt = args.currentRecipe.age
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
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = binding.updateAgeEt.text

        if (inputCheck(firstName, lastName, age)) {
            // Create Recipe Object
            val updatedRecipe = Recipe(args.currentRecipe.id, firstName, lastName, age.toString())
            // Update Current Recipe
            mRecipeViewModel.updateRecipe(updatedRecipe)
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


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
            mRecipeViewModel.deleteRecipe(args.currentRecipe)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentRecipe.firstName}",
                Toast.LENGTH_SHORT
            ).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentRecipe.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentRecipe.firstName}?")
        builder.create().show()
    }
}