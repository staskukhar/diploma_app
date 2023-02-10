package com.example.mypets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel(){
    val message: MutableLiveData<Pet> by lazy{
        MutableLiveData<Pet>()
    }
}