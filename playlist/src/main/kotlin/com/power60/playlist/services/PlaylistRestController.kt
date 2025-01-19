package com.power60.playlist.services

import com.power60.playlist.actions.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlist")
class PlaylistRestController {

    // TODO: Bind PlaylistMongoService as an eager singleton so we don't keep rebuilding it.
    private val mongoService = PlaylistMongoService()

    @GetMapping("/")
    fun index(): String {
        return testFun()
    }

    @PostMapping("/CreatePlaylist")
    fun handleCreatePlaylist(request: CreatePlaylistRequest): CreatePlaylistResponse {
        return CreatePlaylist(mongoService).main(request)
    }

    @PostMapping("/GetPlaylist")
    fun handleGetPlaylist(request: GetPlaylistRequest): GetPlaylistResponse {
        return GetPlaylist(mongoService).main(request)
    }

    @PostMapping("/UpdatePlaylist")
    fun handleUpdatePlaylist(request: UpdatePlaylistRequest): UpdatePlaylistResponse {
        return UpdatePlaylist(mongoService).main(request)
    }

    @PostMapping("/DeletePlaylist")
    fun handleDeletePlaylist(request: DeletePlaylistRequest): DeletePlaylistResponse {
        return DeletePlaylist(mongoService).main(request)
    }

    fun testFun(): String {
        return "Hello World From Playlist!"
    }
}