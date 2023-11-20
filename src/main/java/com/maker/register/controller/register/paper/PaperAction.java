package com.maker.register.controller.register.paper;

import com.maker.register.model.Paper;
import com.maker.register.model.PaperParam;
import com.maker.register.service.paper.PaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/paper/*")
public class PaperAction {
    private static final Logger LOGGER= LoggerFactory.getLogger(PaperAction.class);
    @Autowired
    private PaperService paperService;
    private  SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("save")
    @ResponseBody
    public Integer savePapers(@RequestBody PaperParam paperParam){
            String account=paperParam.getAccount();
            String date=paperParam.getDate();
            String email=paperParam.getEmail();
            String informant=paperParam.getInformant();
            String[] doi=paperParam.getDoi();
            String[] title=paperParam.getTitle();
            String[] sn=paperParam.getSn();
            String[] jtitleVol=paperParam.getJtitleVol();
            String[] paperClass=paperParam.getPaperClass();
            String[] pUrl= paperParam.getpUrl();
            if(account==null || "".equals(account)){
                LOGGER.error("奖励账户为空！");
                return 0;
            }
            if(date==null || "".equals(date)){
                date=sdf.format(new Date());
            }
           // LOGGER.info("【填报人】{}、【账户】{}、【邮箱】{}、【doi】{}、【论文标题】{}、【刊号】{}、【刊名及卷期】{}、【论文等级】{}、【论文链接】{}",
           //         informant,account,email, Arrays.toString(doi), Arrays.toString(title),Arrays.toString(sn)
            //        ,Arrays.toString(jtitleVol),Arrays.toString(paperClass),Arrays.toString(pUrl));
        try{
           LOGGER.info("【doi】{}、【title】{}、【sn】{}、【jtitleVol】{}、【paperClass】{}、【pUrl】{}",doi.length,title.length,sn.length,jtitleVol.length,paperClass.length,pUrl.length);
            if(doi.length==title.length && doi.length==sn.length && doi.length== jtitleVol.length && doi.length==paperClass.length && doi.length== pUrl.length){
                List<Paper> papers=new ArrayList<>();
                //LOGGER.info("【paperAction】所有数组参数相等");
                for (int i=0;i< doi.length;i++){
                    Paper paper=new Paper();

                    paper.setDoi(doi[i]);
                    paper.setTitle(title[i]);
                    paper.setPaperClass(Integer.parseInt(paperClass[i]));
                    paper.setJtitleVol(jtitleVol[i]);
                    paper.setSn(sn[i]);
                    paper.setAccount(account);
                    paper.setInformant(informant);

                    paper.setBonus(0.0);
                    paper.setDate(date);
                    paper.setEmail(email);
                    paper.setThanks(1);
                    paper.setIsGrant(0);
                    paper.setTimestamp(new Date().getTime());
                    paper.setpUrl(pUrl[i]);
                    papers.add(paper);

                }

                //LOGGER.info("【PaperAction】{}",papers);
                return paperService.insertPaper(papers);

            } else{
                LOGGER.error("【PaperAction】传入论文属性数量不相等，参数异常！");
            }

        }catch (Exception e){
            LOGGER.error("新增论文信息出错：{}",e.getMessage());
        }
        return 0;
    }

    /**
     * 接收对象列表，此处使用@RequestBody注解
     * 使用该注解，前端必须传标准的json数据过来
     * 建议使用JSON.stringify格式化data对象，以及请求加上application/json
     * */
    @RequestMapping("replace")
    @ResponseBody
    public Object replacePapers(@RequestBody List<Paper> papers){
        Map<String,Object> result=new HashMap<>();
       if(papers==null || papers.isEmpty()){
           LOGGER.error("传入论文参数为空");
           result.put("code",0);
            result.put("message","论文参数为空");
           return result;
       }
       try{
           Integer num=paperService.insertPaperAll(papers);
           result.put("code",1);
           result.put("itemnum",num);

       }catch (Exception e){
           LOGGER.error("替换论文异常：{}",e.getMessage());
            result.put("code",0);
            result.put("message","更新论文状态出现问题!");
       }
        return result;
    }

    @RequestMapping("getPapersByDate")
    @ResponseBody
    public Object getPaperByDate(String startDate,String endDate,Integer isGrant){
        Map<String,Object> result=new HashMap<>();
        if(startDate==null || "".equals(startDate) || endDate==null || "".equals(endDate)){
            result.put("code",0);
            result.put("message","开始日期或者结束日期为空！");

        }
        Map<String,Object> reqParam=new HashMap<>();
        reqParam.put("startDate",startDate);
        reqParam.put("endDate",endDate);
        reqParam.put("isGrant",isGrant);
        try {
            List<Paper> papers=paperService.queryPaperByDate(reqParam);
            result.put("pdata",papers);
            result.put("code",1);

        }catch (Exception e){
            LOGGER.error("查询论文信息出现异常：{}",e.getMessage());
            result.put("code",0);
            result.put("message","系统繁忙,请联系管理员...");
        }
        return result;
    }
}
