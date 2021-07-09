package com.pdl.gdkoala.feign.fallback;

import com.pdl.gdkoala.feign.CorrectionFeignService;
import com.pdl.gdkoala.feign.ErrorBookPaperFeignService;
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
public class ErrorBookPaperFallBackFactory implements FallbackFactory<ErrorBookPaperFeignService> {
	
	private static Logger logger = LoggerFactory.getLogger(ErrorBookPaperFallBackFactory.class);


	@Override
	public ErrorBookPaperFeignService create(Throwable cause) {
		return new ErrorBookPaperFeignService() {
			@Override
			public ServerResponse<Map<String, Object>> paperDetailsPrint(Integer paperId) {
				return null;
			}
		};
	}
}
