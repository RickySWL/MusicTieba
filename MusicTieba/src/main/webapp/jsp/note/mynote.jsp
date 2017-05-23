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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的帖子</title>
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

#button_a {
	float: right;
	margin: 10px 85px 10px 85px;
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
</style>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
var un='${sessionScope.ui.userName}';
var pu='${sessionScope.ui.photoUrl}';
var ut='${sessionScope.ui.admin}'==='true';
var oldsk='${PageBean.key}';
var count = '${PageBean.count}';
var newsk = "";
var pageno = ${PageBean.pageLength};
var nowpageno = ${PageBean.nowPageNo};
var topageno = 1;
var link = '';
var msg  = "${msg}";


$(document).ready(function(){
	if(pu.length == 0){
		$("#photo").hide();
		$("#nophoto").show();
	}else{
		$("#photo").show();
		$("#nophoto").hide();
	}
	listinfomation();
});

function listinfomation(){
	if(count == 0){
		alert("没有一条记录");	
		$("#web_last").hide();
	}
	if (count < 20 && count > 0) {
		pageno = 1;
	} else {
		pageno = Math.ceil(count/20);
	}
	var pageinfo="共" + pageno + "页，" + count + "条记录，当前第"+ nowpageno + "页";
	$("#listinfo").append(pageinfo);
	
}


function havevalue(){
	var temp = $("#newsk").val();
	if(temp.length != 0){
		newsk=temp;
	}else{
		if(oldsk.length != 0){
			newsk=oldsk;
		}
	}
}

function searchnote(){
	newsk = $("#newsk").val();
	link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
	location.href = link;
}

function firstpage() {	
	if(nowpageno==1){
		alert("已经是第一页了！");
	}else{
		topageno = 1;
		havevalue();
		link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
		location.href = link;
	}
}

function previouspage() {
	if (nowpageno == 1) {
		alert("已经是第一页了！");
	} else {
		topageno = nowpageno - 1;
		havevalue();
		link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
		location.href = link;
	}
}
function nextpage() {
	if (nowpageno == pageno) {
		alert("没有下一页了!");
	} else {
		topageno = nowpageno + 1;
		havevalue();
		link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
		location.href = link;
	}
}
function lastpage() {
	topageno = pageno;
	if(topageno == nowpageno){
		alert("已经是最后一页了！");
	}else{
		havevalue();
		link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
		location.href = link;
	}
}

function jumppage(){
	var t = $("#jump").val();
	if(!isNaN(t)){
		if(t==0){
			alert("无效页数");
		}else{
			if(t>pageno){
				alert("无效页数");	
			}else{
				topageno = t;
				havevalue();
				link = "<%=basePath%>note/mynote?newsk=" + newsk + "&topageno="+ topageno;
				location.href = link;
			}
		}
	}else{
		alert("请输入数字");
	}
}

function deletenote(noteid){
	if(confirm('注意该行为会删除帖子，是否继续？')){
		window.location.href="<%=basePath%>note/deletenote?noteid="+noteid; 
	}
}


</script>
<body>
	<div id="web_head">
		<div id="head_title">随意音乐论坛</div>
		<div id="user_a">
			<div id="userinfo">
				<div>
					<img id="photo" alt="头像" src="<%=basePath%>${sessionScope.ui.photoUrl}">
				</div>
				<span><b>会员:${sessionScope.ui.userName},您好！
				</b></span>
			</div>
			<div id="button_a">
				<input type="button" value="返回论坛"
					onclick="javascript:window.location.href='<%=basePath%>note/listandadd';">
			</div>
		</div>
	</div>
	<div id="searchnote">
		搜索帖子：<input type="text" id="newsk"><input type="button"
			value="搜索" onclick="searchnote();">
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
							href="<%=basePath%>note/readandadd?noteid=${note.noteId}&topageno=">${note.title}</a><input
							class="deleteaction" type="button" value="删除"
							onclick="deletenote(${note.noteId});"></td>
						<td class="noteusername">${note.user.userName}</td>
						<td class="notecreatetime"><fmt:formatDate value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="notelasttime"><fmt:formatDate value="${note.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="notefloors">${note.floors}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<div id="web_last">
		<div id="listinfo"></div>
		<div id="queryaction">
			<input type="button" value="首页" onclick="firstpage();"> <input
				type="button" value="上一页" onclick="previouspage();"> <input
				type="button" value="下一页" onclick="nextpage();"> <input
				type="button" value="尾页" onclick="lastpage();"> 跳转到第<input
				type="text" id="jump">页 <input type="button" value="跳转"
				onclick="jumppage();">
		</div>
	</div>
</body>
</html>