package com.pdl.gdkoala.feign;

import com.pdl.gdkoala.feign.fallback.ErrorBookPaperFallBackFactory;
import com.tutorial.common.resp.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yangwei
 *
 */
@FeignClient(name="tutorial-ebook", fallbackFactory= ErrorBookPaperFallBackFactory.class )
public interface ErrorBookPaperFeignService {

    //获取卷子详情（打印使用）
    @RequestMapping(value = "/errorBookPaper/feign/api/v2/paperDetailsPrint",method = RequestMethod.POST)
    ServerResponse<Map<String, Object>> paperDetailsPrint(@RequestParam("paperId") Integer paperId);
}
