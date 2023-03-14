package com.example.encryptedstoragekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.encryptedstoragekotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EncryptFragment : Fragment() {

    private lateinit var viewModel:SecurityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SecurityViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_encrypt, container, false)
    }
}