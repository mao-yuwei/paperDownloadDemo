package com.pdl.gdkoala.resp.appointment;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class AppointmentInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9080581966797339626L;

	private String appointmentId;
	
	private Integer course;
	
	private Integer type;
	
	private Integer status;
	
	private String studentId;
	
	private String teacherId;
	
	private String dollorsId;
	
	private String teacherName;
	
	private String dollorsName;
	
	private Long appointmentDate;
	
	private Long startTime;
	
	private Long endTime;
	
}