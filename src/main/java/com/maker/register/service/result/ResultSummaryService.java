package com.maker.register.service.result;

import com.maker.register.model.ResultSummary;

import java.util.List;

public interface ResultSummaryService {
    public List<ResultSummary> getResultSummaryByYear(String year,String lastYear) throws Exception;
    public int replaceResultSummary(List<ResultSummary> resultSummaries)throws Exception;
}
