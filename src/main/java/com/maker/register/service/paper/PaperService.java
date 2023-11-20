package com.maker.register.service.paper;

import com.maker.register.model.Paper;

import java.util.List;
import java.util.Map;

public interface PaperService {

    public int insertPaper(List<Paper> paperList)throws Exception;
    public int insertPaperAll(List<Paper> papers) throws Exception;

    public List<Paper> queryPaperByDate(Map<String,Object> map) throws Exception;
}
