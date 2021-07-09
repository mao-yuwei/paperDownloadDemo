package com.pdl.gdkoala.wordpapermake;

import com.pdl.gdkoala.util.JsonUtil;
import com.pdl.gdkoala.wordpapermake.htmltowordhandle.NodesToWord;
import com.pdl.gdkoala.wordpapermake.htmltowordhandle.SpecialContentToWordUtil;
import com.tutorial.common.aliyun.Aliyun;
import com.tutorial.common.aliyun.AliyunUtil;
import com.tutorial.common.constant.AliYunConstants;
import com.tutorial.common.constant.CssConstants;
import com.tutorial.common.enums.PlatformEnum;
import com.tutorial.common.tools.IdWorker;
import com.tutorial.common.utils.StringUtils;
import gui.ava.html.Html2Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author: maoyuwei
 * @Desc:试卷试题数据处理
 **/
public class QuestionDataFormat {
    public static Logger logger = Logger.getLogger("QuestionDataFormat");
    
    private static org.slf4j.Logger log = LoggerFactory.getLogger(QuestionDataFormat.class);
    
    private static String IMAGE_PATH= QuestionDataFormat.class.getClassLoader().getResource("").getPath().replace("classes", "tempfile");

    //modified by dzyssssss 2020.6.29 替换成固定的目录
    //private static String IMAGE_PATH= QuestionDataFormat.class.getClassLoader().getResource("").getPath().replace("classes", "tempfile");
    //private static String IMAGE_PATH= "/data/gdkoala/tutorial/paperdownload/tempfile/";
    //modified by dzyssssss 2020.6.29 end

