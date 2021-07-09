package com.pdl.gdkoala.resp.ebookpaper;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class Ebook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937893104359903578L;

	private String testId;
	
	private String paperId;
	
	private String stem;
	
	private String optionA;
	
	private String optionB;
	
	private String optionC;
	
	private String optionD;
	
	private String optionE;
	
	private String optionF;
	
	/**
	 * 答案
	 */
	private String answer;
	
	/**
	 * 解析
	 */
	private String parse;
	
}
