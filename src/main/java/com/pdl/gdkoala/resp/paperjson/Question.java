package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Question
 * @author yangwei
 *
 */
@Getter
@Setter
public class Question implements Serializable {
	
	private static final long serialVersionUID = 528161179245353787L;

	/**
	 * 题干
	 */
	private String questionContent;

	/**
	 * 答案
	 */
	private String questionAnswer;


	/**
	 * 知识点
	 */
	private List<QuestionLabel> questionLabelList;
	
	/**
	 * 选项
	 */
	private String questionSelection;
	
	/**
	 * 解析
	 */
	private String questionAnswerInfo;
	
	/**
	 * 错题编号
	 */
	private String questionSource;

	/**
	 * 题目难易程度
	 */
	private Integer questionDifficulty;

	private boolean hasDiv = true;
	
}