package com.pdl.gdkoala.service.imp;

import com.alibaba.fastjson.JSON;
import com.pdl.gdkoala.feign.CorrectionFeignService;
import com.pdl.gdkoala.feign.ErrorBookPaperFeignService;
import com.pdl.gdkoala.resp.appointment.AppointmentInfo;
import com.pdl.gdkoala.resp.appointment.AppointmentResp;
import com.pdl.gdkoala.resp.appointment.Tests;
import com.pdl.gdkoala.resp.ebookpaper.Ebook;
import com.pdl.gdkoala.resp.ebookpaper.EbookPaperV2;
import com.pdl.gdkoala.resp.homework.HomeworkInfo;
import com.pdl.gdkoala.resp.homework.HomeworkV2;
import com.pdl.gdkoala.resp.homework.Topic;
import com.pdl.gdkoala.resp.paperjson.*;
import com.pdl.gdkoala.service.PaperService;
import com.tutorial.errorbook.feign.vo.CommQuesDetailVo;
import com.tutorial.errorbook.feign.vo.ErrorBookPaperVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yangwei
 */
@Service
public class PaperServiceImp implements PaperService {

	private static Logger logger = LoggerFactory.getLogger(PaperServiceImp.class);
	
	@Autowired
	private CorrectionFeignService correctionFeignService;

	@Autowired
	private ErrorBookPaperFeignService errorBookPaperFeignService;

	/* (non-Javadoc) getPaperInfo
	 * @see com.pdl.gdkoala.service.PaperService#getPaperInfo(java.lang.String)
	 */
	@Override
	public PaperJsonObj getPaperInfo(String subscribeId) {
		// TODO Auto-generated method stub
		Map<String, Object> paperInfo = correctionFeignService.getAllInfoForOfflineCourseQR(subscribeId).getData();
		
		String paperInfoString = JSON.toJSONString(paperInfo);
		logger.info(paperInfoString);
		
		PaperJsonObj pjObj = new PaperJsonObj();
		
		List<QuestionsType> questionsTypeList = new ArrayList<QuestionsType>(1);
		QuestionsType questionsType = new QuestionsType();
		List<Question> questionsList = new ArrayList<Question>();
		
		AppointmentResp amResp = JSON.parseObject(paperInfoString, AppointmentResp.class);
		
		List<Tests> paperInfos = amResp.getPaperInfo();
		AppointmentInfo appointmentInfo = amResp.getAppointmentInfo();
		
		pjObj.setStudentId(appointmentInfo.getStudentId());
		
		if (CollectionUtils.isEmpty(paperInfos) || StringUtils.isBlank(appointmentInfo.getDollorsId())) {
			return null;
		}
		
		for (Tests tests : paperInfos) {
			Question question = new Question();
			
			question.setQuestionSource("???????????????" + tests.getId());
			question.setQuestionContent(tests.getStem());
			if (!StringUtils.contains(question.getQuestionContent(), "div")) {
				question.setHasDiv(false);
			}
			
			// selections
			setQuestionSelection(tests, question);
			
			question.setQuestionAnswer(tests.getMethod());
//				question.setQuestionAnswerInfo(tests.getAnswer());
			
			questionsList.add(question);
		}
		
		questionsType.setQuestionsList(questionsList);
		
		questionsTypeList.add(questionsType);
		
		pjObj.setQuestionsTypeList(questionsTypeList);
		
		MainTitle mainTitle = new MainTitle();
		mainTitle.setMainTitleName(appointmentInfo.getDollorsName());
		pjObj.setMainTitle(mainTitle);
		
		SubTitle subTitle = new SubTitle();
		subTitle.setSubTitleName("<div><br /> <img alt=\"\" src=\"http://tutorial-files-bucket.oss-cn-hangzhou.aliyuncs.com/tikuimages/2/2013/700/xuekubao37/af48154f-937e-11e9-970a-b42e9921e93e_xkb5.png\" style=\"vertical-align:middle\" w=\"176px\" h=\"189px\" /> <br /></div>");
		pjObj.setSubTitle(subTitle);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		PaperInfoBar paperInfoBar = new PaperInfoBar();
		paperInfoBar.setPaperInfoBarName(sdf.format(new Date(appointmentInfo.getAppointmentDate())) + " ??????????????????" + appointmentInfo.getDollorsId());
		pjObj.setPaperInfoBar(paperInfoBar);
		
		pjObj.setTestsName("errorbook" + appointmentInfo.getDollorsId());
		
		logger.info("???????????????????????????PaperJsonObj=="+JSON.toJSONString(pjObj));
		
		return pjObj;
	}

