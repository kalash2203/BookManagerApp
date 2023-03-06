package com.example.bookmanagerapp.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanagerapp.R
import com.example.bookmanagerapp.databinding.ItemBooksLayoutBinding
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.utils.Constants.BOOKNAME
import com.example.bookmanagerapp.utils.Constants.ID
import com.example.bookmanagerapp.utils.Constants.NAME
import com.example.bookmanagerapp.utils.Constants.NUMBER
import com.example.bookmanagerapp.utils.DeleteBook

class BooksAdapter(private val deleteBook: DeleteBook,val activity: Activity) : RecyclerView.Adapter<BooksAdapter.ToDoViewHolder>() {


    inner class ToDoViewHolder(val binding: ItemBooksLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    //Used Diff Util to notify the Recycler View change in data on delete or on update
    private val differCallback = object : DiffUtil.ItemCallback<BooksInfo>() {

        override fun areItemsTheSame(oldItem: BooksInfo, newItem: BooksInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BooksInfo, newItem: BooksInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    var bookList: List<BooksInfo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        //inflate the layout
        return ToDoViewHolder(
            ItemBooksLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val bookInfo = bookList[position]

        holder.binding.apply {
            //set up bookName , Number , User Name inside  the cardView
            userName.text = bookInfo.Name
            userNumber.text = bookInfo.Number
            bookName.text = bookInfo.BookName
            // pressing delete button calls the onDelete Function of interface.
            delete.setOnClickListener {
                deleteBook.onDelete(bookInfo)
            }
                // on pressing cardView goTo Add Book Screen and pass the information inside the bundle
            this.root.setOnClickListener {
                Navigation.findNavController(activity, R.id.nav_host_fragment)
                    .navigate(R.id.action_booksListFragment_to_booksFragment, bundleOf(NAME to bookInfo.Name,
                    NUMBER to bookInfo.Number,
                    BOOKNAME to bookInfo.BookName,
                    ID to bookInfo.id))
            }
        }


    }

    //return size of the list
    override fun getItemCount() : Int {return bookList.size}


}