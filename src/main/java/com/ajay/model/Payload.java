package com.ajay.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243587178426637541L;
	private String message;
	private HttpStatus Status;
	private Object data;
}
