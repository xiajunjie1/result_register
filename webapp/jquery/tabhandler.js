 //创建论文登记列表
 var createTr=function(tab,trid,ops){
                var tid='id-'+Math.random().toString(36).substr(2,9);
                var table=$("#"+tab);
                var newRow=$('<tr>');
                    newRow.attr('tid',tid);
                    var td1=$('<td>');
                    var td2=$('<td>');
                        var td3=$('<td>');
                            var td4=$('<td>');
                            var td5=$('<td>');
                            var td6=$('<td>');
                            var td7=$('<td>')
                    td1.append("<input type='text' name='title'  />");
                    td2.append("<input type='text' name='sn' />")
                    td3.append("<textarea name='jtitle_vol'></textarea>");
                    td4.append("<input type='text' name='doi' placeholder='doi:' />");

                    td5.append("<select name='type'><option value=0 >请选择</option><option value=1>SCI-1</option><option value=2>SCI-2</option><option value=3>其它</option></select>");
                    td6.append("<input type='text' name='purl'  />")
                    td7.append("<button class='btn btn-success' name='ins' tid="+tid+"> 插入</button>&nbsp <button class='btn btn-danger' name='del' tid="+tid+">删除</button>");
                    newRow.append(td1);
                    newRow.append(td2);
                    newRow.append(td3);
                    newRow.append(td4);
                    newRow.append(td5);
                    newRow.append(td6);
                    newRow.append(td7);
                    //点击插入
                    if(ops=="insert"){
                        //获取当前插入按钮所在行
                        var curow=$("tr[tid="+trid+"]");
                        //在该行前插入一行
                        curow.before(newRow);

                    }else{
                        //在tbody中最后的部位插入一行
                        table.append(newRow);
                    }
            };

//构造项目表格
var createTrProject=function(tab,trid,ops){
               var tid='id-'+Math.random().toString(36).substr(2,9);
                var table=$("#"+tab);
                var newRow=$('<tr>');
                newRow.attr('tid',tid);
                var td1=$('<td>');
                var td2=$('<td>');
                var td3=$('<td>');
                var td4=$('<td>');
                var td5=$('<td>');
                var td6=$('<td>');

                    td1.append("<input type='text' name='pid'  />");
                    td2.append("<input type='text' name='pname' />")
                    td3.append("<input type='text' name='money' />");
                    td4.append("<input type='text' name='type'  />");
                    td5.append("<input type='text' name='phost'  />")
                    td6.append("<button class='btn btn-success' name='ins' tid="+tid+"> 插入</button>&nbsp <button class='btn btn-danger' name='del' tid="+tid+">删除</button>");
                    newRow.append(td1);
                    newRow.append(td2);
                    newRow.append(td3);
                    newRow.append(td4);
                    newRow.append(td5);
                    newRow.append(td6);

                    if(ops=="insert"){
                        var curow=$("tr[tid="+trid+"]");
                        curow.before(newRow);

                    }else{
                        table.append(newRow);
                    }
};

            var delTr=function(tab,trid){
                $("tr[tid="+trid+"]").remove();

            };

           var dateParam={
                            'language':"zh-CN",//显示语言
                            'format':"yyyy-mm-dd",//显示格式
                             'minView':"month",//可选择的最小视图
                             'initialDate':new Date(), //初始化显示日期
                             'autoclose':true, //设置选择日期时间后是否自动关闭
                              'todayBtn':true, //设置“今天”按钮
                              'clearBtn':true, //清空日期的功能
                                		    //将clear改为清空，修改该插件中的bootstrap-datetimepicker.js文件或者修改bootstrap-datetimepicker.zh-CN.js

                              };
           var dateParam2={
                            'language':"zh-CN",//显示语言
                            'format':"yyyy",//显示格式
                             //'minView':"year",//可选择的最小视图
                             'minView':4,
                             'startView':4,
                             'initialDate':new Date(), //初始化显示日期
                             'autoclose':true, //设置选择日期时间后是否自动关闭
                              'todayBtn':true, //设置“今天”按钮
                              'clearBtn':true, //清空日期的功能
                                		    //将clear改为清空，修改该插件中的bootstrap-datetimepicker.js文件或者修改bootstrap-datetimepicker.zh-CN.js

                              };

           var getAllUsers=function(id,ops){
              $.ajax({
                    url:'/user/getalluser',
                    data:{},
                    type:'post',
                    dataType:'json',
                    success:function(data){
                    console.log(data);
                    if(ops=="select"){
                    console.log("test");
                   // $("#account").append("<option value="">请选择</option>");
                        $("#account").append("<option value=''>请选择</option>");

                       $.each(data,function(index,obj){
                        var option=$("<option></option>");
                        //option.val(obj.userid);
                        //option.text(obj.username);
                        $("#"+id).append("<option>"+index+"</option>");
                                        });

                    }

                    }

              });
           }

