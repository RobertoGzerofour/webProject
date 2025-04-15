package com.humber.WebProject.services;

import com.humber.WebProject.models.UsedAlbum;
import com.humber.WebProject.repositores.UsedAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsedAlbumService {
    @Autowired
    private UsedAlbumRepository usedAlbumRepository;

    public List<UsedAlbum> getAllAlbums() {
        return usedAlbumRepository.findAll();
    }

    public Optional<UsedAlbum> getAlbumByNameAndArtist(String name, String artist) {
        return usedAlbumRepository.findByNameIgnoreCaseAndArtistIgnoreCase(name, artist);
    }
    public void saveAlbum(UsedAlbum usedAlbum) {
        usedAlbumRepository.save(usedAlbum);
    }
}
