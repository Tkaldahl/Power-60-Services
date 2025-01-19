package com.power60.playlist.actions

import com.power60.playlist.models.Playlist
import com.power60.playlist.services.PlaylistMongoService

class GetPlaylistRequest(val id: String)
class GetPlaylistResponse(val playlist: Playlist?)

class GetPlaylist constructor(private val mongoService: PlaylistMongoService) {
    fun main(req: GetPlaylistRequest): GetPlaylistResponse {
        val playlist = mongoService.getPlaylistById(req.id)
        return GetPlaylistResponse(playlist)
    }
}