package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * SubTitle
 * @author yangwei
 *
 */
@Getter
@Setter
public class SubTitle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8596338503829187225L;

	private boolean subTitleExist = true;
	
	private String subTitleName;
	
}
