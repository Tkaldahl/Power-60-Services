package com.power60.playlist.models

import java.util.*

interface Playlist {
    var id: String?
    val playlistName: String
    val playlist: List<YTVideoMetadata>
    val transitionVideoUrl: String
}

class PlaylistFactory {
    fun createNewPlaylist(
        playlistName: String,
        playlist: List<YTVideoMetadata>,
        transitionVideoUrl: String
    ): Playlist {
        val id = UUID.randomUUID().toString()
        return mapOf(
            "id" to id,
            "playlistName" to playlistName,
            "playlist" to playlist,
            "transitionVideoUrl" to transitionVideoUrl
        ) as Playlist
    }
}
