package com.example.recipelist.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.databinding.FragmentAddBinding
import com.example.recipelist.databinding.FragmentListBinding
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_ADD
import com.example.recipelist.utils.Logger
import com.example.recipelist.viewmodel.RecipeViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mRecipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }
        Log.d(TAG_FRAG_ADD, "onCreateView: ")
//        Logger.logd(TAG_FRAG_ADD, "onCreateView")

        return binding.root
    }

    private fun insertDataToDatabase() {
//        val firstName = addFirstName_et.text.toString()
//        val lastName = addLastName_et.text.toString()
//        val age = addAge_et.text

//        if (inputCheck(firstName, lastName, age)) {
//            // Create Recipe object
//            val Recipe = Recipe(0, firstName, lastName, Integer.parseInt(age.toString()))
//            // Add data to Database
//            mRecipeViewModel.addRecipe(Recipe)
//            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
//            // Navigate back
//            findNavController().navigate(R.id.action_addFragment_to_listFragment)
//        } else {
//            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
//        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}