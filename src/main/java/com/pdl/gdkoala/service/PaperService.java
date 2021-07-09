package com.pdl.gdkoala.service;

import com.pdl.gdkoala.resp.paperjson.PaperJsonObj;

/**
 * @author yangwei
 *
 */
public interface PaperService {

	/**
	 * getPaperInfo
	 * @param subscribeId
	 * @return
	 */
	PaperJsonObj getPaperInfo(String subscribeId);

	/**
	 * 获取学生试卷/作业信息
	 * @param paperid
	 * @param uid
	 * @param papertype
	 * @return
	 */
	PaperJsonObj getTestsInfo(String paperid, String uid, Integer papertype);

	/**
	 * 获取教案信息
	 * @param subscribeId
	 * @return
	 */
	PaperJsonObj getAllInfoForTeachPlan(String subscribeId);

	/**
	 * 删除教案详情信息
	 * @param paperId
	 */
    void deleteTeachPlanDetailsByPaperId(Long paperId);

}
