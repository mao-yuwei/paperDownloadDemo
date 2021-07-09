package com.pdl.gdkoala.resp.paperjson;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * NeedingAttention
 * @author yangwei
 *
 */
@Getter
@Setter
public class NeedingAttention implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2087270927750218438L;

	private boolean needingAttentionExist = true;
	
	private String [] attention = {"1、", "2、"};
	
}
