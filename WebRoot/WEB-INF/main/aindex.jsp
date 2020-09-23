<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html>
<head>

<title>superhappy</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<%@ include file="/public/head.jspf"%>

<style type="text/css">
	   #menu{
	      width:200px;
	      /* border:1px solid red; */
	   }
	   #menu ul{
	         list-syle:none;
	         padding:0px;
	         margin:0px;
	   }
	   #menu ul li{
	      border-bottom: 1px solid #fff;
	   }
	   #menu ul li a{
	    /*  a标签转换为块级元素 ,设置a标签的款和内间距*/
	     display:block;
	     background-color:#008792;
	     color:#fff;
	     padding:5px;
	     text-decoration:none;
	   }
	     /* 当有鼠标悬停在链接上 */
	   #menu ul li a:hover{
	      background-color:#00a6ac;
	   }
	</style>
	
	<script type="text/javascript">
	  $(function(){
	    $("a[title]").click(function(){
	       var text=$(this).text();//获取文本，如商品信息管理，会员信息管理等
	       var href=$(this).attr("title");//获取属性
	       //1.判断当前后边是否以后相应的tab
	        if($("#tt").tabs("exists",text)){
	          //2.如果没有则创建一个新的tab，否则切换到当前tab
	          $("#tt").tabs("select",text);
	        }else{
	        //没有就创建一个
	        $("#tt").tabs("add",{
	        //依赖panel，title在面板头部显示的标题文本
	        title:text,
	        //依赖panel，closable定义是否显示关闭按钮
	        closable:true,
	        //依赖panel，content面板主体内容
	        //content:'<iframe title="类别管理"  src="send_category_query.action" frameborder="0" width="100%" height="100%" />'
	        //<iframe>内联框架，页面中加一个子页面，把iframe解释成“浏览器中的浏览器“很是恰当。
	        //依赖panel，href:默认是通过url地址，加载远程的页面，但是仅仅是body部分，无法获取css样式，js代码。从URL读取远程数据并且显示到面板
	        //href:'send_category_query.action'
	         content:'<iframe title='+text+'  src='+href+' frameborder="0" width="100%" height="100%" />'
	        
	        });
	        }
	    });
	  });
	</script>
</head>

<body class="easyui-layout">

	<div data-options="region:'north',title:'',split:true" style="height: 30px; 
	    background: url(${shop}/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"
	">
<%-- 	<span style="float:right; padding-right:20px;" >管理员:${sessionScope.adminLogin.adminName} <a href="admin_exitLogin.action" ><font color="white">，注销</font></a></span> --%>
    <span style="padding-left:10px; font-size: 16px; "><img src="${superhappy}/images/blocks.gif" "width="20" height="20"" align="absmiddle" />superhappy数据统计分析系统V1</span>
	
	      
	</div>
	
	  <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div style="text-align:center;color:#15428B; margin:0px; padding:0px;line-height:23px; font-weight:bold;">By 小刚制作</div>
    </div>
	
	<div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
	    <!-- 此处显示的是系统菜单 -->
		<div id="menu" class="easyui-accordion" data-options="fit:true">
			<div title="基础数据" >
            <ul>
              <li><a href="#" title="">近期数据查询</a></li>
              <li><a href="#" title="">近15期数据统计分析</a></li>
              <li><a href="#" title="">多条件查询数据</a></li>
              
             </ul>
			</div>
			<div title="数据维护" >
            <ul>
              <li><a href="#" title="send_mainData_input.action">单期数据录入</a></li>
              <li><a href="#" title="send_mainData_batchInput.action">批量导入数据</a></li>
             </ul>
			</div>
			
            
			</div>
			
		</div>

	</div>
	<div data-options="region:'center',title:'后台操作页面'" style="padding:1px;background:#fff;">
	     <div id="tt" class="easyui-tabs" data-options="fit:true">   
             <div title="首页" data-options="closable:true" style="padding:10px;">   
               此处以后显示相应的系统信息（当前系统的类型，当前项目的域名，硬件相关配置，或则显示报表）
               <%-- <img src="${shop}/images/alogin.gif"/> --%>
              </div>   
      </div>  
	</div>
	 <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closable:true"></div>
</body>

</html>


