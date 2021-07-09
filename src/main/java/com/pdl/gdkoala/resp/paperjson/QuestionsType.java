package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * QuestionsType
 * @author yangwei
 *
 */
@Getter
@Setter
public class QuestionsType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4010144254072141737L;

	private Integer questionTypeCategory = 111;
	
	private String questionFristType = "题型";
	
	private String questionSecondType;
	
	private String questionDesc;
	
	private String questionTypeNote;
	
	private Integer thisTypeSize = 4;
	
	private List<Question> questionsList;
	
}