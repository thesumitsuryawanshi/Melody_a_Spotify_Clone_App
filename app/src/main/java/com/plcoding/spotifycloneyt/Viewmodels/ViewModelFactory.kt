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
//}

package com.plcoding.spotifycloneyt.Viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plcoding.spotifycloneyt.Model.data.remote.MusicDatabase
import com.plcoding.spotifycloneyt.Model.data.repository
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection

class ViewModelFactory(private val musicServiceConnection: MusicServiceConnection) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(musicServiceConnection, repository(MusicDatabase())) as T
    }
}