package com.plcoding.spotifycloneyt.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.spotifycloneyt.Model.data.remote.MusicDatabase
import com.plcoding.spotifycloneyt.Model.data.repository

class LangViewModelFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return LanguageViewModel(repository(MusicDatabase())) as T
    }
}