package com.alexpages.firstapp.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteResponse;
import com.alexpages.firstapp.entity.NoteEntity;
import com.alexpages.firstapp.service.impl.NoteServiceImpl;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

	@MockBean
	private NoteServiceImpl noteServiceImpl;

	private EasyRandom easyRandom;
	private static MockMvc mockMvc;

	public HttpHeaders header() {
		return new HttpHeaders();
	}

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new NoteController(noteServiceImpl)).build();
		RestAssuredMockMvc.mockMvc(mockMvc);
		easyRandom = new EasyRandom();
	}

	@Test
    void testPlaceNote_200() 
	throws Exception 
    {
    	when(noteServiceImpl.placeNote(any())).thenReturn(1L); 	
        mockMvc.perform(post("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header())
                .content(getValidPlaceNoteRequest()))
        .andExpect(status().is(HttpStatus.CREATED.value()));
    }	@Test
    
    void testGetNotes_200() 
	throws Exception 
    {
    	when(noteServiceImpl.getNotes()).thenReturn(easyRandom.objects(NoteDTO.class, 2).collect(Collectors.toList())); 	
        mockMvc.perform(get("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header())
                .content(""))
        .andExpect(status().is(HttpStatus.OK.value()));
    }
    
    @Test
    void testGetNoteById_200() 
	throws Exception 
    {
        when(noteServiceImpl.getNoteById(1L)).thenReturn(easyRandom.nextObject(NoteDTO.class)); 	
        mockMvc.perform(get("/note/{noteId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header())
                .content(""))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetNoteById_400() 
	throws Exception 
    {
        when(noteServiceImpl.getNoteById(1L)).thenReturn(null); 	
        mockMvc.perform(get("/note/{noteId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header())
                .content(""))
                .andExpect(status().isNotFound());
    }

	private String getValidPlaceNoteRequest() {
	    return "{\n" + 
	               "  \"message\": \"random message\"\n" + 
	           "}";
	}
}
