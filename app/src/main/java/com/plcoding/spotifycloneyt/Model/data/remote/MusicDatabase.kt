package com.plcoding.spotifycloneyt.Model.data.remote

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


    //Additional Music sources (Genre's)
    suspend fun getGenreLovesData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("Love").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getGenreMetalData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("metal").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getGenreRomanticData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("romantic").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getGenre90hitsData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("90's Hits").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getGenreOldIsGoldData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("old is gold").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    //Additional Music sources (Languages)
    suspend fun getLanguageEnglishData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("lang_english").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getLanguagePanjabiData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("lang_panjabi").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getLanguageHindiData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("lang_hindi").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getLanguageMarathiData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("lang_marathi").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }

    suspend fun getLanguageKoreanData(): List<Song> {
        val productList = mutableListOf<Song>()

        try {
            val snapshot = db.collection("lang_korean").get().await()
            for (document in snapshot.documents) {
                val product = document.toObject(Song::class.java)
                product?.let {
                    productList.add(product)

                }
            }
        } catch (e: Exception) {
            // Handle any exceptions here
            e.printStackTrace()
        }
        return productList
    }


}