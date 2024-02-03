package com.alexpages.firstapp.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteRequest;
import com.alexpages.firstapp.domain.PlaceNoteResponse;
import com.alexpages.firstapp.error.NoteManagerException500;

@Validated
public interface FirstAppApi {

	@PostMapping(value = { "/note" }, produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<PlaceNoteResponse> placeNote(@RequestBody PlaceNoteRequest placeNoteRequest)
	throws NoteManagerException500;

	@GetMapping(value = { "/note" }, produces = { "application/json" })
	ResponseEntity<List<NoteDTO>> getNotes()
	throws NoteManagerException500;
	
	@GetMapping(value = { "/note/{noteId}" }, produces = { "application/json" })
	ResponseEntity<NoteDTO> getNoteById(@PathVariable("noteId") long noteId)
	throws NoteManagerException500;
}