//通过给定的论文数据分页构造表格
//注意，由于里面涉及到内容的更新，所以此处是假分页，即已经查出了所有满足条件的论文数据
var constructPapers=function(papers,tab,page,pageSize){
   var table=$("#"+tab);
   table.html("");
   console.log(papers);
   var start=(page-1)*pageSize
   var end=(pageSize+start>papers.length)?papers.length:start+pageSize;
   console.log(start);
   console.log(end);
   var totalPages=0;
   //计算总页数
   if(papers.length%pageSize==0){
        totalPages=papers.length/pageSize;
   }else{
        totalPages=parseInt(papers.length/pageSize)+1;
   }
    //根据传入的参数，遍历需要构造的数据，并生成表格
   for(var index=start;index<end;index++){
        var obj=papers[index];
        var newRow=$("<tr>");
                newRow.attr("tid",index);
                var td1=$("<td>");
                var td2=$("<td>");
                var td3=$("<td>");
                var td4=$("<td>");
                var td5=$("<td>");
                var td6=$("<td>");
                var td7=$("<td>");
                var td8=$("<td>");
                var td9=$("<td>");
                var td10=$("<td>");
                var td11=$("<td>");
                var td12=$("<td>");
                var td13=$("<td>");
                td1.append(obj.title);
                td2.append(obj.sn);
                td3.append(obj.jtitleVol);
                td4.append(obj.doi);
                td5.append(obj.account);
                td6.append(obj.informant);
                td7.append(obj.date)
                var paperClass="";
                var bonus=0.00;
                //根据传入的论文等级，填入相应的中文
                switch (obj.paperClass){
                    case 1:{
                        paperClass="SCI-1";

                    }
                        break;
                    case 2:paperClass="SCI-2";break;
                    case 3:paperClass="其它";break;
                    default:paperClass="";
                }
                td8.append(paperClass);
                td9.append("<a href='"+obj.pUrl+"'>"+obj.pUrl+"</a>");
                var thanks=$("<select>");
                thanks.attr("tid",index);
                thanks.attr("id","thanks-"+index);
                thanks.attr("name","thanks");
                thanks.append("<option value=1>是</option><option value=0>否</option>");
                thanks.val(obj.thanks);
                td10.append(thanks);
                var isGrant=$("<select>");
                isGrant.attr("tid",index);
                isGrant.attr("id","isGrant-"+index);
                isGrant.attr("name","isGrant");
                isGrant.append("<option value=1>是</option><option value=0>否</option>");
                isGrant.val(obj.isGrant);
                td11.append(isGrant);
                td12.attr("id","bonus-"+index);
                td12.append(obj.bonus);
                var note=$("<textarea>");
                note.attr("id","note-"+index);
                note.attr("tid",index);
                note.attr("name","note");
                note.val(obj.note);

                td13.append(note);
                newRow.append(td1);
                newRow.append(td2);
                newRow.append(td3);
                newRow.append(td4);
                newRow.append(td5);
                newRow.append(td6);
                newRow.append(td7);
                newRow.append(td8);
                newRow.append(td9);
                newRow.append(td10);
                newRow.append(td11);
                newRow.append(td12);
                newRow.append(td13);
                table.append(newRow);

   }
   /*
   $.each(papers,function(index,obj){

        var newRow=$("<tr>");
        newRow.attr("tid",index);
        var td1=$("<td>");
        var td2=$("<td>");
        var td3=$("<td>");
        var td4=$("<td>");
        var td5=$("<td>");
        var td6=$("<td>");
        var td7=$("<td>");
        var td8=$("<td>");
        var td9=$("<td>");
        var td10=$("<td>");
        var td11=$("<td>");
        var td12=$("<td>");
        var td13=$("<td>");
        td1.append(obj.title);
        td2.append(obj.sn);
        td3.append(obj.jtitleVol);
        td4.append(obj.doi);
        td5.append(obj.account);
        td6.append(obj.informant);
        td7.append(obj.date)
        var paperClass="";
        var bonus=0.00;
        switch (obj.paperClass){
            case 1:{
                paperClass="SCI-1";

            }
                break;
            case 2:paperClass="SCI-2";break;
            case 3:paperClass="其它";break;
            default:paperClass="";
        }
        td8.append(paperClass);
        td9.append("<a href='"+obj.pUrl+"'>"+obj.pUrl+"</a>");
        var thanks=$("<select>");
        thanks.attr("tid",index);
        thanks.attr("id","thanks-"+index);
        thanks.attr("name","thanks");
        thanks.append("<option value=1>是</option><option value=0>否</option>");
        thanks.val(obj.thanks);
        td10.append(thanks);
        var isGrant=$("<select>");
        isGrant.attr("tid",index);
        isGrant.attr("id","isGrant-"+index);
        isGrant.attr("name","isGrant");
        isGrant.append("<option value=1>是</option><option value=0>否</option>");
        isGrant.val(obj.isGrant);
        td11.append(isGrant);
        td12.attr("id","bonus-"+index);
        td12.append(obj.bonus);
        var note=$("<textarea>");
        note.attr("id","note-"+index);
        note.attr("tid",index);
        note.attr("name","note");
        note.val(obj.note);

        td13.append(note);
        newRow.append(td1);
        newRow.append(td2);
        newRow.append(td3);
        newRow.append(td4);
        newRow.append(td5);
        newRow.append(td6);
        newRow.append(td7);
        newRow.append(td8);
        newRow.append(td9);
        newRow.append(td10);
        newRow.append(td11);
        newRow.append(td12);
        newRow.append(td13);
        table.append(newRow);



   });*/

                   $("#bs_pag").bs_pagination({
                                   'currentPage':page,//当前页
                                   'rowsPerPage':pageSize,
                                   'totalPages':totalPages,//必填，总页数
                                   'totalRows':papers.length,
                                   'visiblePageLinks':5,//分页按钮最多可显示5个页码
                                   'showGoToPage':true,//显示跳转页面
                                   'showRowsInfo':true,
                                   'onChangePage':function(event,pageObj){
                                       //变更页码时，回调此函数
                                       //pageObj可以返回当前切换到的页面和当前页面显示条数
                                       var notes=$("textarea[name='note']");
                                       console.log(notes);
                                       notes.each(function(){
                                          var index=$(this).attr('tid');
                                          papers[index].note=$(this).val();
                                       });

                                       constructPapers(papers,tab,pageObj.currentPage,pageObj.rowsPerPage);
                                   }
                               });
}

