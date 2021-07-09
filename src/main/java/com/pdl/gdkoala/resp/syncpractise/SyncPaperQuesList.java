package com.pdl.gdkoala.resp.syncpractise;

import lombok.Data;

import java.util.List;

@Data
public class SyncPaperQuesList {

    private String paperName;
    private List<SyncQues> list;
}
