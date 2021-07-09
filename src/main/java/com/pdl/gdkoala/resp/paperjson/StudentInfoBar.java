package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * StudentInfoBar
 * @author yangwei
 *
 */
@Getter
@Setter
public class StudentInfoBar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1883897097609167004L;

	private boolean studentInfoBarExist = true;
	
	private String studentInfoBarName;
	
}