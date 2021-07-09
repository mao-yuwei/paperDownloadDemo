package com.pdl.gdkoala.resp;

import com.pdl.gdkoala.resp.syncpractise.StatusBean;
import com.pdl.gdkoala.resp.syncpractise.SyncPaperQuesList;
import lombok.Data;

@Data
public class RespSyncPaper {
    private StatusBean status;

    private SyncPaperQuesList data;

}
