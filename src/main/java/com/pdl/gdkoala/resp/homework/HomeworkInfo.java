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
public class HomeworkInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4095201596995115664L;

	private String id;
	
	private String teacherId;
	
	private String stuId;
	
	private String subject;
	
	private String homeworkStatus;
	
	private String source;
	
	private String pattern;
	
	private String teacherName;
	
	private String tickTopic;
	
	private String remark;
	
	private String createTime;
	
	private String endTime;
	
	private String submitTime;
	
	private String homeworkName;
	
}
