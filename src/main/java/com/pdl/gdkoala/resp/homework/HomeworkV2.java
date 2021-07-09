package com.pdl.gdkoala.resp.homework;

import com.tutorial.errorbook.feign.vo.CommQuesDetailVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class HomeworkV2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6413016066483091647L;

	private HomeworkInfo homeworkInfo;
	
	private List<CommQuesDetailVo> topicInfo;
	
}
