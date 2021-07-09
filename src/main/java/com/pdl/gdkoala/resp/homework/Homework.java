package com.pdl.gdkoala.resp.homework;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class Homework implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6413016066483091647L;

	private HomeworkInfo homeworkInfo;
	
	private List<Topic> topicInfo;
	
}
