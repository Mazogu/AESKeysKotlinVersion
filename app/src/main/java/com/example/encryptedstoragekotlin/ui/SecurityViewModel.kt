package com.example.encryptedstoragekotlin.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.encryptedstoragekotlin.cipher.CipherWrapper
import com.example.encryptedstoragekotlin.firebase.FirebaseDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(private val cipher:CipherWrapper, firebaseDB: FirebaseDB) : ViewModel(), FirebaseDB.CallBack {

    val encryptText = MutableLiveData<String>()

    override fun retrieveData(data: String?, key: String?) {
        TODO("Not yet implemented")
    }

    override fun removeData(key: String?) {
        TODO("Not yet implemented")
    }
}