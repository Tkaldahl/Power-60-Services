package com.power60.playlist.actions

import com.power60.playlist.models.Playlist
import com.power60.playlist.services.PlaylistMongoService

class CreatePlaylistRequest(val playlist: Playlist)
class CreatePlaylistResponse(val id: String?)

class CreatePlaylist constructor(private val mongoService: PlaylistMongoService) {
    fun main(req: CreatePlaylistRequest): CreatePlaylistResponse {
        val playlistId = mongoService.save(req.playlist)
        return CreatePlaylistResponse(playlistId)
    }
}