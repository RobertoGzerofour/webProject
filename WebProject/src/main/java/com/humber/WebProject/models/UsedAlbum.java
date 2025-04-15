package com.humber.WebProject.models;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;



@Entity
@Table(name = "used_albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsedAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Album name is required")
    private String name;

    @Column(nullable = false )
    @Min(value = 1900, message = "Invalid release year")
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "Artist name is required")
    private String artist;

    @ElementCollection
    @Column(nullable = false)
    private List<String> genres;

    @Column(nullable = false)
    @Min(value = 1, message = "Number of songs must be at least 1")
    private Integer numOfSongs;

    @Column(nullable = false)
    @Min(value = 1, message = "Listen time must be positive")
    private Integer listenTime;

    @Column(nullable = false)
    @NotBlank(message = "Image link is required")
    private String imageLink;

    @Column(nullable = false)
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    @Column(nullable = false)
    @NotBlank(message = "Condition description is required")
    private String conditionDescription;
}

