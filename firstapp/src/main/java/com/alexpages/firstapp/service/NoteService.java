package com.alexpages.firstapp.service;

import java.util.List;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteRequest;
import com.alexpages.firstapp.entity.NoteEntity;

import lombok.NonNull;

public interface NoteService 
{

	public Long placeNote(@NonNull PlaceNoteRequest placeNoteRequest);

	public List<NoteDTO> getNotes();

	public NoteDTO getNoteById(@NonNull Long noteId);
	
}
