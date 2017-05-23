<%@ page language="java"
	import="com.java.util.*,com.musictieba.entity.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>看帖</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#web_head {
	width: 100%;
	height: 200px;
}

#head_title {
	float: left;
	height: 200px;
	font-size: 150px;
	color: #f0f8ff;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135;
	height: 200px;
}

#user_a {
	float: right;
	width: 25%;
	height: 200px;
	text-align: center;
	background-image: url("../img/after_login.jpg");
	background-size: 100% 100%;
}

#photo {
	width: 80px;
	height: 80px;
	margin: 30px 20px 10px 20px;
}

#login_tips {
	text-align: center;
	margin: 50px;
}

#button_a {
	float: right;
	margin: 10px 85px 10px 85px;
}

#searchnote {
	height: 30px;
	margin: 10px;
}

table td {
	border: 2px solid #ccc;
}

.noteid {
	width: 100px;
}

.notetitle {
	width: 600px;
}

.noteusername {
	width: 130px;
}

.notecreatetime {
	width: 180px;
}

.notelasttime {
	width: 180px;
}

.notefloors {
	width: 100px;
}

.addnote {
	padding: 0 200px 0 200px;
}

#listinfo {
	text-align: center;
	margin: 5px;
	font-size: 30px;
}

#jump_a {
	width: 40px;
}

#query_action {
	margin: 10px;
}
</style>
</head>
<link href="<%=basePath%>/uediter/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>/uediter/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/uediter/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/uediter/umeditor.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/uediter/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8">

var un='${sessionScope.ui.userName}';
var pu='${sessionScope.ui.photoUrl}';
var ut='${sessionScope.ui.admin}'==='true';

var oldsk='${PageBean.key}';
var count = '${PageBean.count}';
var newsk = '';
var pageno = ${PageBean.pageLength};
var nowpageno = ${PageBean.nowPageNo};
var topageno = 1;
var link = '';
var msg  = '${msg}';

	function listinfomation(){
			if(count == 0){
				alert("这个贴吧太冷清了，留下点回忆吧！");	
			}
		document.getElementById("listinfo").innerHTML = "共" + pageno + "页，" + count + "条记录，当前第"
			+ nowpageno + "页";
	}
	
	function isMember() {
		if (un.length == 0) {
			document.getElementById('addnote').style.display = "none";
			document.getElementById('userinfo').style.display = "none";
			document.getElementById('mynote').style.display="none";
			document.getElementById('login_tips').style.display = "";
		} else {
			document.getElementById('addnote').style.display = "";
			document.getElementById('userinfo').style.display = "";
			document.getElementById('mynote').style.display = "";
			document.getElementById('login_tips').style.display = "none";
		}
	}
	
	
	function havevalue(){
		var temp = document.getElementById("newsk").value;
		if(temp.length != 0){
			newsk=temp;
		}else{
			if(oldsk.length != 0){
				newsk=oldsk;
			}
		}
	}
	
	function searchnote(){
		newsk= document.getElementById("newsk").value;
		link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
		location.href = link;
	}
	
	function firstpage() {	
		if(nowpageno==1){
			alert("已经是第一页了！");
		}else{
			topageno = 1;
			havevalue();
			link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	
	function previouspage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = nowpageno - 1;
			havevalue();
			link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function nextpage() {
		if (nowpageno == pageno) {
			alert("没有下一页了!");
		} else {
			topageno = nowpageno + 1;
			havevalue();
			link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function lastpage() {
		topageno = pageno;
		if(topageno == nowpageno){
			alert("已经是最后一页了！");
		}else{
			havevalue();
			link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	
	function jumppage(){
		var t = document.getElementById("jump").value;
		if(!isNaN(t)){
			if(t==0){
				alert("无效页数");
			}else{
				if(t>pageno){
					alert("无效页数");	
				}else{
					topageno = t;
					havevalue();
					link = "<%=basePath%>note/listandadd?newsk=" + newsk + "&topageno="+ topageno;
					location.href = link;
				}
			}
		}else{
			alert("请输入数字");
		}
	}

	function isAdmin(){
		if(ut){
			document.getElementById('managenotes').style.display = "";
		}else{
			document.getElementById('managenotes').style.display="none";
		}
	}
	
	window.onload = function(){
		listinfomation();
		isMember();
		isAdmin();
		showdeleteaction();
	};
</script>

<body>
	<div id="web_head">
		<div id="head_title">随意音乐论坛</div>
		<div id="user_a">
			<div id="userinfo">
				<div>
					<img id="photo" alt="头像"
						src="<%=basePath%>${sessionScope.ui.photoUrl}">
				</div>
				<span><b>会员: ${sessionScope.ui.userName},欢迎登录! </b></span>
			</div>
			<div id="login_tips">
				<b>游客您好,只有登录了之后才能发言哦！</b>
			</div>
			<div id="button_a">
				<input type="button" value="我的帖子" id="mynote"
					onclick="javascript:window.location.href='<%=basePath%>note/mynote';">
				<input type="button" value="返回主页"
					onclick="javascript:window.location.href='<%=basePath%>mainpage';">
				<input id="managenotes" type="button" value="帖子管理"
					onclick="javascript:window.location.href='<%=basePath%>note/managenotes';">
			</div>
		</div>
	</div>

	<div id="searchnote">
		<span>搜索帖子：</span>&nbsp;&nbsp;<input type="text" id="newsk">&nbsp;&nbsp;<input
			type="button" value="搜索" onclick="searchnote();">
	</div>

	<div id="notelist">
		<form>
			<table>
				<tr>
					<td>序号</td>
					<td>标题</td>
					<td>发帖人</td>
					<td>发帖时间</td>
					<td>最后回复时间</td>
					<td>回复量</td>
				</tr>
				<c:forEach items="${PageBean.source}" var="note" varStatus="status">
					<tr class="notestyle">
						<td class="noteid">${note.noteId}</td>
						<td class="notetitle"><a
							href="<%=basePath%>note/readandadd?noteid=${note.noteId}&topageno=">${note.title}</a></td>
						<td class="noteusername">${note.user.userName}</td>
						<td class="notecreatetime"><fmt:formatDate
								value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="notelasttime"><fmt:formatDate
								value="${note.lastTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="notefloors">${note.floors}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<div id="listinfo"></div>
	<div id="query_action">
		<input type="button" value="首页" onclick="firstpage();"> <input
			type="button" value="上一页" onclick="previouspage();"> <input
			type="button" value="下一页" onclick="nextpage();"> <input
			type="button" value="尾页" onclick="lastpage();"> 跳转到第<input
			id="jump_a" type="text" id="jump">页 <input type="button"
			value="跳转" onclick="jumppage();">
	</div>

	<div id="addnote">
		<form id="from4addnote" action="listandadd" method="post"
			class="addnote">
			<b>标题:</b><input type="text" name="title" id="title"
				style="width: 850px; margin: 10px;" /><br>
			<script id="myEditor" type="text/plan" name="content" id="content"
				style="width:900px;height:240px;">
		</script>
			<br>
			<script type="text/javascript">
				var um = UM.getEditor('myEditor');
			</script>
			<input type="submit" value="发帖" "/>
		</form>
	</div>
</body>
</html>