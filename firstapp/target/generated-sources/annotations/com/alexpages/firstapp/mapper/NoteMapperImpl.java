package com.alexpages.firstapp.mapper;

import com.alexpages.firstapp.domain.NoteDTO;
import com.alexpages.firstapp.entity.NoteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T10:23:25+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public List<NoteDTO> toNoteDTOList(List<NoteEntity> noteEntites) {
        if ( noteEntites == null ) {
            return null;
        }

        List<NoteDTO> list = new ArrayList<NoteDTO>( noteEntites.size() );
        for ( NoteEntity noteEntity : noteEntites ) {
            list.add( toNoteDTO( noteEntity ) );
        }

        return list;
    }

    @Override
    public NoteDTO toNoteDTO(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }

        NoteDTO.NoteDTOBuilder noteDTO = NoteDTO.builder();

        noteDTO.id( noteEntity.getId() );
        noteDTO.message( noteEntity.getMessage() );

        return noteDTO.build();
    }
}
