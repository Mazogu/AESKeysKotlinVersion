package com.example.encryptedstoragekotlin.hilt

import com.example.encryptedstoragekotlin.cipher.CipherWrapper
import com.example.encryptedstoragekotlin.cipher.KeyStoreWrapper
import com.example.encryptedstoragekotlin.firebase.FirebaseDB
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
object FirebaseModule{
    @Provides
    fun provideFirebaseDB():FirebaseDatabase = FirebaseDatabase.getInstance()
}

@Module
@InstallIn(SingletonComponent::class)
object CipherModule{
    @Provides
    fun provideCipher():Cipher = Cipher.getInstance(CipherWrapper.TRANSFORMATION)
}

@Module
@InstallIn(SingletonComponent::class)
object KeyStoreModule{
    @Provides
    fun provideKeyStore():KeyStore = KeyStore.getInstance(KeyStoreWrapper.KEYSTORE_PROVIDER)

    @Provides
    fun provideKeyGenerator():KeyGenerator = KeyGenerator.getInstance(KeyStoreWrapper.MY_ALGORITHM, KeyStoreWrapper.KEYSTORE_PROVIDER)
}