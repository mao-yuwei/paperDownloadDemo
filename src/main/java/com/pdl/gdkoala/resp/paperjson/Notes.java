package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Notes
 * @author yangwei
 *
 */
@Getter
@Setter
public class Notes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 108700145294915524L;

	private String title = "";
	
	private String notes = "";
	
}
