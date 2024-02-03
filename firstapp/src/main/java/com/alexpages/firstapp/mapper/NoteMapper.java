package com.alexpages.firstapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.entity.NoteEntity;

@Mapper(componentModel = "spring")
public interface NoteMapper {
		
	List<NoteDTO> toNoteDTOList(List<NoteEntity> noteEntites);
	
	NoteDTO toNoteDTO(NoteEntity noteEntity);
}
