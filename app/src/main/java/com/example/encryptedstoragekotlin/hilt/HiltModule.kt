package com.example.encryptedstoragekotlin.hilt

import com.example.encryptedstoragekotlin.cipher.CipherWrapper
import com.example.encryptedstoragekotlin.firebase.FirebaseDB
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.crypto.Cipher

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