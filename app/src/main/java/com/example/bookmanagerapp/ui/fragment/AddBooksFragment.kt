package com.example.bookmanagerapp.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.ui.viewmodel.BooksViewModel
import com.example.bookmanagerapp.R
import com.example.bookmanagerapp.databinding.FragmentAddBookBinding
import com.example.bookmanagerapp.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBooksFragment : BottomSheetDialogFragment(R.layout.fragment_add_book) {

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    //List of items inside dropdown menu
    private var bookName = arrayOf<String?>(
        "Book A",
        "Book B",
        "Book C"
    )
    //set up ViewModel using viewModels()
    private val viewModel: BooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve Information sent in Bundle on Pressing on the Card
        val name = arguments?.getString(Constants.NAME)
        val number = arguments?.getString(Constants.NUMBER)
        val selectedBookName = arguments?.getString(Constants.BOOKNAME)
        val id = arguments?.getInt(Constants.ID)


        //initializing ArrayAdapter with layout and array of String items
        val arrayAdapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(requireContext(), R.layout.my_selected_item, bookName)
        arrayAdapter.setDropDownViewResource(R.layout.my_selected_item)
        binding.selectBooks.adapter = arrayAdapter

        binding.btnAddTask.setOnClickListener { mView ->
            //check if id is null . If Yes then create fresh record and insert in side database
            //else update old record
            if (id == null)
                saveNote(mView)
            else
                updateNote(mView,name.toString(),number.toString(),selectedBookName.toString(),id)
        }


        // check for book Position inside the array and then set the spinner selection to particular item
        if (!name.isNullOrEmpty()){
            binding.etName.setText(name)
            binding.etNumber.setText(number)
            for (i in bookName.indices)
            {
                if(bookName[i]==selectedBookName)
                {
                    binding.selectBooks.setSelection(i,true)
                }
            }
        }



    }

    private fun updateNote(mView: View, name: String, number: String, selectedBookName: String, id: Int) {
        val bookInfo = BooksInfo(id, name,number,selectedBookName)
        viewModel.updateBook(bookInfo)
      dismiss()
        Snackbar.make(mView, "Info Updated Successfully",
            Snackbar.LENGTH_SHORT).show()
    }


    private fun saveNote(view: View) {
        val userName = binding.etName.text.toString()
        val userNumber = binding.etNumber.text.toString()
        val bookName = binding.selectBooks.selectedItem.toString()

        //Check if UserName or Number is Empty or not. If its empty then show snackBar message
        if (userName.isNotEmpty() && userNumber.isNotEmpty()) {
            val bookInfo = BooksInfo(0, userName,userNumber,bookName)

            //Pass bookInfo Object to insert the data
            viewModel.insertBook(bookInfo)

            Snackbar.make(view, "Info Saved Successfully",
                Snackbar.LENGTH_SHORT).show()

           dismiss()

        } else {
            val toast = Toast.makeText(activity,
                "User Name and Number can not be empty",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}