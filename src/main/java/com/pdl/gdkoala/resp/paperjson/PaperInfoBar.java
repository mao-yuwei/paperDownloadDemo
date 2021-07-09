package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * PaperInfoBar
 * @author yangwei
 *
 */
@Getter
@Setter
public class PaperInfoBar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2695416307489365818L;

	private boolean paperInfoBarExist = true;
	
	private String paperInfoBarName;
	
}