package com.example.encryptedstoragekotlin.hilt

import com.example.encryptedstoragekotlin.cipher.CipherWrapper
import com.example.encryptedstoragekotlin.cipher.KeyStoreWrapper
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule{
    @Provides
    fun provideFirebaseDB():FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideKeyStore():KeyStore = KeyStore.getInstance(KeyStoreWrapper.KEYSTORE_PROVIDER)

    @Provides
    fun provideKeyGenerator():KeyGenerator = KeyGenerator.getInstance(KeyStoreWrapper.MY_ALGORITHM, KeyStoreWrapper.KEYSTORE_PROVIDER)

    @Provides
    fun provideCipher():Cipher = Cipher.getInstance(CipherWrapper.TRANSFORMATION)
}