	/**
	 * ??????????????????
	 * @param tests
	 * @param question
	 */
	private void setQuestionSelection(Tests tests, Question question) {
		StringBuilder qSelection = new StringBuilder("<span class=\"quesborder\"><table style=\"width:100%\" class=\"ques quesborder\"><tbody>");
		
		String optionA = tests.getOptiona();
		if (StringUtils.isNotBlank(optionA)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("A." + optionA);
			qSelection.append("</label></td></tr>");
		}
		
		String optionB = tests.getOptionb();
		if (StringUtils.isNotBlank(optionB)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("B." + optionB);
			qSelection.append("</label></td></tr>");
		}
		
		String optionC = tests.getOptionc();
		if (StringUtils.isNotBlank(optionC)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("C." + optionC);
			qSelection.append("</label></td></tr>");
		}
		
		String optionD = tests.getOptiond();
		if (StringUtils.isNotBlank(optionD)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("D." + optionD);
			qSelection.append("</label></td></tr>");
		}
		
		String optionE = tests.getOptione();
		if (StringUtils.isNotBlank(optionE)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("E." + optionE);
			qSelection.append("</label></td></tr>");
		}
		
		String optionF = tests.getOptionf();
		if (StringUtils.isNotBlank(optionF)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("F." + optionF);
			qSelection.append("</label></td></tr>");
		}
		
		qSelection.append("</tbody></table></span>");
		question.setQuestionSelection(qSelection.toString());
	}
	
	/**
	 * ??????????????????
	 * @param tests
	 * @param question
	 */
	private void setQuestionSelection(Ebook tests, Question question) {
		StringBuilder qSelection = new StringBuilder("<span class=\"quesborder\"><table style=\"width:100%\" class=\"ques quesborder\"><tbody>");
		
		String optionA = tests.getOptionA();
		if (StringUtils.isNotBlank(optionA)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("A." + optionA);
			qSelection.append("</label></td></tr>");
		}
		
		String optionB = tests.getOptionB();
		if (StringUtils.isNotBlank(optionB)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("B." + optionB);
			qSelection.append("</label></td></tr>");
		}
		
		String optionC = tests.getOptionC();
		if (StringUtils.isNotBlank(optionC)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("C." + optionC);
			qSelection.append("</label></td></tr>");
		}
		
		String optionD = tests.getOptionD();
		if (StringUtils.isNotBlank(optionD)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("D." + optionD);
			qSelection.append("</label></td></tr>");
		}
		
		String optionE = tests.getOptionE();
		if (StringUtils.isNotBlank(optionE)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("E." + optionE);
			qSelection.append("</label></td></tr>");
		}
		
		String optionF = tests.getOptionF();
		if (StringUtils.isNotBlank(optionF)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("F." + optionF);
			qSelection.append("</label></td></tr>");
		}
		
		qSelection.append("</tbody></table></span>");
		question.setQuestionSelection(qSelection.toString());
	}
	
