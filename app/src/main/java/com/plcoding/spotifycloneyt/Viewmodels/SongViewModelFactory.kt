package com.plcoding.spotifycloneyt.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection

class SongViewModelFactory(private val musicServiceConnection: MusicServiceConnection) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return SongViewModel(musicServiceConnection) as T
    }
}