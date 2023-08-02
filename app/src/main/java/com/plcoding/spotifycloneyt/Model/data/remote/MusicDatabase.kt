package com.plcoding.spotifycloneyt.Model.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)
    private val db = Firebase.firestore


    suspend fun getAllSongs(): List<Song> {
        val MusicList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("song names").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    MusicList.add(product)
                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }

        return MusicList
    }
}



