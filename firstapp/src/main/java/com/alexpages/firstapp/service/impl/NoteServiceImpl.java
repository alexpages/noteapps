package com.alexpages.firstapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.domain.PlaceNoteRequest;
import com.alexpages.firstapp.entity.NoteEntity;
import com.alexpages.firstapp.error.NoteManagerException404;
import com.alexpages.firstapp.error.NoteManagerException500;
import com.alexpages.firstapp.mapper.NoteMapper;
import com.alexpages.firstapp.repository.NoteRepository;
import com.alexpages.firstapp.service.NoteService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
	
	private final NoteRepository noteRepository;
	private final NoteMapper noteMapper;
	
	@Override
	public Long placeNote(@NonNull PlaceNoteRequest placeNoteRequest) 
	{
		try {
			NoteEntity noteEntity = noteRepository.save(NoteEntity
					.builder()
					.message(placeNoteRequest.getMessage())
					.build());
			return noteEntity.getId();
		
		} catch (Exception e) {
			log.error("NoteServiceImpl > placeNote > Note could not be saved");
			throw new NoteManagerException500("NoteServiceImpl > placeNote > Note could not be saved");
		}
	}

	@Override
	public List<NoteDTO> getNotes() 
	{
		try {		
			List<NoteDTO> lNoteDTOs = noteMapper.toNoteDTOList(noteRepository.findAll());
			log.info("NoteServiceImpl > getNotes > {}", lNoteDTOs);
			return lNoteDTOs;

		} catch (Exception e) {
			log.error("NoteServiceImpl > getNotes > Notes could not be retrieved");
			throw new NoteManagerException500("NoteServiceImpl > getNotes > Notes could not be retrieved");
		}
	}

	@Override
	public NoteDTO getNoteById(@NonNull Long noteId) 
	{
		try {
			Optional<NoteEntity> noteEntity = noteRepository.findById(noteId);
			if (noteEntity.isPresent())	{
				return 	noteMapper.toNoteDTO(noteEntity.get());
			}
			return null;
		} catch (Exception e) {
			log.error("NoteServiceImpl > getNoteById > There was an error retrievind Note with ID: " + noteId);
			throw new NoteManagerException500("NoteServiceImpl > getNoteById > There was an error retrieving Note with ID: " + noteId, e);
		}
	}
}