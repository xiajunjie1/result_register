package com.maker.register.dao.paper;

import com.maker.register.model.Paper;

import java.util.List;
import java.util.Map;

public interface PaperMapper {

    public int insertPaper(List<Paper> papers)throws Exception;
    public int insertPaperAll(List<Paper> papers)throws Exception;
    public List<Paper> selectAllPapersByDate(Map<String,Object> map)throws Exception;
}
