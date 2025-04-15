package com.humber.WebProject.repositores;

import com.humber.WebProject.models.UsedAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsedAlbumRepository extends JpaRepository<UsedAlbum, Long> {

    Optional<UsedAlbum> findByNameIgnoreCaseAndArtistIgnoreCase(String name, String artist);
}
