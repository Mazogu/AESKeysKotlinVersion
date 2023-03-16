package com.example.encryptedstoragekotlin.cipher

import android.util.Base64
import com.example.encryptedstoragekotlin.Exceptions.CipherNotInitializedException
import java.io.UnsupportedEncodingException
import java.security.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class CipherWrapper @Inject constructor(private val cipher: Cipher) {


    @ExperimentalStdlibApi
    @Throws(InvalidKeyException::class, BadPaddingException::class,
        IllegalBlockSizeException::class, NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class, UnsupportedEncodingException::class)
    fun encrypt(plainText:String, key:Key):String{
        val iv = ByteArray(GCM_IV)
        var random = SecureRandom()
        val spec = GCMParameterSpec(GCM_TAG * Byte.SIZE_BITS, iv)
        cipher.init(Cipher.ENCRYPT_MODE,key,spec) ?: throw CipherNotInitializedException("Cipher hasn't been initialized")
        val cipherText = cipher.doFinal(plainText.encodeToByteArray()) ?: ByteArray(0)
        val encryptedBytes = ByteArray(cipherText.size + iv.size)
        System.arraycopy(iv,0,encryptedBytes,0,iv.size)
        System.arraycopy(cipherText,0,encryptedBytes,iv.size,cipherText.size)
        return Base64.encodeToString(encryptedBytes,Base64.DEFAULT)
    }

    @Throws(InvalidKeyException::class, BadPaddingException::class,
        IllegalBlockSizeException::class, NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class, UnsupportedEncodingException::class)
    fun decrypt(encryptedText:String?, key:Key?):String{
        val decoded:ByteArray = Base64.decode(encryptedText, Base64.DEFAULT)
        val iv:ByteArray = decoded.copyOfRange(0, GCM_IV)
        val spec = GCMParameterSpec(GCM_TAG * Byte.SIZE_BITS,iv)
        cipher.init(Cipher.DECRYPT_MODE,key,spec) ?: throw CipherNotInitializedException("Cipher hasn't been initialized")
        val cipherText = cipher.doFinal(decoded,GCM_IV,decoded.size - GCM_IV)
        return String(cipherText ?: ByteArray(0),Charsets.UTF_8)
    }

    companion object {
        const val TRANSFORMATION = "AES/GCM/NoPadding"
        const val GCM_TAG = 16
        const val GCM_IV = 12
    }
}