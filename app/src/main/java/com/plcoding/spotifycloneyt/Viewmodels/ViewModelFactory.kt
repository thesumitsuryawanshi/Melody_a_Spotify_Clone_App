//class viewModelFactory {
//
//
//    class viewModelFactory(   val musicServiceConnection: MusicServiceConnection) : ViewModelProvider.Factory {
//        override inline fun <reified MainViewModel: ViewModel?> create(modelClass: Class<MainViewModel>): MainViewModel {
//            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//                return MainViewModel(musicServiceConnection) as MainViewModel
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
//
//}

package com.plcoding.spotifycloneyt.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection

class ViewModelFactory(private val musicServiceConnection: MusicServiceConnection) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(musicServiceConnection) as T
    }
}
