package com.pdl.gdkoala.util;

import com.pdl.gdkoala.resp.Constant;
import com.pdl.gdkoala.resp.RespSyncPaper;
import com.pdl.gdkoala.resp.paperjson.*;
import com.pdl.gdkoala.resp.syncpractise.SyncPaperQuesList;
import com.pdl.gdkoala.resp.syncpractise.SyncQues;
import com.tutorial.common.enums.QuesTypeEnum;
import com.tutorial.common.utils.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取其他数据
 */
public class ThirdWrapData {

    /*
     * 向服务器发送请求
     */
    private static String sendPost(String url, Map<String, String> head, Map<String, Object> params) {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            connection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : head.entrySet()){
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            //connection.setRequestProperty("accept", "application/json; charset=UTF-8");
            //connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //connection.setRequestProperty("user-agent", "Mozilla/5.0");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()){
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("&");
            }

            if (stringBuilder.length() > 1){
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }

            byte[] bs = stringBuilder.toString().getBytes("UTF-8");

            out = new DataOutputStream(connection.getOutputStream());
            out.write(bs);
            out.flush();

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF8"));
            }

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            if (code != HttpURLConnection.HTTP_OK) {
                throw new Exception(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /*
     * 发送POST请求
     */
    private static <T> T sendPost( String url, Map<String, String> head, Map<String, Object> params, Class<T> classOfT) throws Exception {
        String json = sendPost(url, head, params);

        return Utility.convertTo(json, classOfT);
    }

    /**
     * 获取同步练习中的题目列表
     *
     * @param param
     * @return
     */
    public static PaperJsonObj getSyncQuesList(Map<String, Object> param) throws Exception {
        Map<String, String > head = new HashMap<>();
        head.put("accept", "application/json; charset=UTF-8");
        head.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        head.put("user-agent", "Mozilla/5.0");

        String result = sendPost(Constant.QUERY_SYNC_QUES_LIST, head, param);

        if (ObjectUtils.isEmpty(result)){
            throw new Exception("返回试卷信息为空");
        }

        RespSyncPaper respSyncPaper = Utility.convertTo(result, RespSyncPaper.class);
        if (respSyncPaper.getStatus() == null || respSyncPaper.getStatus().getValue() != 200){
            throw new Exception(respSyncPaper.getStatus().getName());
        }

        SyncPaperQuesList syncPaperQuesList = respSyncPaper.getData();

        //返回定义的数据结构
        return convert2PrintQuesList(syncPaperQuesList);
    }

    /**
     * 将专题练习中的题目转换成需要打印的数据格式
     *
     * @param syncQues
     * @return
     */
    private static Question convert2Question(SyncQues syncQues){
        Question question = new Question();
        question.setQuestionContent(syncQues.getQuestionContent());
        question.setQuestionAnswer(syncQues.getQuestionAnswer());
        question.setQuestionAnswerInfo(syncQues.getQuestionAnswerInfo());
        question.setQuestionDifficulty(syncQues.getQuestionDifficulty().getValue());

        StringBuilder qSelection = new StringBuilder("<span class=\"quesborder\"><table style=\"width:100%\" class=\"ques quesborder\"><tbody>");

        String optionA = syncQues.getOptionA();
        if (StringUtils.isNotBlank(optionA)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("A." + optionA);
            qSelection.append("</label></td></tr>");
        }

        String optionB = syncQues.getOptionB();
        if (StringUtils.isNotBlank(optionB)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("B." + optionB);
            qSelection.append("</label></td></tr>");
        }

        String optionC = syncQues.getOptionC();
        if (StringUtils.isNotBlank(optionC)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("C." + optionC);
            qSelection.append("</label></td></tr>");
        }

        String optionD = syncQues.getOptionD();
        if (StringUtils.isNotBlank(optionD)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("D." + optionD);
            qSelection.append("</label></td></tr>");
        }

        String optionE = syncQues.getOptionE();
        if (StringUtils.isNotBlank(optionE)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("E." + optionE);
            qSelection.append("</label></td></tr>");
        }

        String optionF = syncQues.getOptionF();
        if (StringUtils.isNotBlank(optionF)) {
            qSelection.append("<tr><td style=\"width:98%\" class=\"selectoption\"><label class=\"\">");
            qSelection.append("F." + optionF);
            qSelection.append("</label></td></tr>");
        }

        qSelection.append("</tbody></table></span>");
        question.setQuestionSelection(qSelection.toString());

        //知识点
        if (!ObjectUtils.isEmpty(syncQues.getKnowledgeList())){

            List<QuestionLabel> questionLabelList = new ArrayList<>();
            for (String temp : syncQues.getKnowledgeList()){

                QuestionLabel questionLabel = new QuestionLabel();
                questionLabel.setLabelName(temp);
                questionLabel.setImportance("1"); //
                questionLabelList.add(questionLabel);
            }

            question.setQuestionLabelList(questionLabelList);
        }

        if (!ObjectUtils.isEmpty(syncQues.getQuesId())){
            question.setQuestionSource(syncQues.getQuesId());
        }

        return question;
    }

    /**
     * 将同步练习返回的题目结构转化成打印结构
     * 需要根据题型进行分组
     *
     * @param syncPaperQuesList
     * @return
     */
    private static PaperJsonObj convert2PrintQuesList(SyncPaperQuesList syncPaperQuesList){

        PaperJsonObj paperJsonObj = new PaperJsonObj();

        //试卷名称
        MainTitle mainTitle = new MainTitle();
        mainTitle.setMainTitleName(syncPaperQuesList.getPaperName());
        mainTitle.setMainTitleExist(true);
        paperJsonObj.setMainTitle(mainTitle);

        //子标题
        SubTitle subTitle = new SubTitle();
        subTitle.setSubTitleName("教师组卷");
        subTitle.setSubTitleExist(false);
        paperJsonObj.setSubTitle(subTitle);

        //paper bar
        PaperInfoBar paperInfoBar = new PaperInfoBar();
        paperInfoBar.setPaperInfoBarName("");
        paperInfoBar.setPaperInfoBarExist(false);
        paperJsonObj.setPaperInfoBar(paperInfoBar);

        //stu bar
        StudentInfoBar studentInfoBar = new StudentInfoBar();
        studentInfoBar.setStudentInfoBarName("");
        studentInfoBar.setStudentInfoBarExist(false);
        paperJsonObj.setStudentInfoBar(studentInfoBar);

        //注意事项
        NeedingAttention needingAttention = new NeedingAttention();
        needingAttention.setNeedingAttentionExist(false);
        needingAttention.setAttention(new String[0]);
        paperJsonObj.setNeedingAttention(needingAttention);

        SubPaperAndNotes subPaperAndNotes = new SubPaperAndNotes();
        subPaperAndNotes.setSubPaperAndNotesExist(false);
        subPaperAndNotes.setNotesList(new ArrayList<>());
        paperJsonObj.setSubPaperAndNotes(subPaperAndNotes);

        //需要根据题型进行分组
        Map<Integer, List<SyncQues>> collectGroup = syncPaperQuesList.getList()
                .stream()
                .collect(Collectors.groupingBy(item -> item.getQuestionType().getValue()));
        //按照顺序组装最后格式
        List<QuestionsType> questionsTypeList = new ArrayList<>();
        QuestionsType questionsType = new QuestionsType();

        //单选题处理 只能硬性处理，没有排序大小
        List<SyncQues> syncQuesList = collectGroup.get(QuesTypeEnum.SINGLE_CHOICE.getCode());
        if (syncQuesList != null){
            questionsType.setQuestionDesc(QuesTypeEnum.SINGLE_CHOICE.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.SINGLE_CHOICE.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.SINGLE_CHOICE.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //多选题
        syncQuesList = collectGroup.get(QuesTypeEnum.MULTI_CHOICE.getCode());
        if (syncQuesList != null){
            questionsType = new QuestionsType();
            questionsType.setQuestionDesc(QuesTypeEnum.MULTI_CHOICE.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.MULTI_CHOICE.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.MULTI_CHOICE.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //填空
        syncQuesList = collectGroup.get(QuesTypeEnum.FILL.getCode());
        if (syncQuesList != null){
            questionsType = new QuestionsType();
            questionsType.setQuestionDesc(QuesTypeEnum.FILL.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.FILL.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.FILL.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //判断
        syncQuesList = collectGroup.get(QuesTypeEnum.JUDGE.getCode());
        if (syncQuesList != null){
            questionsType = new QuestionsType();
            questionsType.setQuestionDesc(QuesTypeEnum.JUDGE.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.JUDGE.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.JUDGE.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //简答
        syncQuesList = collectGroup.get(QuesTypeEnum.SHORT_ANSWER.getCode());
        if (syncQuesList != null){
            questionsType = new QuestionsType();
            questionsType.setQuestionDesc(QuesTypeEnum.SHORT_ANSWER.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.SHORT_ANSWER.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.SHORT_ANSWER.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //组合题目
        syncQuesList = collectGroup.get(QuesTypeEnum.COMB_ANSWER.getCode());
        if (syncQuesList != null){
            questionsType = new QuestionsType();
            questionsType.setQuestionDesc(QuesTypeEnum.COMB_ANSWER.getMsg());
            questionsType.setQuestionTypeCategory(QuesTypeEnum.COMB_ANSWER.getCode());
            questionsType.setQuestionFristType(QuesTypeEnum.COMB_ANSWER.getMsg());
            questionsType.setQuestionTypeNote(""); //题型注释

            List<Question> questionList = new ArrayList<>();
            for (SyncQues temp : syncQuesList){
                Question question = convert2Question(temp);
                questionList.add(question);
            }
            questionsType.setQuestionsList(questionList);
            questionsTypeList.add(questionsType);
        }

        //题目列表
        paperJsonObj.setQuestionsTypeList(questionsTypeList);

        return  paperJsonObj;
    }
}
