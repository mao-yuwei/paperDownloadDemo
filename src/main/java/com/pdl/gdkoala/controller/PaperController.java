package com.pdl.gdkoala.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.pdl.gdkoala.resp.ServerResponse;
import com.pdl.gdkoala.resp.paperjson.PaperJsonObj;
import com.pdl.gdkoala.service.PaperService;
import com.pdl.gdkoala.util.JsonUtil;
import com.pdl.gdkoala.util.QRCodeUtil;
import com.pdl.gdkoala.util.SnowflakeIdWorker;
import com.pdl.gdkoala.util.ThirdWrapData;
import com.pdl.gdkoala.wordpapermake.PaperDownloadSeting;
import com.pdl.gdkoala.wordpapermake.QuestionDataFormat;
import com.tutorial.common.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/paper")
public class PaperController {

    private static Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    private PaperService paperService;

    //	/**
//	 * getPaperInfo
//	 * @param subscribeId
//	 */
//	@SuppressWarnings("unused")
    @ResponseBody
    @RequestMapping("/getPaperInfo")
    public ServerResponse<PaperJsonObj> getPaperInfo() throws Exception {
//		PaperJsonObj pjObj = paperService.getPaperInfo(subscribeId);
//		PaperJsonObj pjObj = paperService.getTestsInfo("17", "27", 1);
//		PaperJsonObj pjObj = paperService.getTestsInfo("12", "27", 2);

        Map<String, Object> param = new HashMap<>();
//		paperId=3&paperType=4
        param.put("paperId", 3);
        param.put("paperType", 4);
        PaperJsonObj paperJsonObj = ThirdWrapData.getSyncQuesList(param);

        return ServerResponse.createBySuccess(paperJsonObj);
    }

