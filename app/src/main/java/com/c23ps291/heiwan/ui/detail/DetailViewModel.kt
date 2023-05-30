package com.c23ps291.heiwan.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.c23ps291.heiwan.data.HeiwanRepository

class DetailViewModel(repository: HeiwanRepository) : ViewModel() {

    private val repo = repository

    fun getListAnimal() = repo.getAnimals().asLiveData()
}