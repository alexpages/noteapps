package com.alexpages.firstapp.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDTO {

	private Long id;
	private String message;
}
