<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>maindata-query</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->

<%@include file="/public/head.jspf"%>
<script type="text/javascript">
    $(function(){
    	$('#dg').datagrid({
    		
    		//DataGrid设置
            //请求数据的url地址
           url:'mainData_queryAll.action',   
            //在从远程站点加载数据的时候显示提示消息
            loadMsg:'请等待。。。',
            //显示斑马线效果
            striped:true,
            //如果为true，则在同一行中显示数据。设置为true可以提高加载性能
            nowrap:true,
            //真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动
            fitColumns:false,
            //设置为多选，如果为true，则只允许选择一行
            singleSelect:false,
            //如果为true，则在DataGrid控件底部显示分页工具栏
            pagination:true,
            //默认为'bottom'，定义分页工具栏的位置。可用的值有：'top','bottom','both'
            //pagePosition:'bottom',
            //在设置分页属性的时候初始化页面大小
            pageSize:10,
            //在设置分页属性的时候 初始化页面大小选择列表
            pageList:[10,20,30,40,50],
            //width设置宽度
            //width:900,
            
            columns:[[       
           {field:'id',title:'期号'},    
           {field:'first',title:'第一位'},
           {field:'second',title:'第二位'},    
           {field:'third',title:'第三位'},
           {field:'fourth',title:'第四位'},
           {field:'fifth',title:'第五位'},
           {field:'sixth',title:'第六位'},
           {field:'seventh',title:'第七位'},
           
           {field:'qhz',title:'前和值'},
           {field:'hhz',title:'后和值'},
           {field:'qjob',title:'前奇偶比'},
           {field:'hjob',title:'后奇偶比'},
           {field:'qxdb',title:'前小大比'},
           {field:'hxdb',title:'后小大比'},

           
           {field:'date',title:'开奖日期'} 
             ]]    

    	});
    });
	
</script>



</head>

<body>
	<table id="dg"></table>
</body>
</html>
