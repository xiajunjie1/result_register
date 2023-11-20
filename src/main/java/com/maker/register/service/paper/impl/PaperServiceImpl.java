package com.maker.register.service.paper.impl;

import com.maker.register.dao.paper.PaperMapper;
import com.maker.register.model.Paper;
import com.maker.register.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
   @Autowired
    private PaperMapper paperMapper;
    @Override
    public int insertPaper(List<Paper> paperList) throws Exception {
        if(paperList==null || paperList.isEmpty()){
            throw new RuntimeException("需要插入的论文数据为空");
        }
       
        return paperMapper.insertPaper(paperList);
    }

    @Override
    public int insertPaperAll(List<Paper> papers) throws Exception {
        if(papers==null || papers.isEmpty()){
            throw new RuntimeException("需要插入的论文数据为空");
        }
        return paperMapper.insertPaperAll(papers);
    }

    @Override
    public List<Paper> queryPaperByDate(Map<String, Object> map) throws Exception {
        if(map==null || map.isEmpty()){
            throw new RuntimeException("传入的日期参数为空");
        }
        List<Paper> papers=paperMapper.selectAllPapersByDate(map);
        System.err.println("【PaperService】"+papers);
        return papers;
    }
}
