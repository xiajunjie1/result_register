<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>项目成果登记</title>
    <link href="/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <link href="/jquery/bootstrap-selector/css/bootstrap-select.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript"  src="/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/jquery/echars/echarts.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js?id=1"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/jquery/bootstrap-selector/js/i18n/defaults-zh_CN.js"></script>
    <script type="text/javascript" src="/jquery/tabhandler.js?id=1"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#block").hide();
            $("#date").datetimepicker(dateParam);
            createTrProject("mytab","","append");
            $("#mytab").on("click","button[name='ins']",function(){
                createTrProject("mytab",$(this).attr("tid"),"insert");
            });
            $("#mytab").on("click","button[name='del']",function(){
                    delTr("mytab",$(this).attr("tid"));
            });

            $("#addbtn").on("click",function(){
                createTrProject("mytab","","append");
            });
            $("#submit").click(function(){
            var account=$("#account").val();
             var informant=$("#informant").val();
             var email=$("#email").val();
             var date=$("#date").val();
             var piddom=$("input[name='pid']");
             var pnamedom=$("input[name='pname']");
             var moneydom=$("input[name='money']");
             var typedom=$("input[name='type']");
             var phostdom=$("input[name='phost']");
             var pid=new Array();
             var pname=new Array();
             var money=new Array();
             var type=new Array();
             var phost=new Array();
             if(account==""){
                 alert("奖励账号不能为空");
                 return;
             }
             try{
                 piddom.each(function(){
                     if($(this).val()==""){
                         alert("项目编号不能为空！");
                         throw "项目编号有空值！";
                     }
                     pid.push($(this).val());

                 });

                 pnamedom.each(function(){
                     if($(this).val()==""){
                         alert("项目名不能为空");
                         throw "项目名有空值";
                     }
                     pname.push($(this).val());
                 });
                 moneydom.each(function(){
                     money.push($(this).val());

                 });

                 typedom.each(function(){
                     type.push($(this).val());
                 });

                 phostdom.each(function(){
                     phost.push($(this).val());
                 });

             }catch(e){
                 console.log(e);
                 return;
             }
             var data={
                                           'account':account,
                                           'informant':informant,
                                           'email':email,
                                           'date':date,
                                           'pid':pid,
                                           'pname':pname,
                                           'money':money,
                                           'type':type,
                                           'phost':phost
                                       };

             var reqParam={
                 url:'/project/save',
                 data:JSON.stringify(data),
                 contentType:'application/json',
                 type:'post',
                 dataType:'json',
                 success:function(data){
                        alert("新增项目"+data+"项");
                        $("#block").hide();
                 },
                 beforeSend:function(){
                    $("#block").show();
                 }
             };
             console.log(reqParam);
             $.ajax(reqParam);

            });

        });
    </script>

 </head>
 <body>
    <div id="block"  style="position:absolute;width:100%;height:2000px;background:rgba(242,242,242,0.5);z-index:10">
        <img style="position:absolute;left:45%;top:7%;width:25px;height:25px"  src="/image/loading.gif" />
    </div>
    <div class="col-lg-12" style="text-align:center;margin:10px">
            <h2>项目成果登记</h2>
            <div style="position:relative;height:5px;background:#f2f2f2"></div>
        </div>


        <div class=" col-lg-12">
            <div class="col-lg-3">
                <input type="text" placeholder="填报人" id="informant" name="informant" />
            </div>
            <div class="col-lg-3">
            <label >奖励账号：</label>
                <select class="selectpicker" data-live-search="true" name="account" id="account">
                    <c:forEach items="${ulist}" var="u">
                        <option value="${u.userId}">${u.userId}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-lg-3">
                <input type="text" name="email" id="email" placeholder="e-mail" />
            </div>


            <div class="col-lg-3">
                <label >填表日期：</label>
                <input type="text" id="date" name="date" readonly="readonly" />
            </div>
        </div>

    <div class="col-lg-12" style="margin-top:5px">
        <table  class="table table-bordered" style="border-bottom:none;border-left:none;border-right:none">
        	<caption><strong>项目登记</strong></caption>
        	<thead>
        		<tr>
        			<th>项目编号<span style="color:#ff0000">*</span></th>
        			<th>项目名称<span style="color:#ff0000">*</span></th>
        			<th>项目金额</th>
                    <th>项目类型</th>
                    <th>项目主持人</th>
                    <th>操作</th>
        		</tr>
        	</thead>
        	<tbody id="mytab">


        	</tbody>
            <tfoot>
                <tr>
                    <td colspan="7" ><button class="btn btn-primary" id="addbtn">添加</button></td>
                </tr>
                <tr style="border:none">
                    <td colspan="7" style="text-align:center;border:none;"><button class="btn btn-primary" id="submit">提交</button></td>
                </tr>
            </tfoot>
        </table>
        </div>
 </body>
 </html>