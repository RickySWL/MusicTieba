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
<title></title>
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

#prompt{
	text-align: center;
	margin: 50px;
}

table td {
	border: 2px solid #ccc;
}

#n_info_a {
	border: 2px solid #ccc;
	float: right;
	width: 29%;
}

.notetitle {
	text-align: center;
	font-size: 40px;
	margin: 20px 0px;
}

.noteid {
	border: 2px solid #ccc;
	margin: 10px;
}

.noteusername {
	border: 2px solid #ccc;
	margin: 10px;
}

.notecontent {
	border: 2px solid #ccc;
	width: 70%;
	float: left;
	height: auto !important;
	height: 600px;
	min-height: 600px;
}

.notecreatetime {
	border: 2px solid #ccc;
	margin: 10px;
}

.notelasttime {
	border: 2px solid #ccc;
	margin: 10px;
}

.notefloors {
	border: 2px solid #ccc;
	margin: 10px;
}

.readnote {
	height: auto !important;
	height: 600px;
	min-height: 600px;
}

.commentcontent {
	width: 635px;
	height: auto !important;
	height: 200px;
	min-height: 200px;
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
<script type="text/javascript" charset="utf-8" >
	var un='${sessionScope.ui.userName}';
	var pu='${sessionScope.ui.photoUrl}';
	var ut='${sessionScope.ui.admin}'==='true';
	var noteId = ${note.noteId};
	var notetitle = "${note.title}";
	var link="";
	var count = '${PageBean.count}';
	var pageno = ${PageBean.pageLength};
	var nowpageno = ${PageBean.nowPageNo};
	var topageno = 1;

	function ready2read() {
			document.getElementById("noteId").value = noteId;
			document.title = notetitle;
	}

	function listinfomation(){
		if(count == 0){
			alert("这个话题太冷清了，什么也没有！留下点回忆吧！");	
			document.getElementById("web_last").style.display="none";
		}
		document.getElementById("listinfo").innerHTML = "共" + pageno + "页，" + count + "条记录，当前第"
			+ nowpageno + "页";
	}
	
	
	function isMember() {
		if (un.length == 0) {
			document.getElementById('addcomment').style.display = "none";
			document.getElementById('userinfo').style.display = "none";
			document.getElementById('prompt').style.display = "";
		} else {
			document.getElementById('addcomment').style.display = "";
			document.getElementById('userinfo').style.display = "";
			document.getElementById('prompt').style.display = "none";
		}
	}

	function firstpage() {	
		if(nowpageno==1){
			alert("已经是第一页了！");
		}else{
			topageno = 1;
			link = "<%=basePath%>note/readandadd?noteid=" + noteId + "&topageno="+ topageno;
			location.href = link;
		}
	}
	
	function previouspage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = nowpageno - 1;
			link = "<%=basePath%>note/readandadd?noteid=" + noteId + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function nextpage() {
		if (nowpageno == pageno) {
			alert("没有下一页了!");
		} else {
			topageno = nowpageno + 1;
			link = "<%=basePath%>note/readandadd?noteid=" + noteId + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function lastpage() {
		topageno = pageno;
		if(topageno == nowpageno){
			alert("已经是最后一页了！");
		}else{
			link = "<%=basePath%>note/readandadd?noteid=" + noteId + "&topageno="+ topageno;
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
					link = "<%=basePath%>note/readandadd?noteid=" + noteId + "&topageno="+ topageno;
					location.href = link;
				}
			}
		}else{
			alert("请输入数字");
		}
	}
	
	function deletecomment(cid){
		if(confirm('注意该行为会删除回复，是否继续？')){
			window.location.href="<%=basePath%>note/deletecomment?noteid="+noteId+"&cid="+cid; 
		}
	}
	function deletenote(){
		if(confirm('注意该行为会删除帖子，是否继续？')){
			window.location.href="<%=basePath%>note/deletenote?noteid="+noteId; 
		}
	}
	
	function showdeleteaction(){
		var da;
		var i;
		if(ut){
			da =document.getElementsByClassName('deleteaction');
			for (i = 0; i < da.length; i++) {
			da[i].style.display = "";}
		}else{
			da =document.getElementsByClassName('deleteaction');
			for (i = 0; i < da.length; i++) {
			da[i].style.display = "none";}
		}
	}
	
	
	window.onload = function() {
		ready2read();
		isMember();
		listinfomation();
		showdeleteaction();
	};
</script>
<body>
	<div id="web_head">
		<div id="head_title">随意音乐论坛</div>
		<div id="user_a">
			<div id="userinfo">
				<div>
					<img id="photo" alt="头像" src="<%=basePath%>'${sessionScope.ui.photoUrl}'">
				</div>
				<span><b>会员:${sessionScope.ui.userName},您好!
				</b></span>
			</div>
			<div id="prompt">
				<b>游客您好,只有登录了之后才能发言哦！</b>
			</div>
			<div>
				<input type="button" value="返回主页"
					onclick="javascript:window.location.href='<%=basePath%>mainpage';">
				<input type="button" value="返回论坛"
					onclick="javascript:window.location.href='<%=basePath%>note/listandadd';">
			</div>
		</div>
	</div>


	<div class="readnote">
		<div class="notetitle">${note.title}</div>

		<div class="notecontent">${note.content}</div>
		<div id="n_info_a">
			<div class="noteid">
				帖子号：${note.noteId}<input class="deleteaction" type="button"
					value="删除" onclick="deletenote();">
			</div>
			<div class="noteusername">楼主：${note.user.userName}</div>
			<div class="notecreatetime">发帖时间：<fmt:formatDate value="${note.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			<div class="notelasttime">最后回复时间：<fmt:formatDate value="${note.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			<div class="notefloors">楼层数：${note.floors}</div>
		</div>
	</div>

	<div id="web_last">
		<div id="readcomment">
			<from>
			<table>
				<tr>
					<td>楼层</td>
					<td>内容</td>
					<td>回帖人</td>
					<td>回帖时间</td>
				</tr>
				<c:forEach items="${PageBean.source}" var="comment" varStatus="status">
					<tr>
						<td>${(PageBean.nowPageNo-1)*15+(status.index+1)}</td>
						<td><div class="commentcontent">${comment.content}
								<input class="deleteaction" type="button" value="删除"
									onclick="deletecomment(${comment.commentId});">
							</div></td>
						<td>${comment.user.userName}</td>
						<td><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
			</table>
			</from>
		</div>


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




	<div id="addcomment">
		<form id="form4comment" action="readandadd" method="post"
			class="addnote">
			<input type="hidden" name="noteId" id="noteId" /><br>
			<script id="myEditor" type="text/plan" name="content" id="content"
				style="width:935px;height:250px;">
		</script>
			<br>
			<script type="text/javascript">
				var um = UM.getEditor('myEditor');
			</script>
			<input type="submit" value="回帖" />
		</form>
	</div>
</body>
</html>