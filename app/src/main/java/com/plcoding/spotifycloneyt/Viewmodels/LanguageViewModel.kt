package com.plcoding.spotifycloneyt.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.Model.data.repository
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repository: repository
) : ViewModel() {

    val getLanguageEnglishData: LiveData<List<Song>>
        get() = repository.LanguageEnglishData

    val getLanguagePanjabiData: LiveData<List<Song>>
        get() = repository.LanguagePanjabiData

    val getLanguageHindiData: LiveData<List<Song>>
        get() = repository.LanguageHindiData

    val getLanguageMarathiData: LiveData<List<Song>>
        get() = repository.LanguageMarathiData

    val getLanguageKoreanData: LiveData<List<Song>>
        get() = repository.LanguageKoreanData


    init {
        viewModelScope.launch {
            repository.getLanguageEnglishData()
            repository.getLanguagePanjabiData()
            repository.getLanguageHindiData()
            repository.getLanguageMarathiData()
            repository.getLanguageKoreanData()
        }

    }
}
