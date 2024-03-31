package com.power60.playlist.services

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlist")
class PlaylistRestController {

    @GetMapping("/")
    fun index(): String {
        return testFun()
    }

    @PostMapping("/save-playlist")
    fun handleSavePlaylist(): Boolean {
        return true
//        save_playlist_req = request.get_data().decode("utf-8")
//        save_playlist_req_json = json.loads(save_playlist_req)
//        playlist_id = SavePlaylist().main(save_playlist_req_json)
//
//        return {"playlist_id": playlist_id}, 200, response_headers
    }

    fun testFun(): String {
        return "Hello World From Playlist!"
    }
}