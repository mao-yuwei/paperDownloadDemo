package com.pdl.gdkoala.resp.syncpractise;

import lombok.Data;

import java.util.List;

@Data
public class SyncQues {
    /**
     * questionContent : 丑小鸭作者是谁？ <br><img class='sdd-image' src='http://139.224.74.55:82/admin/resources/file/2021-05-31/b16bd51f-6997-4cc8-8dd2-919dc052e940.jpeg'  width='62' height='62' /><img class='sdd-image' src='http://139.224.74.55:82/admin/resources/file/2021-05-31/c9e7ba35-4a5f-44cc-9682-3a5b01e5f251.PNG'  width='37' height='40' /><br><table class='sdd-table'><tbody><tr><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>2</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>3</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>4</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>5</td></tr><tr><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>9</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>8</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>7</td><td  class='sdd-table-td' valign='top' style='word-break: break-all; border-width: 1px; border-style: solid;'>6</td></tr></tbody></table>
     * questionAnswer : A
     * questionSelection : ["安徒生 ","生安徒","徒安徒","丑大鸭 ","丑大鹅"]
     * questionAnswerInfo : 很好
     * questionSource : 10
     * questionType : {"name":"单选题","value":23}
     * questionDifficulty : {"name":"偏易","value":2}
     * knowledgeList : ["集合的含义","集合的表示法"]
     * knowledges : 集合的含义、集合的表示法
     * optionA : 安徒生
     * optionB : 生安徒
     * optionC : 徒安徒
     * optionD : 丑大鸭
     * optionE : 丑大鹅
     * optionF : null
     */

    private String quesId;
    private String questionContent;
    private String questionAnswer;
    private String questionAnswerInfo;
    private String questionSource;
    private QuestionTypeBean questionType;
    private QuestionDifficultyBean questionDifficulty;
    private String knowledges;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private List<String> questionSelection;
    private List<String> knowledgeList;
}
