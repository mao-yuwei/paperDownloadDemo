package com.pdl.gdkoala.feign;

import com.pdl.gdkoala.feign.fallback.CorrectionFallBackFactory;
import com.tutorial.common.resp.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yangwei
 *
 */
@FeignClient(name="tutorial-correction", fallbackFactory=CorrectionFallBackFactory.class )
public interface CorrectionFeignService {

    /**
     * 根据预约单ID获取试卷信息
     * @param subscribeId
     * @return
     */
    @PostMapping("/api/v1/user/getAllInfoForOfflineCourseQR")
    ServerResponse<Map<String, Object>> getAllInfoForOfflineCourseQR(@RequestParam("subscribeId") String subscribeId);
    
    /**
     * 根据作业ID和学生ID获取作业信息
     * @param id 作业ID
     * @param stuId 学生ID
     * @return
     */
    @PostMapping("/api/v1/user/getHomeworkDetailQR")
    ServerResponse<Map<String, Object>> getHomeworkDetailQR(@RequestParam("id") String id, @RequestParam("stuId") String stuId);

    /**
     * 根据预约单ID获取教案信息
     * @param subscribeId
     * @return
     */
    @PostMapping("/teachPlan/feign/api/v1/getAllInfoForTeachPlan")
    ServerResponse<Map<String, Object>> getAllInfoForTeachPlan(@RequestParam("subscribeId") String subscribeId);

    /**
     * 删除
     * @param paperId
     * @return
     */
    @PostMapping("/teachPlanDetails/feign/api/v1/deleteByPaperId")
    ServerResponse deleteByPaperId(@RequestParam("paperId") Long paperId);

}
