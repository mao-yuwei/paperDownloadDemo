package com.pdl.gdkoala.resp.appointment;

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
public class AppointmentResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8213981406881572131L;

	private List<Tests> paperInfo;
	
	private AppointmentInfo appointmentInfo;
	
}