    private static Map<Integer,String> questionTypeCategoryMap;
    static {
        questionTypeCategoryMap=new HashMap<Integer, String>();
        questionTypeCategoryMap.put(111,"第I卷 （选择题）");
        questionTypeCategoryMap.put(112,"第Ⅱ卷 （非选择题）");
    }
    /**
     *@Author: maoyuwei
     *@Date: 2019/9/7 15:29
     *@Desc: 获取题型分类
     */
    public static String getQuestionTypeCategory(Object questionTypeCategoryObj,Object qtIsSelectObj){
        if(questionTypeCategoryObj!=null){
            Integer questionTypeCategory=Integer.parseInt(questionTypeCategoryObj.toString());
            if(!questionTypeCategory.equals(0)){
                String questionTypeCategoryStr=questionTypeCategoryMap.get(questionTypeCategory);
                if(questionTypeCategoryStr!=null) {return questionTypeCategoryStr;}
            }
        }
        if(qtIsSelectObj==null) {return null;}
        return " ";
    }
    /**
     *@throws Exception
     * @Author: maoyuwei
     *@Date: 2019/9/7 15:29
     *@Desc:  试卷数据处理为word样式
     */
    public static Map<String, Object> htmlPaperToWordStyle(Map<String, Object> paperMap) throws Exception {
        Integer questionIndex=1;//试题序号
        String questionTypeCategorybefore="";
        String tempMediaPath=IMAGE_PATH+ UUID.randomUUID().toString()+"/";//图片临时存放路径

        //add by dzyssssss 2020.6.28 看下图片的临时位置
        logger.info("wordTempImageFile is： " + tempMediaPath);

        Map<String, List<String>> relations=new HashMap<String, List<String>>();//图片信息
        List<String> relationshipStr=new ArrayList<String>();//图片链接信息
        List<String> pkgpartStr=new ArrayList<String>();//doc里图片base64码
        relations.put("relationshipStr", relationshipStr);
        relations.put("pkgpartStr", pkgpartStr);
        
        Integer paperType = paperMap.get("paperType") == null ? 1 : Integer.parseInt(paperMap.get("paperType").toString());
        String QRCodeUrl = paperMap.get("qRCodeUrl") == null ? null : paperMap.get("qRCodeUrl").toString();
        //教师类型写入二维码数据
        if (paperType.equals(1) && QRCodeUrl != null) {
            String index = UUID.randomUUID().toString();
            String imageType = QRCodeUrl.substring(QRCodeUrl.lastIndexOf(".") + 1);
            Map<String, String> map = SpecialContentToWordUtil.addWordMark(QRCodeUrl, imageType, index, tempMediaPath, new HashMap<>());
            relations.get("relationshipStr").add(map.get("relationshipStr"));
            relations.get("pkgpartStr").add(map.get("pkgpartStr"));
            //二维码在word中的ID
            paperMap.put("QRCodeId", "rId" + index);
        }
        
        try {
        	Map subTitle = JsonUtil.jsontoMapMapObject(JsonUtil.toJson(paperMap.get("subTitle")));
    		String subTitleName = subTitle.get("subTitleName").toString();
    		subTitleName= NodesToWord.htmlStrToWordStr(subTitleName,relations,tempMediaPath);
    		subTitle.put("subTitleName", subTitleName);
        	
            List<Map<String, Object>> questionsTypeList= JsonUtil.jsontoListMapObject(JsonUtil.toJson(paperMap.get("questionsTypeList")));
            for(Map<String, Object> qtMap:questionsTypeList) {
                List<Map<String, Object>> questionsList= JsonUtil.jsontoListMapObject(JsonUtil.toJson(qtMap.get("questionsList")));
                String questionTypeCategory=getQuestionTypeCategory(qtMap.get("questionTypeCategory"),qtMap.get("qtIsSelect"));//题型分类
                if(questionTypeCategory!=null&&!questionTypeCategory.equals(questionTypeCategorybefore)){
                    qtMap.put("questionTypeCategoryStr",questionTypeCategory);
                    questionTypeCategorybefore=questionTypeCategory;
                }
                for(Map<String, Object> qMap:questionsList) {
                    qMap.put("questionIndex",questionIndex++);
                    //题文word格式化
                    if(qMap.get("questionContent")!=null&&!qMap.get("questionContent").toString().equals("")){
                       String questionContent=qMap.get("questionContent").toString();
                        questionContent= NodesToWord.htmlStrToWordStr(questionContent,relations,tempMediaPath);
                        qMap.put("questionContent", questionContent);
                    }
                    //选项word格式化
                    if(qMap.get("questionSelection")!=null&&!qMap.get("questionSelection").toString().equals("")){
                        String questionSelection=qMap.get("questionSelection").toString();
                        questionSelection= NodesToWord.selectionStrToWordStr(questionSelection,relations,tempMediaPath);
                        qMap.put("questionSelection", questionSelection);
                    }
                    //答案word格式化
                    if(qMap.get("questionAnswer")!=null&&!qMap.get("questionAnswer").toString().equals("")){
                        String questionAnswer=qMap.get("questionAnswer").toString();
                        questionAnswer=NodesToWord.htmlStrToWordStr(questionAnswer,relations,tempMediaPath);
                        qMap.put("questionAnswer", questionAnswer);
                    }
                    //解析word格式化
                    if(qMap.get("questionAnswerInfo")!=null&&!qMap.get("questionAnswerInfo").toString().equals("")){
                        String questionAnswerInfo=qMap.get("questionAnswerInfo").toString();
                        questionAnswerInfo=NodesToWord.htmlStrToWordStr(questionAnswerInfo,relations,tempMediaPath);
                        qMap.put("questionAnswerInfo", questionAnswerInfo);
                    }
                }
                qtMap.put("questionsList", questionsList);
            }
        	
            paperMap.put("relations", relations);
            paperMap.put("subTitle", subTitle);
            paperMap.put("questionsTypeList", questionsTypeList);
            paperMap.put("tempMediaPath",tempMediaPath);
            
            return paperMap;
        } catch (Exception e) {
//        	logger.info("试卷下载异常");
        	log.error("试卷下载异常：", e);
        	throw new Exception("试卷下载异常");
//            return paperMap;
        }
    }

    /**
     * html的table标签转images标签
     * @return
     */
    public static String tableToImages(String htmlStr){
        log.info("需要转换的html字符串htmlStr=="+htmlStr);
        //头部添加<div>
        htmlStr = "<div>" + htmlStr;
        //尾部添加</div>
        htmlStr = htmlStr + "</div>";
        //解析html
        Document document = Jsoup.parse(htmlStr);
        Elements elements = document.select("table");
        for (Element e : elements){
            System.out.println(e.html());
        }
        return null;
    }


