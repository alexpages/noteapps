package com.alexpages.secondapp.external.domain.firstapp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor
public class NoteDTO {

	private Long id;
	private String message;
}
