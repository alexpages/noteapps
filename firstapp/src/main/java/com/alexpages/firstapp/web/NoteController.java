package com.alexpages.firstapp.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.alexpages.firstapp.api.FirstAppApi;
import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteRequest;
import com.alexpages.firstapp.domain.PlaceNoteResponse;
import com.alexpages.firstapp.error.NoteManagerException404;
import com.alexpages.firstapp.error.NoteManagerException500;
import com.alexpages.firstapp.service.impl.NoteServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class NoteController implements FirstAppApi {

	private final NoteServiceImpl noteServiceImpl;

	@Override
	public ResponseEntity<PlaceNoteResponse> placeNote(@Validated PlaceNoteRequest placeNoteRequest) 
	throws NoteManagerException500 
	{
		Long id = noteServiceImpl.placeNote(placeNoteRequest);
		return new ResponseEntity<>(PlaceNoteResponse.builder().id(id).build(),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<NoteDTO>> getNotes() 
	throws NoteManagerException500 
	{
		List<NoteDTO> lNoteDTO =  noteServiceImpl.getNotes();
		return new ResponseEntity<>(lNoteDTO,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteDTO> getNoteById(long noteId) 
	throws NoteManagerException500 
	{
		NoteDTO noteDTO=  noteServiceImpl.getNoteById(noteId);
		if (noteDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(noteDTO, HttpStatus.OK);
	}	
}