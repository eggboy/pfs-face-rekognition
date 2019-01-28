package io.pivotal.pfs.imagedemo;

import io.pivotal.pfs.imagedemo.faces.DetectFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class ImageDemoApplication {
	@Autowired
	DetectFaces detectFaces;

	public static void main(String[] args) {
		SpringApplication.run(ImageDemoApplication.class, args);
	}

	Function<String, String> getAgeRangeFromImage() {
		return (image) -> detectFaces.requestFaceDetection(image)
				.getFaceDetails()
				.stream()
				.map(fd -> "Your Age Range is between " + fd.getAgeRange().getLow() + " and " + fd.getAgeRange().getHigh()+"\n")
				.collect(Collectors.joining());
	}
}

