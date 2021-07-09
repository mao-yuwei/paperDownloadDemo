package com.pdl.gdkoala.feign.fallback;

import com.pdl.gdkoala.feign.CorrectionFeignService;
import com.tutorial.common.resp.ServerResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangwei
 *
 */
@Component
public class CorrectionFallBackFactory implements FallbackFactory<CorrectionFeignService> {
	
	private static Logger logger = LoggerFactory.getLogger(CorrectionFallBackFactory.class);

	@Override
	public CorrectionFeignService create(Throwable arg0) {
		// TODO Auto-generated method stub
		
		logger.warn("CorrectionFeignService服务降级...");
		
		return new CorrectionFeignService() {
			
			/* (non-Javadoc)
			 * @see com.pdl.gdkoala.feign.CorrectionFeignService#getAllInfoForOfflineCourseQR(java.lang.String)
			 */
			@Override
			public ServerResponse<Map<String, Object>> getAllInfoForOfflineCourseQR(String subscribeId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServerResponse<Map<String, Object>> getHomeworkDetailQR(String id, String stuId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServerResponse<Map<String, Object>> getAllInfoForTeachPlan(String subscribeId) {
				return null;
			}

			@Override
			public ServerResponse deleteByPaperId(Long paperid) {
				return null;
			}

		};
	}
	
}