	/**
	 * ??????????????????
	 * @param tests
	 * @param question
	 */
	private void setQuestionSelection(Topic tests, Question question) {
		StringBuilder qSelection = new StringBuilder("<span class=\"quesborder\"><table style=\"width:100%\" class=\"ques quesborder\"><tbody>");
		
		String optionA = tests.getOptionA();
		if (StringUtils.isNotBlank(optionA)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("A." + optionA);
			qSelection.append("</label></td></tr>");
		}
		
		String optionB = tests.getOptionB();
		if (StringUtils.isNotBlank(optionB)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("B." + optionB);
			qSelection.append("</label></td></tr>");
		}
		
		String optionC = tests.getOptionC();
		if (StringUtils.isNotBlank(optionC)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("C." + optionC);
			qSelection.append("</label></td></tr>");
		}
		
		String optionD = tests.getOptionD();
		if (StringUtils.isNotBlank(optionD)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("D." + optionD);
			qSelection.append("</label></td></tr>");
		}
		
		String optionE = tests.getOptionE();
		if (StringUtils.isNotBlank(optionE)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("E." + optionE);
			qSelection.append("</label></td></tr>");
		}
		
		String optionF = tests.getOptionF();
		if (StringUtils.isNotBlank(optionF)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("F." + optionF);
			qSelection.append("</label></td></tr>");
		}
		
		qSelection.append("</tbody></table></span>");
		question.setQuestionSelection(qSelection.toString());
	}

	/**
	 * ??????????????????v2
	 * @param tests
	 * @param question
	 */
	private void setQuestionSelectionV2(CommQuesDetailVo tests, Question question) {
		StringBuilder qSelection = new StringBuilder("<span class=\"quesborder\"><table style=\"width:100%\" class=\"ques quesborder\"><tbody>");

		String optionA = tests.getOptiona();
		if (StringUtils.isNotBlank(optionA)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("A." + optionA);
			qSelection.append("</label></td></tr>");
		}

		String optionB = tests.getOptionb();
		if (StringUtils.isNotBlank(optionB)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("B." + optionB);
			qSelection.append("</label></td></tr>");
		}

		String optionC = tests.getOptionc();
		if (StringUtils.isNotBlank(optionC)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("C." + optionC);
			qSelection.append("</label></td></tr>");
		}

		String optionD = tests.getOptiond();
		if (StringUtils.isNotBlank(optionD)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("D." + optionD);
			qSelection.append("</label></td></tr>");
		}

		String optionE = tests.getOptione();
		if (StringUtils.isNotBlank(optionE)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("E." + optionE);
			qSelection.append("</label></td></tr>");
		}

		String optionF = tests.getOptionf();
		if (StringUtils.isNotBlank(optionF)) {
			qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
			qSelection.append("F." + optionF);
			qSelection.append("</label></td></tr>");
		}

		qSelection.append("</tbody></table></span>");
		question.setQuestionSelection(qSelection.toString());
	}