    @RequestMapping("/download")
    public String download(@RequestParam Map<String, Object> queryMap,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        String docType = "docx";
//		String docType = "doc";
        String paperSizeType = "A4";
        String answerType = "1";
        String fontSize = "1";
        String paperName = "gdkoala-paper";
        String questionSourceShow = "1";


        Map<String, Object> paperMap = getPaperData(paperName);

        paperMap.put("fontSize", fontSize);
        paperMap.put("docType", docType);
        paperMap.put("answerType", answerType);
        paperMap.put("paperSizeType", paperSizeType);
        paperMap.put("teacherUse", 1);
        paperMap.put("questionSourceShow",questionSourceShow)

        ServletOutputStream out = null;

        try {
            String filename = "gdkoala-paper." + docType;
            String userAgent = request.getHeader("User-Agent");
            filename = !userAgent.contains("Chrome") ? new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) : URLEncoder.encode(filename, "utf-8");
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            out = response.getOutputStream();

            PaperDownloadSeting.createWord(paperMap, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

        return "200";
    }

    private void wirteFile(String json) {
        String tempMediaPath = PaperController.class.getClassLoader().getResource("").getPath().replace("classes", "tempfile");
        String fileName = "paperjson.json";//????????????????????????

        //??????????????????
        File file = new File(tempMediaPath, fileName);
        if (!file.exists()) {
            file.setWritable(true, false);
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                logger.info("??????????????????!!");
                ex.printStackTrace();
            }
        }

        try {
            OutputStream out = new FileOutputStream(file);
            InputStream in = new FileInputStream(json);
            byte[] buffer = new byte[10240];
            int bytesToRead = -1;
            while ((bytesToRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPaperData2(String paperJson) {
        Map<String, Object> paperMap = JsonUtil.fromJson(paperJson, Map.class);
        return paperMap;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPaperData(String paperName) {
        String paperData = "";
        try {
            File file = ResourceUtils.getFile("classpath:paperdata/gdkoala-paper");
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            while ((line = br.readLine()) != null) {
                paperData = paperData + line;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> paperMap = JsonUtil.fromJson(paperData, Map.class);
        return paperMap;
    }

    /**
     * ??????????????????
     *
     * @param useType     ?????????1?????????????????????2???????????? 3??????????????? 4??????????????????
     * @param subscribeId ??????ID???useType???1?????????
     * @param paperid     ??????ID/??????ID
     * @param uid         ??????ID???useType???1???????????????ID??????????????????ID
     * @param stuid       ??????ID???useType???1???????????????ID
     * @param papertype   ???????????????1????????????2?????????  10x ????????????????????????100???????????????
     * @param answerType  ???????????????1???????????????2?????????+???????????????????????????
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getPaperdownload")
    public String getPaperdownload(Integer useType, String subscribeId, String paperid, String uid, String stuid, Integer papertype, Integer answerType,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("getPaperdownload???????????????useType???" + useType
                + "???subscribeId???" + subscribeId
                + "???paperid???" + paperid
                + "???uid???" + uid
                + "???stuid???" + stuid
                + "???papertype???" + papertype
                + "???answerType???" + answerType);

        PaperJsonObj pjObj = null;

        String docType = "docx";
        String paperSizeType = "A4";
        String fontSize = "1";

        if (1 == useType) {
            // ????????? ????????????????????????
            pjObj = paperService.getPaperInfo(subscribeId);

        } else if (2 == useType) {
            // ?????? ??????????????????/????????????
            pjObj = paperService.getTestsInfo(paperid, uid, papertype);
        } else if (3 == useType) {
            //???????????? ??????????????????
            pjObj = paperService.getAllInfoForTeachPlan(subscribeId);
        } else if (4 == useType) {
            //????????????????????????
            Map<String, Object> param = new HashMap<>();
            //		paperId=3&paperType=4
            param.put("paperId", paperid);
            param.put("paperType", papertype - 100);
            pjObj = ThirdWrapData.getSyncQuesList(param);

			//just for test
			wirteFile(new Gson().toJson(pjObj));
        }

        if (null == pjObj) {
            // ??????ID?????????????????????
            return null;
        }

        if (1 == useType) {
            // ????????????
            pjObj.setSecurityMark(true);

            pjObj.setPaperType("1");
//			pjObj.setQRCodeUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589118124309&di=0c1fe83658e8db8437bfa9ba1f8d1be9&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3381390367%2C3810581293%26fm%3D214%26gp%3D0.jpg");

            // ???????????????
            try {
                Long qrcode = SnowflakeIdWorker.generateId();
                QRCodeUtil.encode(pjObj.getStudentId() + "###" + subscribeId, null, "/data/gdkoala/tutorial/paperdownload/qrcode/" + qrcode + ".jpg", true);
                pjObj.setQRCodeUrl("/data/gdkoala/tutorial/paperdownload/qrcode/" + qrcode + ".jpg");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (2 == useType) {
            // ??????
            pjObj.setQRCodeUrl("http://tutorial-files-bucket.oss-cn-hangzhou.aliyuncs.com/placeholder.jpg");
        }

        if (3 == useType) {
            // ????????????
            pjObj.setDevideFiveLine(true);

            pjObj.setPaperType("1");

            // ???????????????
            try {
                Long qrcode = SnowflakeIdWorker.generateId();
                QRCodeUtil.encode(pjObj.getStudentId() + "###" + subscribeId, null, "/data/gdkoala/tutorial/paperdownload/qrcode/" + qrcode + ".jpg", true);
                pjObj.setQRCodeUrl("/data/gdkoala/tutorial/paperdownload/qrcode/" + qrcode + ".jpg");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (4 == useType) {
            // ????????????
            pjObj.setDevideFiveLine(true);

            pjObj.setPaperType("1");
        }

        //??????????????????
        pjObj.getQuestionsTypeList().forEach(qtl -> {
            qtl.getQuestionsList().forEach(ql -> {
                try {
                    //??????
                    ql.setQuestionContent(QuestionDataFormat.contentReplace(ql.getQuestionContent()));
                    //??????
                    ql.setQuestionAnswer(QuestionDataFormat.contentReplace(ql.getQuestionAnswer()));
                    //??????
                    ql.setQuestionAnswerInfo(QuestionDataFormat.contentReplace(ql.getQuestionAnswerInfo()));
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("table??????????????????");
                }
            });
        });

        Map<String, Object> paperMap = getPaperData2(JSON.toJSONString(pjObj));
        logger.info("??????json???" + JSON.toJSONString(pjObj));

        paperMap.put("fontSize", fontSize);
        paperMap.put("docType", docType);
        if (1 == answerType) {
            paperMap.put("answerType", "3");
        } else if (2 == answerType) {
            paperMap.put("answerType", "1");
        }
        paperMap.put("paperSizeType", paperSizeType);

        ServletOutputStream out = null;

        try {
            String filename = pjObj.getTestsName() + "." + docType;
            String userAgent = request.getHeader("User-Agent");
            filename = !userAgent.contains("Chrome") ? new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) : URLEncoder.encode(filename, "utf-8");
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);

            out = response.getOutputStream();
            PaperDownloadSeting.createWord(paperMap, out);
            out.flush();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            logger.error("?????????????????????", e);
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("?????????????????????", e);
            return null;
        } finally {
            out.close();
        }

        return "200";

    }
}
