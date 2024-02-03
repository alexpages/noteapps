package com.alexpages.firstapp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteRequest;
import com.alexpages.firstapp.entity.NoteEntity;
import com.alexpages.firstapp.error.NoteManagerException500;
import com.alexpages.firstapp.mapper.NoteMapper;
import com.alexpages.firstapp.repository.NoteRepository;

import lombok.NonNull;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

	@InjectMocks
	private NoteServiceImpl noteServiceImpl;
	@Mock
	private NoteRepository noteRepository;
	@Mock
	private NoteMapper noteMapper;

	private EasyRandom easyRandom;

	@BeforeEach
	public void setUp() {
		easyRandom = new EasyRandom();
	}

	@Test
	void testPlaceNoteSuccess() 
	{
		when(noteRepository.save(any())).thenReturn(easyRandom.nextObject(NoteEntity.class));
		assertNotNull(noteServiceImpl.placeNote(generateValidPlaceNoteRequest()));
	}
	
	@Test
	void testPlaceNoteError_1() 
	{
		when(noteRepository.save(any())).thenThrow(new RuntimeException("some error"));
		assertThrows(NoteManagerException500.class, () -> noteServiceImpl.placeNote(generateValidPlaceNoteRequest()));
	}
	
	@Test
	void testPlaceNoteError_2() 
	{
		assertThrows(NullPointerException.class, () -> noteServiceImpl.placeNote(null));
	}
	
	@Test
	void testGetNotesSuccess() 
	{
		when(noteRepository.findAll()).thenReturn(easyRandom.objects(NoteEntity.class, 2).collect(Collectors.toList()));
		when(noteMapper.toNoteDTOList(any())).thenReturn(easyRandom.objects(NoteDTO.class, 2).collect(Collectors.toList()));
		assertNotNull(noteServiceImpl.getNotes());
	}
	
	@Test
	void testGetNotesError() 
	{	
		when(noteRepository.findAll()).thenThrow(new RuntimeException("some error"));
		assertThrows(NoteManagerException500.class, () -> noteServiceImpl.getNotes());
	}
	
	@Test
	void testGetNoteByIdSuccess() 
	{	
		when(noteRepository.findById(any(Long.class))).thenReturn(Optional.of(easyRandom.nextObject(NoteEntity.class)));
		when(noteMapper.toNoteDTO(any())).thenReturn(easyRandom.nextObject(NoteDTO.class));
		assertNotNull(noteServiceImpl.getNoteById(1L));
	}
	
	@Test
	void testGetNoteByIdError() 
	{	
		when(noteRepository.findById(any(Long.class))).thenThrow(new RuntimeException("some error"));
		assertThrows(NoteManagerException500.class, () -> noteServiceImpl.getNoteById(1L));
	}
	
	
	@Test
	void testGetNoteByIdError_2() 
	{	
		assertThrows(NullPointerException.class, () -> noteServiceImpl.getNoteById(null));
	}
	
	@Test
	void testGetNoteById_NotFound() 
	{	
		when(noteRepository.findById(any(Long.class))).thenReturn(Optional.empty());
		assertNull(noteServiceImpl.getNoteById(1L));
	}

	private PlaceNoteRequest generateValidPlaceNoteRequest()
	{
		return PlaceNoteRequest.builder().message("any message to be saved").build();
	}
}
