<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>论文成果登记</title>
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
    <script type="text/javascript" src="/jquery/tabhandler.js?id=2"></script>
 </head>
     <script>
            $(document).ready(function(){
                    $("#block").hide();
                    createTr("mytab","","append");
                    //var insBtn=$("button[name='ins']");
                    $("#mytab").on("click","button[name='ins']",function(){
                        createTr("mytab",$(this).attr("tid"),"insert");
                    });
                    $("#mytab").on("click","button[name='del']",function(){
                        delTr("mytab",$(this).attr("tid"));
                    });
                    $("#addbtn").on("click",function(){
                        createTr("mytab","","append");
                    });

                    $("#date").datetimepicker(dateParam);
                          $("#submit").click(function(){
                                              var informant=$.trim($("input[name='informant']").val());
                                              var account=$("select[name='account']").val();
                                              var email=$.trim($("input[name='email']").val());
                                              var date=$.trim($("input[name='date']").val());
                                              var titledom=$("input[name='title']");
                                              var doidom=$("input[name='doi']");
                                              var sndom=$("input[name='sn']");
                                              var jtitleVoldom=$("textarea[name='jtitle_vol']");
                                              var paperClassdom=$("select[name='type']");
                                              var pUrldom=$("input[name='purl']");
                                              var title=new Array();
                                              var doi=new Array();
                                              var sn=new Array();
                                              var jtitleVol=new Array();
                                              var paperClass=new Array();
                                              var pUrl=new Array();
                                              if(account==""){
                                                alert("奖励账号不能为空！");
                                                return;

                                              }
                                             try{
                                              titledom.each(function(){
                                                  if($.trim($(this).val())==""){
                                                    alert("论文标题不能为空！");
                                                    throw "论文有空值";
                                                  }
                                                  title.push($(this).val());

                                              });
                                              doidom.each(function(){
                                                if($.trim($(this).val())==""){
                                                    alert("doi不能为空");
                                                    throw "doi有空值";
                                                }
                                                doi.push($(this).val());
                                              });
                                              sndom.each(function(){
                                                    sn.push($(this).val());

                                              });
                                              jtitleVoldom.each(function(){
                                                jtitleVol.push($(this).val());
                                              });
                                              paperClassdom.each(function(){
                                              console.log($(this).val());
                                              if($(this).val()==0){
                                                alert("请选择论文等级!");
                                                throw "论文等级有空值";
                                              }
                                                 paperClass.push($(this).val());

                                              });
                                              pUrldom.each(function(){
                                                if($.trim($(this).val())==""){
                                                    alert("论文链接不能为空");
                                                    throw "论文链接有空值";
                                                }
                                                pUrl.push($.trim($(this).val()));
                                              });

                                              }catch(e){
                                                console.log(e);
                                                return;
                                              }
                               console.log("【标题数组长度】"+title.length);
                             //构造请求参数
                             var reqParam={
                              'informant':informant,
                              'account':account,
                                'email':email,
                                  'date':date,
                                  'doi':doi,
                                  'title':title,
                                  'sn':sn,
                                  'jtitleVol':jtitleVol,
                                  'pUrl':pUrl,
                                  'paperClass':paperClass
                                                          }
                                  // console.log(JSON.stringify(reqParam))

                            var ajaxParam={
                                        url:'/paper/save',
                                        data:JSON.stringify(reqParam),
                                        type:'post',
                                        dataType:'json',
                                        contentType:'application/json',
                                        success:function(data){

                                            alert("新增论文"+data+"篇");
                                            $("#block").hide();
                                        },
                                        beforeSend:function(){
                                            $("#block").show();
                                        }
                            };
                            //JSON.string
                            $.ajax(ajaxParam);

                                });





            });




        </script>
    </head>
    <body>
    <div id="block"  style="position:absolute;width:100%;height:2000px;background:rgba(242,242,242,0.5);z-index:10">
    <img style="position:absolute;left:45%;top:7%;width:25px;height:25px"  src="/image/loading.gif" />
    </div>
    <div class="col-lg-12" style="text-align:center;margin:10px">
        <h2>论文成果登记</h2>
        <div style="position:relative;height:5px;background:#f2f2f2"></div>
    </div>


    <div class=" col-lg-12">
        <div class="col-lg-3">
            <input type="text" placeholder="填报人" name="informant" />
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
    	<caption><strong>论文登记</strong></caption>
    	<thead>
    		<tr>
    			<th>论文名称<span style="color:#ff0000">*</span></th>
    			<th>刊号</th>
    			<th>刊名及卷期</th>
                <th>doi<span style="color:#ff0000">*</span></th>
                <th>论文等级</th>
                <th>论文链接<span style="color:#ff0000">*</span></th>
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