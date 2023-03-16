package com.example.encryptedstoragekotlin.cipher

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.Key
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.inject.Inject

class KeyStoreWrapper @Inject constructor(private val keyStore: KeyStore, private val keyGenerator: KeyGenerator){

    init {
        keyStore.load(null)
    }

    fun createSecretKey(alias:String):Key{
        if(keyStore.containsAlias(alias))
            return keyStore.getKey(alias, null)
        val spec = KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setRandomizedEncryptionRequired(false)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        }.build()
        keyGenerator.init(spec)
        return keyGenerator.generateKey()
    }

    fun getKey(alias:String):Key?{
        val secretKeyEntry = keyStore.getEntry(alias, null) as KeyStore.SecretKeyEntry?
        return secretKeyEntry?.secretKey
    }


    companion object {
        const val MY_ALGORITHM = "AES"
        const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    }
}