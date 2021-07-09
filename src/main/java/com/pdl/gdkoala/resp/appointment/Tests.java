package com.pdl.gdkoala.resp.appointment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.errorbook.feign.vo.QuesKnowledgeVo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
public class Tests implements Serializable {

	//题id(菁优、大题库)
	private Integer id;
	//题干
	private String stem;
	//选项a
	private String optiona;
	//选项b
	private String optionb;
	//选项c
	private String optionc;
	//选项d
	private String optiond;
	//选项e
	private String optione;
	//选项f
	private String optionf;
	//来源
	private String label;
	//本地来源（菁优、大题库）
	private Integer source;
	//难度系数
	private BigDecimal degree;
	//知识点
	private List<String> points;
	//答案
	private String answers;
	//解答过程
	private String method;
	//解析
	private String analyse;
	//注意点
	private String discuss;
	//题型id
	private Integer cate;
	//题型名称
	private String cateName;
	//学科
	private Integer subjectId;
	//知识点
	private List<QuesKnowledgeVo> quesKnowledges = new ArrayList<>();
	//类别（1.错题 2 相似题 3.作业）
	private Integer category;
	//错题id
	private Integer wrongId;
	//id排序
	private Integer idSort;
	//wrongId排序
	private Integer wrongIdSort;

//	/**
//	 *
//	 */
//	private static final long serialVersionUID = -1834246256793977664L;
//
//	private String id;
//
//	private String originalId;
//
//	private String testId;
//
//	private Integer source;
//
//	private String stem;
//
//	private String OptionF;
//
//	private String OptionE;
//
//	private String OptionD;
//
//	private String OptionC;
//
//	private String OptionB;
//
//	private String OptionA;
//
//	private String answer;
//
//	private Long creatTime;
	
}