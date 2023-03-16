package com.example.encryptedstoragekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList.OnListChangedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.encryptedstoragekotlin.R
import com.example.encryptedstoragekotlin.databinding.FragmentDecryptBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DecryptFragment: Fragment() {

    private lateinit var viewModel:SecurityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var listener:OnListChangedCallback<ObservableArrayList<Pair<String,String>>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SecurityViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        val binding:FragmentDecryptBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_decrypt, container, false)
        recyclerView = binding.decryptRecycle
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DecryptAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = getListener()
        viewModel.decryptedStrings.addOnListChangedCallback(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.decryptedStrings.removeOnListChangedCallback(listener)
    }

    private fun getListener():OnListChangedCallback<ObservableArrayList<Pair<String,String>>>{
        return object : OnListChangedCallback<ObservableArrayList<Pair<String, String>>>(){
            override fun onChanged(sender: ObservableArrayList<Pair<String, String>>?) {
                TODO("Not yet implemented")
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<Pair<String, String>>?,
                positionStart: Int,
                itemCount: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<Pair<String, String>>?,
                positionStart: Int,
                itemCount: Int
            ) {
                sender?.let {
                    val adapter = recyclerView.adapter as DecryptAdapter
                    if(itemCount == 1)
                        adapter.addString(it[positionStart])
                    else if(itemCount > 1)
                        adapter.addList(it.subList(positionStart, itemCount))
                }
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<Pair<String, String>>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<Pair<String, String>>?,
                positionStart: Int,
                itemCount: Int
            ) {
                sender?.let {
                    val adapter = recyclerView.adapter as DecryptAdapter
                    if(itemCount == 1)
                        adapter.removeString(positionStart)
                }
            }
        }
    }
}