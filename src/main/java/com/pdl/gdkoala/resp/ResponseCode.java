package com.pdl.gdkoala.resp;


public enum ResponseCode {
    SERVER_EXCEPTION(00,"service fallback"),

    SUCCESS(0, "操作成功"),
    FAILED(999999,"操作失败"),

    TOKENERROR(0001,"TOKEN不存在"),
    LACKTOKENERROR(0002,"缺少必要参数TOKEN"),
    LACKERROR(0003,"缺少必要参数"),
    PASSWORDSAMEERROR(0004,"新旧密码不能相同"),
    USERINFOMISSINGERROR(0005,"用户信息未完善"),
    USERINFOUPDATEERROR(0006,"用户信息更新失败"),
    USERINFOGETERROR(0007,"用户信息获取失败"),


    UPLOADERROR(1000,"文件上传失败"),
    FAILEDVIDEO(1001,"只支持上传MP4格式的视频文件"),
    QUESTIONFORMATERROR(1002,"文件格式错误"),
    PATHERROR(1003,"文件URL异常"),
    DOWNLOADERROR(1004,"文件下载失败"),
    FILENOTEXISTERROR(1005,"文件不存在"),
    FILEEXISTERROR(1006,"文件已存在"),
    DELETEFILENOTERROR(1007,"无删除文件"),
    DELETELISTFILENOTERROR(1008,"批量删除异常"),
    READFILENOTERROR(1009,"文件读取错误"),


    JYEOOFAILED(2001,"题库数据获取失败"),
    JYEOOTOKENISNULL(2002,"登录凭证为空"),
    JYEOOTOKENERROR(2003,"登录凭证错误"),
    JYEOOREGISTERERROR(2004,"用户注册失败"),
    JYEOOLOGINERROR(2005,"用户登录失败"),
    JYEOOREGISTERSUCCESS(2006,"用户注册成功"),
    JYEOOREGISTERANDUPDATESUCCESS(2007,"用户注册成功并且数据更新成功"),
    JYEOOREGISTERANDUPDATEERROR(2008,"用户注册成功但数据更新未成功"),

    SMSSENDERROR(3000,"短信发送失败"),
    SMSCODEERROR(3001,"验证码错误"),
    SMSCODELAPSE(3002,"验证码失效，请重新获取"),


    USERREGISTERERROR(4000,"用户注册失败"),
    USERREGISTERSUCCESS(4001,"用户注册成功"),
    USERACOUNTEXIXT(4002,"手机号已被注册"),
    USERNAME0RPWDISNULL(4003,"用户名或密码为空"),
    USERNAME0RPWDERROR(4004,"用户名或密码错误"),
    USERISLOGIN(4005,"该用户已登录"),
    USERDISABLE(4006,"该用户已停用"),
    USERNO(4007,"该用户不存在"),
    USERINFOERROR(4008,"获取用户信息错误"),
    OLDPWD(4009,"原密码有误"),


    VIDEOPARSING(5001,"暂无解析视频，去约课"),
    NOTUSEFERRORBOOK(5002,"尚未使用错题本"),
    NOTUSEFERRORBOOKTEC(5003,"尚未使用备课本"),

    PHOTOERROR(6001,"所拍错题图片异常"),
    WRONGBOOKHASBEENADDED(6002,"该题已加入错题本"),
    NORESOLUTION(6003,"该题无法加入错题本"),
    QUESTIONNOTEXISTE(6004,"该题不存在"),
    NOANSWERORPARSE(6005,"该题需要解析或者答题加入错题本"),



    PAPERNOTEXISTE(7001,"试卷不存在"),
    PAPERNOTINFOEXISTE(7002,"未找到改试卷相关信息"),
    PAPERNOTTESTINFOEXISTE(7003,"未答题"),

    KNOWLEDGENITSELECT(8001,"相关知识点未查询到");

    private final Integer code;
    private final String desc;


    ResponseCode(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