    public static String replaceQuestionContent (String oldContent) {
        logger.info("题干内容oldContent=="+oldContent);
        //解析html
        Document document = Jsoup.parse(oldContent);
        //查找table标签
        Elements elements = document.select("table");
        //文件路径一部分
        String uuid = UUID.randomUUID().toString();
        //遍历
        for (Element e : elements){
            String tempMediaPath = File.separator + "data" + File.separator + "gdkoala"+File.separator+"tutorial"+File.separator+"paperdownload"+File.separator+"tempfile"+File.separator+uuid+File.separator;
            String fileName="image"+UUID.randomUUID().toString()+".png";//图片临时存放路径
            //新建文件路径
            File file = new File(tempMediaPath,fileName);
            if (!file.exists()){
                file.setWritable(true,false);
                file.getParentFile().mkdir();
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    logger.info("生成文件失败!!");
                    ex.printStackTrace();
                }
            }

            e.attr("style","background:#fff");
            logger.info("table标签内容=="+e.toString());

            //html转image
            Html2Image html2Image = Html2Image.fromHtml(e.toString());
		    html2Image.getImageRenderer().saveImage(tempMediaPath+fileName);
		    //删除table标签下的子标签
            rmChilds(e);
            // 把table标签名改为image
            e.tagName("image");
            //为table标签添加src，style属性
            e.attr("src", tempMediaPath);
            e.attr("style","background:#fff");
            //去掉原有无用的class标签-可选
            e.removeAttr("class");
        }
        logger.info("最终生成的文档string=="+document.html());
        return document.html();
    }

    public static void rmChilds(Element e){
        Elements children = e.children();
        for (Element item : children){
            item.remove();
        }
    }

    //table转图片
    public static String contentReplace(String content) throws IOException {
        if (StringUtils.isEmpty(content)){
            return content;
        }
        //题干转换document(顺便把题干字母小写)
        Document document = Jsoup.parse(content);
        //获取body字符串
        String body = document.select("body").get(0).html();
        Elements tables = document.select("body>table");
        //最外层是div
//        if (tables.size() == 0){
//            tables = document.select("div>table");
//        }
        for (Element table : tables) {
            String tableStr = table.toString();
            StringBuilder tableSb = new StringBuilder(CssConstants.STYLECSS).append(tableStr);
            log.info(tableSb.toString());
            String filePath = File.separator + "data" + File.separator + "gdkoala"+File.separator+"tutorial"+File.separator+"paperdownload"+File.separator+"tempfile"+File.separator+ IdWorker.get32UUID() + ".png";
            File file = new File(filePath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            log.info(String.valueOf(file.length()));
            //table转图片
            Html2Image html2Image = Html2Image.fromHtml(tableSb.toString());
            html2Image.getImageRenderer().saveImage(file);
            log.info(String.valueOf(file.length()));
            Aliyun aliyun = new Aliyun();
            aliyun.setAliyunAccessKeyId(AliYunConstants.accessKeyId);
            aliyun.setAliyunAccessKeySecret(AliYunConstants.accessKeySecret);
            aliyun.setAliyunOssBucket(AliYunConstants.tempBucketName);
            aliyun.setAliyunOssEndpoint(AliYunConstants.tempEndPoint);
            aliyun.setAliyunOssUrl(AliYunConstants.tempBucketURL);
            String fileUrl = AliyunUtil.uploadPic(PlatformEnum.PAPERDOWNLOAD, file, aliyun);
            //替换
            String imgStr = "<img alt=\"金考拉\" src=\""+fileUrl+"\">";
            boolean contains = StringUtils.contains(body, tableStr);
            log.info("是否存在==============" + String.valueOf(contains));
            body = StringUtils.replace(body, tableStr, imgStr);
        }
        log.info("table转图片===================================" + body);
        return body;
    };

//    public static void main(String[] args) throws IOException {
//        String content = "<div>【题文】、如下表， 已知离散型随机变量ξ的分 布列，则Dξ为&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><table border=\"1\" cellpadding=\"0\" cellspacing=\"0\"><tr style=\"height:14.9pt\"><td width=\"49\" valign=\"top\" style=\"width:36.6pt;border:solid windowtext 1.0pt;   padding:0cm 5.4pt 0cm 5.4pt;height:14.9pt\">ξ<br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border:solid windowtext 1.0pt;   border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.9pt\">－2<br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border:solid windowtext 1.0pt;   border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.9pt\">0<br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border:solid windowtext 1.0pt;   border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.9pt\">2<br /></td></tr><tr style=\"height:15.45pt\"><td width=\"49\" valign=\"top\" style=\"width:36.6pt;border:solid windowtext 1.0pt;   border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:15.45pt\">p<br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border-top:none;border-left:   none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;   padding:0cm 5.4pt 0cm 5.4pt;height:15.45pt\"><img src=\"/tikuimages/2/2011/700/xuekubao3/286cad4f-92a9-11e9-b685-b42e9921e93e_xkb13.png\" style=\"vertical-align:middle;\" /><br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border-top:none;border-left:   none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;   padding:0cm 5.4pt 0cm 5.4pt;height:15.45pt\"><img src=\"/tikuimages/2/2011/700/xuekubao26/286cfb70-92a9-11e9-b419-b42e9921e93e_xkb56.png\" style=\"vertical-align:middle;\" /><br /></td><td width=\"49\" valign=\"top\" style=\"width:36.65pt;border-top:none;border-left:   none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;   padding:0cm 5.4pt 0cm 5.4pt;height:15.45pt\">m<br /></td></tr></table>&nbsp;</div>";
//        contentReplace(content);
//    }

}
