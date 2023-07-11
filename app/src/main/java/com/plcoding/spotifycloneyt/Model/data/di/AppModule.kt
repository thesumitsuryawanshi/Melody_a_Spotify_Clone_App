package com.plcoding.spotifycloneyt.Model.data.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.View.MainActivity
import com.plcoding.spotifycloneyt.View.adapters.SongAdapter
import com.plcoding.spotifycloneyt.View.adapters.SwipeSongAdapter
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMusicServiceConnection(
        @ApplicationContext context: Context
    ) = MusicServiceConnection(context)



//    @Singleton
//    @Provides
//     fun SwipeSongAdapter(): SwipeSongAdapter = SwipeSongAdapter()
//
//

    @Singleton
    @Provides
    fun provideSongModel(): List<Song> = emptyList()


    @Singleton
    @Provides
    fun provideSongsClicked(): SongAdapter.SongsCLicked {
        return object : SongAdapter.SongsCLicked {
            override fun SongCLicked(song: Song) {
                // Define the behavior when a song is clicked
            }
        }
    }

    @Singleton
    @Provides
    fun provideSwipeSongsClicked(): SwipeSongAdapter.SwipeSongsCLicked {
        return object :  SwipeSongAdapter.SwipeSongsCLicked{
            override fun SwipeSongCLicked(song: Song) {
                // Define the behavior when a song is clicked
            }
        }
    }

//    @Singleton
//    @Provides
//    fun providemainViewModel() = MainViewModel(provideMusicServiceConnection(@ApplicationContext MainActivity()))
//

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}