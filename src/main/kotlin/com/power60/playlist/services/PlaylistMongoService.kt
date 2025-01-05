package com.power60.playlist.services

import com.mongodb.client.result.DeleteResult
import com.power60.playlist.models.Playlist
import com.power60.playlist.models.PlaylistFactory
import com.power60.utilities.services.MongoService
import com.power60.utilities.services.MongoServiceImpl

class PlaylistMongoService {
    private val mongoService: MongoService<Playlist> = MongoServiceImpl(
        "repo",
        "playlists",
        "mongodb+srv://cluster0.y1ghy8y.mongodb.net/",
        27017,
        "tkaldahl",
        "R0KNG6LbX93QnO45",
        Playlist::class.java
    )

//    fun getAllPlaylists(): List<Playlist> {
//        val getAllQuery = MatchAllQuery()
//        val playlists = mongoService.search(getAllQuery).items
//
//        return playlists // we may need to clean these _ids before we can return them.
//    }

    fun save(playlist: Playlist): String? {
        return if (playlist.id.isNullOrBlank()) {
            val newPlaylist = PlaylistFactory().createNewPlaylist(
                playlist.playlistName,
                playlist.playlist,
                playlist.transitionVideoUrl
            )
            mongoService.save(newPlaylist)
        } else {
            mongoService.update(playlist.id!!, playlist).upsertedId.toString()
        }
    }

    fun getPlaylistById(playlistId: String): Playlist? {
        return mongoService.searchById(playlistId)
    }

//    fun searchPlaylists(searchQuery: Query): List<Playlist> {
//        return mongoService.search(searchQuery)
//    }

    fun deletePlaylist(playlistId: String): DeleteResult {
        return mongoService.deleteById(playlistId)
    }
}
