package com.plcoding.spotifycloneyt.Model.data.di

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.View.MainActivity
import com.plcoding.spotifycloneyt.View.adapters.SongAdapter
import com.plcoding.spotifycloneyt.View.adapters.SwipeSongsCLicked
import com.plcoding.spotifycloneyt.View.fragments.HomeFragment
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
    fun provideSwipeSongClicked(): SwipeSongsCLicked {
        return object : SwipeSongsCLicked {
            override fun SwipeSongCLicked(song: Song) {
                Navigation.findNavController(HomeFragment().binding.root).navigate(R.id.globalActionToSongFragment)
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