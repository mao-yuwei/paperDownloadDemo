package com.pdl.gdkoala.resp.ebookpaper;

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
public class EbookPaper implements Serializable {

	private String paperId;
	
	private String paperName;

	private Long paperCreateTime;
	
	private List<Ebook> list;
	
}
