package com.maker.register.dao.result;

import com.maker.register.model.ResultSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResultSummaryMapper {
    public List<ResultSummary> selectResultSummaryByYear(@Param("year") String year, @Param("lastYear") String lastYear) throws Exception;
    public int insertResultSummary(List<ResultSummary> list)throws Exception;
}
