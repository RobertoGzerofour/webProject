package com.humber.WebProject;

import com.humber.WebProject.models.UsedAlbum;
import com.humber.WebProject.repositores.UsedAlbumRepository;
import com.humber.WebProject.repositores.UserRepository;
import com.humber.WebProject.services.UsedAlbumService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebProjectApplication {
	private final UserRepository userRepository;

	public WebProjectApplication(UserRepository userRepository, UsedAlbumService usedAlbumService) {
		this.userRepository = userRepository;
		this.usedAlbumService = usedAlbumService;
	}
	private final UsedAlbumService usedAlbumService;



	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);


	}

	@Bean
	CommandLineRunner run(UsedAlbumService usedAlbumService) {
		return args -> {
			usedAlbumService.saveAlbum(new UsedAlbum(null, "Goodbye Yellow Brick Road", 1973, "Elton John",
					List.of("Rock", "Pop"), 17, 76,
					"https://upload.wikimedia.org/wikipedia/en/8/86/Elton_John_-_Goodbye_Yellow_Brick_Road.jpg",
					25.99, "Very Good"));
			usedAlbumService.saveAlbum(new UsedAlbum(null, "Goodbye Yellow Brick Road", 1973, "Elton John",
					List.of("Rock", "Pop"), 17, 76,
					"https://upload.wikimedia.org/wikipedia/en/8/86/Elton_John_-_Goodbye_Yellow_Brick_Road.jpg",
					25.99, "Very Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Blackstar", 2016, "David Bowie",
					List.of("Art Rock", "Jazz"), 7, 41,
					"https://upload.wikimedia.org/wikipedia/en/thumb/2/2d/Blackstar_%28Front_Cover%29.svg/1200px-Blackstar_%28Front_Cover%29.svg.png",
					29.99, "Excellent"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Fates", 2007, "Dr. Dog",
					List.of("Indie Rock", "Psychedelic Rock"), 12, 37,
					"https://upload.wikimedia.org/wikipedia/en/1/1f/Drdog_fate_splash.jpg",
					19.99, "Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Easy Beat", 2005, "Dr. Dog",
					List.of("Indie Rock", "Lo-Fi"), 10, 32,
					"https://upload.wikimedia.org/wikipedia/en/thumb/6/61/Drdog_easybeat.jpg/220px-Drdog_easybeat.jpg",
					15.99, "Fair"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Bridge Over Troubled Water", 1970, "Simon & Garfunkel",
					List.of("Folk Rock", "Pop"), 11, 37,
					"https://upload.wikimedia.org/wikipedia/en/4/41/Simon_and_Garfunkel%2C_Bridge_over_Troubled_Water_%281970%29.png",
					22.99, "Very Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Monsters of Folk", 2009, "Monsters of Folk",
					List.of("Folk Rock", "Indie"), 15, 55,
					"https://upload.wikimedia.org/wikipedia/en/8/83/MOFalbum.jpg",
					20.99, "Excellent"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Surfer Rosa", 1988, "Pixies",
					List.of("Alternative Rock", "Indie Rock"), 9, 33,
					"https://upload.wikimedia.org/wikipedia/en/3/34/SurferRosa.jpg",
					18.99, "Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "The Pressure Is On", 1981, "Hank Williams Jr.",
					List.of("Country"), 10, 32,
					"https://upload.wikimedia.org/wikipedia/en/e/eb/ThePressureIsOn.jpg",
					14.99, "Fair"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Gunfighter Ballads and Trail Songs", 1959, "Marty Robbins",
					List.of("Country", "Western"), 12, 35,
					"https://m.media-amazon.com/images/I/61vK2tABN7L.jpg",
					19.99, "Very Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Cold Spring Harbor", 1971, "Billy Joel",
					List.of("Soft Rock", "Pop"), 10, 30,
					"https://upload.wikimedia.org/wikipedia/en/8/85/Cold_Spring_Harbor_album_cover.jpg",
					17.99, "Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Don't Shoot Me I'm Only the Piano Player", 1973, "Elton John",
					List.of("Rock", "Pop"), 10, 40,
					"https://upload.wikimedia.org/wikipedia/en/6/6e/Elton_John_-_Don%27t_Shoot_Me_I%27m_Only_the_Piano_Player.jpg",
					21.99, "Very Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Space Oddity", 1969, "David Bowie",
					List.of("Rock", "Psychedelic Rock"), 10, 40,
					"https://upload.wikimedia.org/wikipedia/en/b/b9/DavidBowiePhilips.jpg",
					23.99, "Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "The Boy and the Heron", 2023, "Various",
					List.of("Soundtrack"), 15, 50,
					"https://media.pitchfork.com/photos/659f0f93ff94bee560eef2f8/master/pass/Joe%20Hisaishi-The%20Boy%20and%20the%20Heron%20OST.jpeg",
					25.99, "Excellent"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Fantastic Mr. Fox", 2009, "Various",
					List.of("Soundtrack"), 12, 45,
					"https://upload.wikimedia.org/wikipedia/en/a/ad/Fantastic_Mr._Fox_soundtrack_cover.jpg",
					19.99, "Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "Taxi Driver", 1976, "Various",
					List.of("Soundtrack"), 10, 35,
					"https://m.media-amazon.com/images/I/716WOYVfv9L._SY200_QL15_.jpg",
					22.99, "Very Good"));

			usedAlbumService.saveAlbum(new UsedAlbum(null, "The Big Lebowski", 1998, "Various",
					List.of("Soundtrack"), 14, 45,
					"https://m.media-amazon.com/images/I/714UX0Md65L._UF1000,1000_QL80_.jpg",
					24.99, "Excellent"));


		};
	}

}
