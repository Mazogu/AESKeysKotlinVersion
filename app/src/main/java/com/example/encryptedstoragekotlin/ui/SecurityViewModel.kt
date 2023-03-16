package com.example.encryptedstoragekotlin.ui

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.encryptedstoragekotlin.cipher.CipherWrapper
import com.example.encryptedstoragekotlin.cipher.KeyStoreWrapper
import com.example.encryptedstoragekotlin.firebase.FirebaseDB
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.security.Key
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(private val cipher:CipherWrapper, private val keyWrapper:KeyStoreWrapper, private val firebaseDB: FirebaseDB) : ViewModel(), FirebaseDB.CallBack {

    val encryptText = ObservableField<String>()
    val decryptedStrings = ObservableArrayList<Pair<String, String>>()
    init {
        firebaseDB.attachCallback(this)
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun encryptEnteredText(){
        try {
            keyWrapper.createSecretKey(ALIAS)
            val key = keyWrapper.getKey(ALIAS)
            key?.let {
                val secret = cipher.encrypt(encryptText.get() ?: "Empty", it)
                firebaseDB.sendMessage(secret)
            } ?: throw NoSuchFieldException()
        }
        catch (e:Exception){
            Timber.e(e)
        }
    }

    override fun retrieveData(key: String?, data: String?) {
        try{
            val secretKey: Key? = keyWrapper.getKey(ALIAS)
            val plainText = cipher.decrypt(data, secretKey)
            key?.let { decryptedStrings.add(Pair(it, plainText)) }
        }
        catch (e:Exception){
            Timber.e(e)
        }
    }

    override fun removeData(key: String?) {
        key?.let {
            val removed = decryptedStrings.indexOfFirst{ key == it.first }
            decryptedStrings.removeAt(removed)
        }
    }

    fun removeListeners() {
        firebaseDB.removeListeners()
    }

    companion object{
        private const val ALIAS = "Encryption Go"
    }
}