package com.pdl.gdkoala.resp.homework;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4881505218296959364L;

	private String quesId;
	
	private String question;
	
	private String optionA;
	
	private String optionB;
	
	private String optionC;
	
	private String optionD;
	
	private String optionE;
	
	private String optionF;
	
	private String answerObj;
	
	/**
	 * 答案
	 */
	private String answerSbj;
	
	/**
	 * 解析
	 */
	private String parse;
	
	private String quesType;
	
	private String subjectId;
	
	private String answer;
	
	private Long createTime;
	
	private String isExcellent;
	
}
