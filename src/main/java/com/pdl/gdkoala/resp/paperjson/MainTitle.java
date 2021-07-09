package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * MainTitle
 * @author yangwei
 *
 */
@Getter
@Setter
public class MainTitle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6355719868305482819L;

	private boolean mainTitleExist = true;
	
	/**
	 * 试卷/作业名称
	 */
	private String mainTitleName;
	
}