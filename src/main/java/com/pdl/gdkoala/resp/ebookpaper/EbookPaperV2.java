package com.pdl.gdkoala.resp.ebookpaper;

import com.tutorial.errorbook.feign.vo.CommQuesDetailVo;
import com.tutorial.errorbook.feign.vo.ErrorBookPaperVo;
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
public class EbookPaperV2 implements Serializable {

	private ErrorBookPaperVo errorBookPaperVo;
	
	private List<CommQuesDetailVo> commQuesDetailVos;
	
}
