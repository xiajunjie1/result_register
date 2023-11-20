<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>核对项目成果</title>
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
               var projects=[];
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
                    url:'/project/getProjectsByDate',
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
                        projects=data.pdata;
                        console.log(projects);

                        $("#mytab").on('change',"select",function(){
                                var index=$(this).attr("tid");
                                 projects[index][$(this).attr("name")]=$(this).val();
                                 var bonus=projects[index].charge*0.1*projects[index].isGrant;
                                 $("#bonus-"+index).html(bonus);
                                 projects[index].bonus=bonus;



                        });

                        constructProjects(projects,"mytab",1,10);

                        block.removeClass("afterClick");
                        block.addClass("beforeClick");


                    }
               }); //ajax end
               }); //click end

               $("#saveBtn").click(function(){
               var block=$("#block");
               block.removeClass("beforeClick");
               block.addClass("afterClick");
                    var notes= $("textarea[name='note']");

                    notes.each(function(){
                        var index=$(this).attr("tid");
                        console.log(index);
                        projects[index].note=$(this).val();
                    }); //loop end
                    var reqData={
                        'projects':projects
                    }
                    $.ajax({
                        url:'/project/replace',
                        data:JSON.stringify(projects),
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
    <div class="col-lg-12 divBc" style="margin:10px;text-align:center;"><h2>项目成果核对</h2></div>

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
                	<caption><strong>项目成果</strong></caption>
                	<thead>
                		<tr>
                			<th>项目编号</th>
                			<th>项目名</th>
                			<th>项目金额</th>
                            <th>项目类型</th>
                            <th>项目主持人</th>
                            <th>奖励账号</th>
                            <th>填报人</th>
                            <th>邮箱</th>
                            <th>填报日期</th>
                            <th>充值金额</th>
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
