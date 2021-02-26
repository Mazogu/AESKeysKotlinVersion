package com.example.encryptedstoragekotlin.Exceptions

import java.lang.Exception

class CipherNotInitializedException : Exception {
    constructor(error:String):super(error)
}