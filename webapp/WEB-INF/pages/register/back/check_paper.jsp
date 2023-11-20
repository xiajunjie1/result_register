<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>核对论文成果</title>
    <link href="/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-selector/css/bootstrap-select.min.css" type="text/css" rel="stylesheet" />
     <link href="/jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
        .divBc{
            background:#fff
        }

        .beforeClick{
            z-index:-10;

        }

        .afterClick{
            z-index:10;
            background:rgba(242,242,242,0.5);
        }
    </style>
    <script type="text/javascript"  src="/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/jquery/echars/echarts.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/i18n/defaults-zh_CN.js"></script>
    <script type="text/javascript" src="/jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="/jquery/bs_pagination-master/localization/en.js"></script>
    <script type="text/javascript" src="/jquery/tabhandler.js?id=1"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
               $("#startDate").datetimepicker(dateParam);
               $("#endDate").datetimepicker(dateParam);
               var papers=[];//用来接收查询到的论文数据，在回写论文数据的时候也是将该对象提交给后端

               //根据日期获取未发放奖励的论文成果
               $("#queryBtn").click(function(){
               var block=$("#block");
               block.removeClass("beforeClick");
               block.addClass("afterClick");
               var endDate = $.trim($("#endDate").val());
               var startDate = $.trim($("#startDate").val());
               if(startDate==""){
                alert("请选择开始时间！");
                block.removeClass("afterClick");
                block.addClass("beforeClick");
                return;
               }
               if(endDate==""){
                alert("请选择截止时间！");
                block.removeClass("afterClick");
                block.addClass("beforeClick");
                return;
               }
               if(startDate>endDate){
                    alert("开始时间不得晚于截止时间");
                    block.removeClass("afterClick");
                    block.addClass("beforeClick");
                    return;
               }

               $.ajax({
                    url:'/paper/getPapersByDate',
                    data:{
                        'startDate':startDate,
                        'endDate':endDate,
                        'isGrant':0
                    },
                    type:'post',
                    dataType:'json',
                    success:function(data){
                        if(data.code==0){
                            alert(data.message);
                            block.removeClass("afterClick");
                            block.addClass("beforeClick");
                            return;
                        }
                        papers=data.pdata;
                        $("#mytab").on('change',"select",function(){
                                var index=$(this).attr("tid");
                                 papers[index][$(this).attr("name")]=$(this).val();
                                 //根据论文等级计算奖励金额，并且根据当前的行索引更新查询到的论文数据
                               switch(papers[index].paperClass){
                                   case 1:{
                                       var bonus=6000*papers[index].isGrant*papers[index].thanks;
                                          papers[index].bonus=bonus;
                                          $("#bonus-"+index).html(bonus);
                                 } break;
                                    case 2:{
                                        var bonus=2000*papers[index].isGrant*papers[index].thanks;
                                        papers[index].bonus=bonus;
                                        $("#bonus-"+index).html(bonus);
                                    } break;
                                    case 3:{

                                        var bonus=500*papers[index].isGrant*papers[index].thanks;
                                        papers[index].bonus=bonus;
                                        //在表格中填入奖励金额
                                        $("#bonus-"+index).html(bonus);
                                    } break;
                                    default:{
                                            alert("论文类型异常！");

                                        }
                            }
                        });
                        //构造表格
                        constructPapers(papers,"mytab",1,10);
                        block.removeClass("afterClick");
                        block.addClass("beforeClick");


                    }
               }); //ajax end
               }); //click end

                //将当前获取到的论文成果回写到数据库中进行更新
               $("#saveBtn").click(function(){
               var block=$("#block");
               block.removeClass("beforeClick");
               block.addClass("afterClick");
                    var notes= $("textarea[name='note']");
                    notes.each(function(){
                        var index=$(this).attr("tid");
                        papers[index].note=$(this).val();
                    }); //loop end
                    var reqData={
                        'papers':papers
                    }
                    $.ajax({
                        url:'/paper/replace',
                        data:JSON.stringify(papers),
                        contentType:'application/json',
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            if(data.code==0){
                                alert(data.message);

                            }
                            else{
                            console.log(data.itemnum);
                            alert("论文数据更新完毕");
                        }
                            block.removeClass("afterClick");
                            block.addClass("beforeClick");
                        }
                    });
               });//click end

        });
    </script>
 </head>

 <body class="divBc">

    <div id="block" class="beforeClick" style="position:absolute;width:100%;height:2000px;"><img style="position:absolute;left:45%;top:7%;width:25px;height:25px"  src="/image/loading.gif" /></div>
    <div class="col-lg-12 divBc" style="margin:10px;text-align:center;"><h2>论文成果核对</h2></div>

    <div class="col-lg-6 col-lg-offset-3 divBc">
        <div class="col-lg-5">
            <label >开始日期：</label>
            <input type="text" id="startDate" name="startDate" readonly="readonly" />
        </div>
         <div class="col-lg-5">
             <label >截止日期：</label>
             <input type="text" id="endDate" name="endDate" readonly="readonly" />
         </div>
         <div class="col-lg-1"><button class="btn btn-primary" id="queryBtn">查询</button></div>
         <div class="col-lg-1"><button class="btn btn-success" id="saveBtn">保存</button></div>

        </div>
        <div class="col-lg-12 divBc" style="background:#f2f2f2;margin:5px; height:5px"></div>
        <div class="col-lg-12 divBc">
              <table  class="table table-bordered" style="border-bottom:none;border-left:none;border-right:none">
                	<caption><strong>论文成果</strong></caption>
                	<thead>
                		<tr>
                			<th>论文名称</th>
                			<th>刊号</th>
                			<th>刊名及卷期</th>
                            <th>doi</th>
                            <th>奖励账号</th>
                            <th>填报人</th>
                            <th>登记日期</th>
                            <th>论文等级</th>
                            <th>论文链接</th>
                            <th>是否致谢</th>
                            <th>是否发放奖励</th>
                            <th>奖励金额</th>
                            <th>备注</th>

                		</tr>
                	</thead>
                	<tbody id="mytab">

                	</tbody>
                	</table>
        </div>

        <div id="bs_pag"></div>
   </div>
 </body>
 </html>