var constructProjects=function(projects,tab,page,pageSize){
   var table=$("#"+tab);
    table.html("");
    var start=(page-1)*pageSize;
    var end=(pageSize+start>projects.length)?projects.length:start+pageSize;
    var totalPages=0;
    if(projects.length%pageSize==0){
        totalPages=projects.length/pageSize;
    }else{
        totalPages=parseInt(projects.length/pageSize)+1
    }
    for(var index=0;index<end;index++){
        var obj=projects[index];
         var newRow=$("<tr>");
         newRow.attr("tid",index);
         var td1=$("<td>");
         var td2=$("<td>");
         var td3=$("<td>");
         var td4=$("<td>");
         var td5=$("<td>");
         var td6=$("<td>");
         var td7=$("<td>");
         var td8=$("<td>");
         var td9=$("<td>");
         var td10=$("<td>");
         var td11=$("<td>");
         var td12=$("<td>");
         var td13=$("<td>");
         td1.append(obj.pid);
         td2.append(obj.pname);
         td3.append(obj.money);
         td4.append(obj.type);
         td5.append(obj.phost);
         td6.append(obj.account);
         td7.append(obj.informant);
         td8.append(obj.email);
         td9.append(obj.date);
         td10.append(obj.charge);
         var select=$("<select>");
         select.attr("tid",index);
         select.attr("name","isGrant");
         select.append("<option value=1>是</option><option value=0>否</option> ");
         select.val(obj.isGrant);
         td11.append(select);
         td12.attr('id',"bonus-"+index);
         td12.append(obj.bonus);
         var note=$("<textarea>");
         note.attr('name',"note");
         note.attr('tid',index);
         note.val(obj.note);
         td13.append(note);
         newRow.append(td1);
         newRow.append(td2);
         newRow.append(td3);
         newRow.append(td4);
         newRow.append(td5);
         newRow.append(td6);
         newRow.append(td7);
         newRow.append(td8);
         newRow.append(td9);
         newRow.append(td10);
         newRow.append(td11);
         newRow.append(td12);
         newRow.append(td13);
         table.append(newRow);


    }
    /*
    $.each(obj,function(index,obj){
        var newRow=$("<tr>");
        newRow.attr("tid",index);
        var td1=$("<td>");
        var td2=$("<td>");
        var td3=$("<td>");
        var td4=$("<td>");
        var td5=$("<td>");
        var td6=$("<td>");
        var td7=$("<td>");
        var td8=$("<td>");
        var td9=$("<td>");
        var td10=$("<td>");
        var td11=$("<td>");
        var td12=$("<td>");
        var td13=$("<td>");
        td1.append(obj.pid);
        td2.append(obj.pname);
        td3.append(obj.money);
        td4.append(obj.type);
        td5.append(obj.phost);
        td6.append(obj.account);
        td7.append(obj.informant);
        td8.append(obj.email);
        td9.append(obj.date);
        td10.append(obj.charge);
        var select=$("<select>");
        select.attr("tid",index);
        select.attr("name","isGrant");
        select.append("<option value=1>是</option><option value=0>否</option> ");
        select.val(obj.isGrant);
        td11.append(select);
        td12.attr('id',"bonus-"+index);
        td12.append(obj.bonus);
        var note=$("<textarea>");
        note.attr('name',"note");
        note.attr('tid',index);
        note.val(obj.note);
        td13.append(note);
        newRow.append(td1);
        newRow.append(td2);
        newRow.append(td3);
        newRow.append(td4);
        newRow.append(td5);
        newRow.append(td6);
        newRow.append(td7);
        newRow.append(td8);
        newRow.append(td9);
        newRow.append(td10);
        newRow.append(td11);
        newRow.append(td12);
        newRow.append(td13);
        table.append(newRow);

    });*/



    $("#bs_pag").bs_pagination({
                                    'currentPage':page,//当前页
                                    'rowsPerPage':pageSize,
                                    'totalPages':totalPages,//必填，总页数
                                    'totalRows':projects.length,
                                    'visiblePageLinks':5,//分页按钮最多可显示5个页码
                                    'showGoToPage':true,//显示跳转页面
                                    'showRowsInfo':true,
                                    'onChangePage':function(event,pageObj){
                                        //变更页码时，回调此函数
                                        //pageObj可以返回当前切换到的页面和当前页面显示条数
                                        var notes=$("textarea[name='note']");
                                        console.log(notes);
                                        notes.each(function(){
                                           var index=$(this).attr('tid');
                                           projects[index].note=$(this).val();
                                        });
                                        constructProjects(projects,tab,pageObj.currentPage,pageObj.rowsPerPage);
                                    }
                                });

}

 var createResultSummary=function(results,tab,page,pageSize){

    var table=$("#"+tab);
    table.html("");
    var start=(page-1)*pageSize;
    var end=(pageSize+start>results.length)?results.length:start+pageSize;
    var totalPages=0;
    if(results.length%pageSize==0){
        totalPages=results.length/pageSize;
    }else{
        totalPages=parseInt(results.length/pageSize)+1
    }
    for(var i=start;i<end;i++){
        var tr=$("<tr>");
        var td1=$("<td>");
        var td2=$("<td>");
        var td3=$("<td>");
        var td4=$("<td>");
        var td5=$("<td>");
        var td6=$("<td>");
        var td7=$("<td>");
        var td8=$("<td>");
        var td9=$("<td>");
        var td10=$("<td>");
        var td11=$("<td>");
        var td12=$("<td>");
        td1.html(results[i].username);
        td2.html(results[i].userid);
        td3.html(results[i].charge);
        var totalResult=results[i].paper+results[i].project+results[i].rewardResidueLast;
        results[i].total_result=totalResult;
        td4.html(results[i].total_result);
        td5.html(results[i].paper);
        td6.html(results[i].project);
        var reward=0;
        var rewardResidue=0;
        var chargeResidue=0;
        if(results[i].charge+results[i].chargeResidueLast>results[i].total_result){
            reward=results[i].total_result;
            chargeResidue=results[i].charge+results[i].chargeResidueLast-results[i].total_result;
        }else{
            reward=results[i].charge+results[i].chargeResidueLast;
            rewardResidue=results[i].total_result-(results[i].charge+results[i].chargeResidueLast);
        }
        results[i].reward=reward;
        results[i].rewardResidue=rewardResidue;
        results[i].chargeResidue=chargeResidue;
        td7.html(results[i].reward);
        td8.html(results[i].rewardResidue);
        td9.html(results[i].rewardResidueLast);
        td10.html(results[i].chargeResidue);
        td11.html(results[i].chargeResidueLast);
        var note=$("<textarea>");
        note.attr('name',"note");
        note.attr('tid',i);
        note.val(results[i].note);
        td12.append(note);
        tr.append(td1);
        tr.append(td2);
        tr.append(td3);
        tr.append(td4);
        tr.append(td5);
        tr.append(td6);
        tr.append(td7);
        tr.append(td8);
        tr.append(td9);
        tr.append(td10);
        tr.append(td11);
        tr.append(td12);
        table.append(tr);


    }
    $("#bs_pag").bs_pagination({
                                     'currentPage':page,//当前页
                                     'rowsPerPage':pageSize,
                                     'totalPages':totalPages,//必填，总页数
                                     'totalRows':results.length,
                                     'visiblePageLinks':5,//分页按钮最多可显示5个页码
                                     'showGoToPage':true,//显示跳转页面
                                     'showRowsInfo':true,
                                     'onChangePage':function(event,pageObj){
                                         //变更页码时，回调此函数
                                         //pageObj可以返回当前切换到的页面和当前页面显示条数
                                         var notes=$("textarea[name='note']");
                                         console.log(notes);
                                         notes.each(function(){
                                            var index=$(this).attr('tid');

                                            results[index].note=$(this).val();
                                            console.log("【test】"+results[index].note);
                                         });
                                         createResultSummary(results,tab,pageObj.currentPage,pageObj.rowsPerPage);
                                     }
                                 });

   }