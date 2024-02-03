package com.alexpages.secondapp.external.domain.firstapp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDTO {

	private Long id;
	private String message;
}
