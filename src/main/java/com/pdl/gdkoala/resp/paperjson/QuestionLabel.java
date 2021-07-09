package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * QuestionLabel
 * @author yangwei
 *
 */
@Getter
@Setter
public class QuestionLabel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -127437655178302002L;

	private String labelName;
	
	private String importance;
	
}