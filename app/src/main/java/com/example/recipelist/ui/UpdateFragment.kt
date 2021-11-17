package com.example.recipelist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mRecipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        view.updateFirstName_et.setText(args.currentRecipe.firstName)
        view.updateLastName_et.setText(args.currentRecipe.lastName)
        view.updateAge_et.setText(args.currentRecipe.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(firstName, lastName, updateAge_et.text)) {
            // Create Recipe Object
            val updatedRecipe = Recipe(args.currentRecipe.id, firstName, lastName, age)
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