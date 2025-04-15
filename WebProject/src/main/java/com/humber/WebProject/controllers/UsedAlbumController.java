package com.humber.WebProject.controllers;

import com.humber.WebProject.models.UsedAlbum;
import com.humber.WebProject.services.UsedAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/albums")
public class UsedAlbumController {
    @Autowired
    private UsedAlbumService usedAlbumService;

    @GetMapping
    public ResponseEntity<List<UsedAlbum>> getAllAlbums(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(usedAlbumService.getAllAlbums());
    }

    @GetMapping("/details")
    public ResponseEntity<UsedAlbum> getAlbumByNameAndArtist(@RequestParam String name, @RequestParam String artist) {
        Optional<UsedAlbum> album = usedAlbumService.getAlbumByNameAndArtist(name, artist);
        return album.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