	/* (non-Javadoc) ??????????????????/????????????
	 * @see com.pdl.gdkoala.service.PaperService#getTestsInfo(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public PaperJsonObj getTestsInfo(String paperid, String uid, Integer papertype) {
		// TODO Auto-generated method stub

		PaperJsonObj pjObj = new PaperJsonObj();
		
		List<QuestionsType> questionsTypeList = new ArrayList<QuestionsType>(1);
		pjObj.setQuestionsTypeList(questionsTypeList);
		
		QuestionsType questionsType = new QuestionsType();
		questionsTypeList.add(questionsType);
		
		List<Question> questionsList = new ArrayList<Question>();
		questionsType.setQuestionsList(questionsList);
		
		Map<String, Object> paperInfo = null;
		
		if (1 == papertype) {
			// ??????

			try {
				paperInfo = errorBookPaperFeignService.paperDetailsPrint(Integer.parseInt(paperid)).getData();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logger.info("??????paperInfo??????");
			}

			String paperInfoString = JSON.toJSONString(paperInfo);
			logger.info("errorBookFeignService???????????????" + paperInfoString);
			
			if (null == paperInfo) {
				return null;
			}
			
			EbookPaperV2 ebookPaperV2 = JSON.parseObject(paperInfoString, EbookPaperV2.class);
			ErrorBookPaperVo errorBookPaperVo = ebookPaperV2.getErrorBookPaperVo();

			List<CommQuesDetailVo> commQuesDetailVos = ebookPaperV2.getCommQuesDetailVos();
			if (CollectionUtils.isEmpty(commQuesDetailVos)) {
				return null;
			}
			
			for (CommQuesDetailVo ebook : commQuesDetailVos) {
				Question question = new Question();
				
				question.setQuestionSource("???????????????" + ebook.getId());
				question.setQuestionContent(ebook.getStem());
				if (!StringUtils.contains(question.getQuestionContent(), "div")) {
					question.setHasDiv(false);
				}
				
				// selections
				setQuestionSelectionV2(ebook, question);
				
				question.setQuestionAnswer(ebook.getMethod());
				question.setQuestionAnswerInfo(ebook.getAnalyse());
				
				questionsList.add(question);
			}
			
			MainTitle mainTitle = new MainTitle();
			mainTitle.setMainTitleName(errorBookPaperVo.getPaperName());
			pjObj.setMainTitle(mainTitle);
			
			SubTitle subTitle = new SubTitle();
			subTitle.setSubTitleName("<div><br /> <img alt=\"\" src=\"http://tutorial-files-bucket.oss-cn-hangzhou.aliyuncs.com/tikuimages/2/2013/700/xuekubao37/af48154f-937e-11e9-970a-b42e9921e93e_xkb5.png\" style=\"vertical-align:middle\" w=\"176px\" h=\"189px\" /> <br /></div>");
			pjObj.setSubTitle(subTitle);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			PaperInfoBar paperInfoBar = new PaperInfoBar();
			paperInfoBar.setPaperInfoBarName(sdf.format(errorBookPaperVo.getCreatTime()) + " ??????????????????" + errorBookPaperVo.getPaperId());
			pjObj.setPaperInfoBar(paperInfoBar);
			
			pjObj.setTestsName("errorbook" + errorBookPaperVo.getPaperId());
		} else if (2 == papertype) {
			// ??????
			paperInfo = correctionFeignService.getHomeworkDetailQR(paperid, uid).getData();
			
			String paperInfoString = JSON.toJSONString(paperInfo);
			logger.info("correctionFeignService???????????????" + paperInfoString);
			
			if (null == paperInfo) {
				return null;
			}

			HomeworkV2 homeworkV2 = JSON.parseObject(paperInfoString, HomeworkV2.class);

			List<CommQuesDetailVo> topicInfo = homeworkV2.getTopicInfo();
			if (CollectionUtils.isEmpty(topicInfo)) {
				return null;
			}
			
			for (CommQuesDetailVo topic : topicInfo) {
				Question question = new Question();
				
				question.setQuestionSource("???????????????" + topic.getId());
				question.setQuestionContent(topic.getStem());
				if (!StringUtils.contains(question.getQuestionContent(), "div")) {
					question.setHasDiv(false);
				}
				
				// selections
				setQuestionSelectionV2(topic, question);
				
				question.setQuestionAnswer(topic.getMethod());
				question.setQuestionAnswerInfo(topic.getAnalyse());
				
				questionsList.add(question);
			}
			
			HomeworkInfo homeworkInfo = homeworkV2.getHomeworkInfo();
			
			pjObj.setStudentId(homeworkInfo.getStuId());
			
			MainTitle mainTitle = new MainTitle();
			mainTitle.setMainTitleName(homeworkInfo.getHomeworkName());
			pjObj.setMainTitle(mainTitle);
			
			SubTitle subTitle = new SubTitle();
			subTitle.setSubTitleName("<div><br /> <img alt=\"\" src=\"http://tutorial-files-bucket.oss-cn-hangzhou.aliyuncs.com/tikuimages/2/2013/700/xuekubao37/af48154f-937e-11e9-970a-b42e9921e93e_xkb5.png\" style=\"vertical-align:middle\" w=\"176px\" h=\"189px\" /> <br /></div>");
			pjObj.setSubTitle(subTitle);

			PaperInfoBar paperInfoBar = new PaperInfoBar();
			paperInfoBar.setPaperInfoBarName(homeworkInfo.getCreateTime() + " ??????????????????" + homeworkInfo.getId());
			pjObj.setPaperInfoBar(paperInfoBar);
			
			pjObj.setTestsName("errorbook" + homeworkInfo.getId());
		}
		
		logger.info(JSON.toJSONString(pjObj));
		
		return pjObj;
		
	}

	@Override
	public PaperJsonObj getAllInfoForTeachPlan(String subscribeId) {

		// TODO Auto-generated method stub
		Map<String, Object> paperInfo = correctionFeignService.getAllInfoForTeachPlan(subscribeId).getData();

		String paperInfoString = JSON.toJSONString(paperInfo);
		logger.info(paperInfoString);

		PaperJsonObj pjObj = new PaperJsonObj();

		List<QuestionsType> questionsTypeList = new ArrayList<QuestionsType>(1);
		QuestionsType questionsType = new QuestionsType();
		List<Question> questionsList = new ArrayList<Question>();

		AppointmentResp amResp = JSON.parseObject(paperInfoString, AppointmentResp.class);

		List<Tests> paperInfos = amResp.getPaperInfo();
		AppointmentInfo appointmentInfo = amResp.getAppointmentInfo();

		pjObj.setStudentId(appointmentInfo.getStudentId());

		if (CollectionUtils.isEmpty(paperInfos) || StringUtils.isBlank(appointmentInfo.getDollorsId())) {
			return null;
		}

		for (Tests tests : paperInfos) {
			Question question = new Question();

			//??????????????????
			switch (tests.getCategory()){
				case 1:
					question.setQuestionSource("??????" + tests.getIdSort() + "(??????" + tests.getId() + ")");
					break;
				case 2:
					question.setQuestionSource("??????" + tests.getWrongIdSort() + "(?????????" + tests.getIdSort() + " ??????" + tests.getId() + ")");
					break;
				case 3:
					question.setQuestionSource("??????" + tests.getWrongIdSort() + "(???????????????" + tests.getIdSort() + " ??????" + tests.getId() + ")");

			}

			question.setQuestionContent(tests.getStem());
			if (!StringUtils.contains(question.getQuestionContent(), "div")) {
				question.setHasDiv(false);
			}

			// selections
			setQuestionSelection(tests, question);

			question.setQuestionAnswer(tests.getMethod());
//				question.setQuestionAnswerInfo(tests.getAnswer());

			questionsList.add(question);
		}

		questionsType.setQuestionsList(questionsList);

		questionsTypeList.add(questionsType);

		pjObj.setQuestionsTypeList(questionsTypeList);

		MainTitle mainTitle = new MainTitle();
		mainTitle.setMainTitleName(appointmentInfo.getDollorsName());
		pjObj.setMainTitle(mainTitle);

		SubTitle subTitle = new SubTitle();
		subTitle.setSubTitleName("<div><br /> <img alt=\"\" src=\"http://tutorial-files-bucket.oss-cn-hangzhou.aliyuncs.com/tikuimages/2/2013/700/xuekubao37/af48154f-937e-11e9-970a-b42e9921e93e_xkb5.png\" style=\"vertical-align:middle\" w=\"176px\" h=\"189px\" /> <br /></div>");
		pjObj.setSubTitle(subTitle);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		PaperInfoBar paperInfoBar = new PaperInfoBar();
		paperInfoBar.setPaperInfoBarName(sdf.format(new Date(appointmentInfo.getAppointmentDate())) + " ??????????????????" + appointmentInfo.getDollorsId());
		pjObj.setPaperInfoBar(paperInfoBar);

		pjObj.setTestsName("errorbook" + appointmentInfo.getDollorsId());

		logger.info("???????????????????????????PaperJsonObj=="+JSON.toJSONString(pjObj));

		return pjObj;
	}

	@Override
	public void deleteTeachPlanDetailsByPaperId(Long paperId) {
		correctionFeignService.deleteByPaperId(paperId);
	}
}
