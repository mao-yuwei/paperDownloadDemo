package com.pdl.gdkoala.resp.paperjson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangwei
 *
 */
@Getter
@Setter
@ToString
public class PaperJsonObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3514288127444064341L;

	private List<QuestionsType> questionsTypeList;
	
	private String paperStruType = "1";
	
	private MainTitle mainTitle;
	
	private SubTitle subTitle;
	
	private boolean bindingLine = false;
	
	/**
	 * 题后是否分页
	 */
	private boolean securityMark = false;

	private PaperInfoBar paperInfoBar;
	
	private StudentInfoBar studentInfoBar;
	
	private boolean gradeBar = true;
	
	private NeedingAttention needingAttention;
	
	private SubPaperAndNotes subPaperAndNotes;
	
	private boolean questionAndNotes = true;
	
	private boolean qGradeBar = true;
	
	private Integer fontSize = 2;
	
	private String paperSizeType = "A4";
	
	private String answerType = "1";
	
	private String docType = "docx";
	
	/**
	 * testsName.docx
	 */
	private String testsName;
	
	private String paperType;
	
	private String QRCodeUrl;
	
	private String studentId;

	/**
	 * 题后是否空五行
	 */
	private boolean devideFiveLine = false;
	
}
