package com.alexpages.secondapp.config;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.alexpages.secondapp.external.domain.firstapp.NoteDTO;
import com.alexpages.secondapp.external.domain.firstapp.PlaceNoteRequest;
import com.alexpages.secondapp.external.domain.firstapp.PlaceNoteResponse;

import reactor.core.publisher.Mono;

@SpringBootTest
class SecondappConfigTest {

	@Qualifier("secondapp")
	@Autowired
	private WebClient webClient;

	@Test
	void testPostTwoNotes() 
	{
		PlaceNoteRequest requestNote1 = new PlaceNoteRequest("first message");
		PlaceNoteRequest requestNote2 = new PlaceNoteRequest("second message");

		webClient.post()
			.uri("/note")
			.body(Mono.just(requestNote1), PlaceNoteRequest.class)
			.retrieve()
			.bodyToMono(Void.class).block();

		webClient.post()
			.uri("/note")
			.body(Mono.just(requestNote2), PlaceNoteRequest.class)
			.retrieve()
			.bodyToMono(Void.class).block();

		System.out.println("testPostTwoNotes > Success, the notes have been saved successfully");
	}

	@Test
	void testRetrieveAllNotes() 
	{
		List<NoteDTO> lnoteDTO = webClient.get()
				.uri("/note")
				.retrieve()
				.bodyToFlux(NoteDTO.class).collectList().block();
		
		System.out.println("testRetrieveAllNotes > Retrieved notes: " + lnoteDTO);
	}
}
