<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>科研成果汇总统计</title>
    <link href="/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-selector/css/bootstrap-select.min.css" type="text/css" rel="stylesheet" />
     <link href="/jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript"  src="/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/jquery/echars/echarts.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/i18n/defaults-zh_CN.js"></script>
    <script type="text/javascript" src="/jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="/jquery/bs_pagination-master/localization/en.js"></script>
    <script type="text/javascript" src="/jquery/tabhandler.js?id=2"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
                $("#block").hide();
               $("#date").datetimepicker(dateParam2);
                var results=[];

               //根据日期获取未发放奖励的论文成果
               $("#queryBtn").click(function(){
               var block=$("#block");
               block.removeClass("beforeClick");
               block.addClass("afterClick");

               var date = $.trim($("#date").val());
               if(date==""){
                alert("请选择汇总年份");

                return;
               }



               $.ajax({
                    url:'/result/getSummary',
                    data:{
                        'year':date,

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
                        results=data.data;
                        console.log(results);
                        createResultSummary(results,"mytab",1,10);
                        $("#block").hide();



                    },
                    beforeSend:function(){
                        $("#block").show();
                    }
               }); //ajax end
               }); //click end

                //将当前获取到的论文成果回写到数据库中进行更新
               $("#saveBtn").click(function(){

                    var notes= $("textarea[name='note']");
                    notes.each(function(){
                        var index=$(this).attr("tid");
                        results[index].note=$(this).val();
                    }); //loop end

                    $.ajax({
                        url:'/result/replace',
                        data:JSON.stringify(results),
                        contentType:'application/json',
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            if(data.code==0){
                                alert(data.message);

                            }
                            else{
                            console.log(data.itemnum);
                            alert("更新完毕");
                        }

                        $("#block").hide();

                        },
                        beforeSend:function(){
                            $("#block").show();
                        }
                    });
               });//click end

        });
    </script>
 </head>

 <body>

     <div id="block"  style="position:absolute;width:100%;height:2000px;background:rgba(242,242,242,0.5);z-index:10">
     <img style="position:absolute;left:45%;top:7%;width:25px;height:25px"  src="/image/loading.gif" />
     </div>
    <div class="col-lg-12 " style="margin:10px;text-align:center;"><h2>科研成果汇总</h2></div>

    <div class="col-lg-6 col-lg-offset-3 divBc">
        <div class="col-lg-5">
            <label >年份：</label>
            <input type="text" id="date" name="date" readonly="readonly" />
        </div>

         <div class="col-lg-1"><button class="btn btn-primary" id="queryBtn">查询</button></div>
         <div class="col-lg-1"><button class="btn btn-success" id="saveBtn">保存</button></div>

        </div>
        <div class="col-lg-12 " style="background:#f2f2f2;margin:5px; height:5px"></div>
        <div class="col-lg-12">
              <table  class="table table-bordered" style="border-bottom:none;border-left:none;border-right:none">
                	<caption><strong>科研成果汇总</strong></caption>
                	<thead>
                		<tr>
                			<th>姓名</th>
                			<th>超算账户</th>
                			<th>充值金额（本年）</th>
                            <th>成果统计</th>
                            <th>论文</th>
                            <th>项目</th>
                            <th>已发奖励额</th>
                            <th>奖励剩余</th>
                            <th>奖励剩余（去年）</th>
                            <th>充值剩余</th>
                            <th>充值剩余（去年）</th>
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
