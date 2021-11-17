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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipelist.R
import com.example.recipelist.data.model.Recipe
import com.example.recipelist.data.repo.RecipeRepository
import com.example.recipelist.databinding.FragmentAddBinding
import com.example.recipelist.databinding.FragmentListBinding
import com.example.recipelist.utils.Globals
import com.example.recipelist.utils.Globals.Companion.TAG_FRAG_ADD
import com.example.recipelist.utils.Logger
import com.example.recipelist.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory(RecipeRepository(requireActivity().application))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }
        Log.d(TAG_FRAG_ADD, "onCreateView: ")
//        Logger.logd(TAG_FRAG_ADD, "onCreateView")

        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = et_add_name.text.toString()
        val description = et_add_description.text.toString()
//        val age = addAge_et.text

        if (inputCheck(name, description)) {
            // Create Recipe object
            val recipe = Recipe(0, name, description)
            // Add data to Database
            viewModel.addRecipe(recipe)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, description: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description))
    }

}