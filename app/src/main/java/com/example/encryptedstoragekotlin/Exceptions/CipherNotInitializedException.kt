package com.example.encryptedstoragekotlin.Exceptions

import java.lang.Exception

class CipherNotInitializedException(error: String) : Exception(error) {
}