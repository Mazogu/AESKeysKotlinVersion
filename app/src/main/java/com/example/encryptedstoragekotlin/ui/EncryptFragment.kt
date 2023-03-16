package com.example.encryptedstoragekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.encryptedstoragekotlin.R
import com.example.encryptedstoragekotlin.databinding.FragmentEncryptBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EncryptFragment : Fragment() {

    private lateinit var viewModel:SecurityViewModel
    private lateinit var currentText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SecurityViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding:FragmentEncryptBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_encrypt, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.view = this
        currentText = binding.encryptedText
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeListeners()
    }

    fun sendMessage(){
        if(currentText.text.isBlank())
            currentText.error = getString(R.string.encrypt_warning)
        else
            viewModel.encryptEnteredText()
    }
}

@BindingAdapter("clearWarning")
fun clearWarning(editText: EditText, text:ObservableField<String>){
    if(!text.get().isNullOrEmpty())
        editText.error = null
}