package com.maker.register.controller.register.result;

import com.maker.register.model.ResultSummary;
import com.maker.register.service.result.ResultSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/result")
public class ResultSummaryAction {
    private static final Logger LOGGER= LoggerFactory.getLogger(ResultSummaryAction.class);
    @Autowired
    private ResultSummaryService resultSummaryService;
    @RequestMapping("getSummary")
    @ResponseBody
    public Object getResultSummaryByYear(String year){
        Map<String,Object> result=new HashMap<>();
        if(year==null || "".equals(year)){
            result.put("code",0);
            result.put("message","传入年份为空！");
            LOGGER.error("传入年份为空！");
            return result;
        }
        try{
            String lastYear=Integer.parseInt(year)-1+"";
            List<ResultSummary> resultSummaries=resultSummaryService.getResultSummaryByYear(year,lastYear);
            for(int i=0;i<resultSummaries.size();i++){
                resultSummaries.get(i).setYear(year);
                resultSummaries.get(i).setId(resultSummaries.get(i).getUserid()+"-"+year);
            }
            result.put("code",1);
            result.put("data",resultSummaries);
        }catch (Exception e){
            LOGGER.error("查询成果汇总出现异常：{}",e.getMessage());
            result.put("code",0);
            result.put("message","获取成果汇总异常！");
        }
        return result;
    }
    @RequestMapping("replace")
    @ResponseBody
    public Object replaceResultSummary(@RequestBody List<ResultSummary> resultSummaries){
        Map<String,Object> result=new HashMap<>();
        if (resultSummaries==null || resultSummaries.isEmpty()){
            result.put("code",0);
            result.put("message","ResultSummary参数为空！");
        }
        try {
           int itemnum = resultSummaryService.replaceResultSummary(resultSummaries);
            result.put("code",1);
            result.put("itemnum",itemnum);

        }catch(Exception e){
            LOGGER.error("更新汇总结果出现异常：{}",e.getMessage());
            result.put("code",0);
            result.put("message","更新汇总信息出现异常");
        }
        return result;
    }
}
