package io.pivotal.pfs.imagedemo;

import io.pivotal.pfs.imagedemo.faces.DetectFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class ImageDemoApplication {
	@Autowired
	DetectFaces detectFaces;

	public static void main(String[] args) {
		SpringApplication.run(ImageDemoApplication.class, args);
	}

	@Bean
	Function<String, String> getAgeRangeFromImage() {
		return (image) -> detectFaces.requestFaceDetection(image)
				.getFaceDetails()
				.stream()
				.map(fd ->  "You must be " + fd.getGender().getValue() +"(Confidence: " + fd.getGender().getConfidence() + "), And You look around " + fd.getAgeRange().getLow() + " to " + fd.getAgeRange().getHigh()+" years old. \n")
				.collect(Collectors.joining());
	}
}

