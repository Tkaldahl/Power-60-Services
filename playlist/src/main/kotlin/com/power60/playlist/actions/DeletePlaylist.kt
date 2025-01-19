package com.power60.playlist.actions

import com.power60.playlist.services.PlaylistMongoService

class DeletePlaylistRequest(val id: String)
class DeletePlaylistResponse(val status: Boolean)

class DeletePlaylist constructor(private val mongoService: PlaylistMongoService) {
    fun main(req: DeletePlaylistRequest): DeletePlaylistResponse {
        val mongoResponse = mongoService.deletePlaylist(req.id).wasAcknowledged()
        return DeletePlaylistResponse(mongoResponse)
    }
}