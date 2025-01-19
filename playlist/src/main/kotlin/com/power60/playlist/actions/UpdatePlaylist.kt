package com.power60.playlist.actions

import com.power60.playlist.models.Playlist
import com.power60.playlist.services.PlaylistMongoService

class UpdatePlaylistRequest(val playlist: Playlist)
class UpdatePlaylistResponse(val id: String?)

class UpdatePlaylist constructor(private val mongoService: PlaylistMongoService) {
    fun main(req: UpdatePlaylistRequest): UpdatePlaylistResponse {
        val updatedPlaylistId = mongoService.save(req.playlist)
        return UpdatePlaylistResponse(updatedPlaylistId)
    }
}