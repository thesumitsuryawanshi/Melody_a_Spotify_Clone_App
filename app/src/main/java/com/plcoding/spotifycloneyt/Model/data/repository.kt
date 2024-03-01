package com.plcoding.spotifycloneyt.Model.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.Model.data.remote.MusicDatabase
import javax.inject.Inject


class repository @Inject constructor(private var dataSource: MusicDatabase) {

    private val __GenreLovemutableLivedata = MutableLiveData<List<Song>>()
    private val __GenreMetalmutableLivedata = MutableLiveData<List<Song>>()
    private val __GenreRomanticmutableLivedata = MutableLiveData<List<Song>>()
    private val __Genre90hitsmutableLivedata = MutableLiveData<List<Song>>()
    private val __GenreOldIsGoldmutableLivedata = MutableLiveData<List<Song>>()


    val GenreLoveData: LiveData<List<Song>>
        get() = __GenreLovemutableLivedata

    val GenreMetalData: LiveData<List<Song>>
        get() = __GenreMetalmutableLivedata

    val GenreRomanticData: LiveData<List<Song>>
        get() = __GenreRomanticmutableLivedata

    val Genre90hitsData: LiveData<List<Song>>
        get() = __Genre90hitsmutableLivedata

    val GenreOldIsGoldData: LiveData<List<Song>>
        get() = __GenreOldIsGoldmutableLivedata

    suspend fun getGenreLoveListData() {
        val _data = dataSource.getGenreLovesData()
        __GenreLovemutableLivedata.postValue(_data)
    }
    suspend fun getGenreMetalListData() {
        val _data = dataSource.getGenreMetalData()
        __GenreMetalmutableLivedata.postValue(_data)
    }
    suspend fun getGenreRomanticListData() {
        val _data = dataSource.getGenreRomanticData()
        __GenreRomanticmutableLivedata.postValue(_data)
    }
    suspend fun getGenre90hitsData() {
        val _data = dataSource.getGenre90hitsData()
        __Genre90hitsmutableLivedata.postValue(_data)
    }
    suspend fun getGenreOldIsGoldData() {
        val _data = dataSource.getGenreOldIsGoldData()
        __GenreOldIsGoldmutableLivedata.postValue(_data)
    }

//------------------------------------------------------------------------------------------------
    private val __LanguageEnglishmutableLivedata = MutableLiveData<List<Song>>()
    private val __LanguagePanjabimutableLivedata = MutableLiveData<List<Song>>()
    private val __LanguageHindimutableLivedata = MutableLiveData<List<Song>>()
    private val __LanguageMarathimutableLivedata = MutableLiveData<List<Song>>()
    private val __LanguageKoreanmutableLivedata = MutableLiveData<List<Song>>()



    val LanguageEnglishData: LiveData<List<Song>>
        get() = __LanguageEnglishmutableLivedata

    val LanguagePanjabiData: LiveData<List<Song>>
        get() = __LanguagePanjabimutableLivedata

    val LanguageHindiData: LiveData<List<Song>>
        get() = __LanguageHindimutableLivedata

    val LanguageMarathiData: LiveData<List<Song>>
        get() = __LanguageMarathimutableLivedata

    val LanguageKoreanData: LiveData<List<Song>>
        get() = __LanguageKoreanmutableLivedata




    suspend fun getLanguageEnglishData() {
        val _data = dataSource.getLanguageEnglishData()
        __LanguageEnglishmutableLivedata.postValue(_data)
    }
    suspend fun getLanguageHindiData() {
        val _data = dataSource.getLanguageHindiData()
        __LanguageHindimutableLivedata.postValue(_data)
    }
    suspend fun getLanguagePanjabiData() {
        val _data = dataSource.getLanguagePanjabiData()
        __LanguagePanjabimutableLivedata.postValue(_data)
    }

    suspend fun getLanguageMarathiData() {
        val _data = dataSource.getLanguageMarathiData()
        __LanguageMarathimutableLivedata.postValue(_data)
    }
    suspend fun getLanguageKoreanData() {
        val _data = dataSource.getLanguageKoreanData()
        __LanguageKoreanmutableLivedata.postValue(_data)
    }


}
