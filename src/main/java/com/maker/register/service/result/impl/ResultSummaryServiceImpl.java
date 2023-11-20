package com.maker.register.service.result.impl;

import com.maker.register.dao.result.ResultSummaryMapper;
import com.maker.register.model.ResultSummary;
import com.maker.register.service.result.ResultSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultSummaryServiceImpl implements ResultSummaryService {
    @Autowired
    private ResultSummaryMapper resultSummaryMapper;
    @Override
    public List<ResultSummary> getResultSummaryByYear(String year, String lastYear) throws Exception {
        if(year==null || "".equals(year)){
            throw new RuntimeException("传入年份为空！");
        }
        if(lastYear==null || "".equals(lastYear)){
            throw new RuntimeException("传入上一年份为空!");
        }
        return resultSummaryMapper.selectResultSummaryByYear(year,lastYear);
    }

    @Override
    public int replaceResultSummary(List<ResultSummary> resultSummaries) throws Exception {
        if(resultSummaries==null || resultSummaries.isEmpty()){
            throw new RuntimeException("传入成果汇总数据为空!");
        }
        return resultSummaryMapper.insertResultSummary(resultSummaries);
    }
}
