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
<title>用户列表</title>
<style type="text/css">
body {
	background-image: url("../img/all_bg.png");
}

#userinfo {
	
}

#web_title {
	height: 150px;
	text-align: center;
	font-size: 100px;
	color: #f0f8ff;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135
}
</style>
</head>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
    var oldsk='${PageBean.key}';
	var count = '${PageBean.count}';
	var newsk = '';
	var pageno = ${PageBean.pageLength};
	var nowpageno = ${PageBean.nowPageNo};
	var topageno = 1;
	var link = '';

	
	$(document).ready(
			function() {
				if(count == 0){
					alert("没有一条记录");	
				}
				$("#pageno").html(
						'<span>共' + pageno + '页，' + count + '条记录，当前第'
								+ nowpageno + '页</span>');
			});
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
	
	function firstpage() {	
		if(nowpageno==1){
			alert("已经是第一页了！");
		}else{
			topageno = 1;
			havevalue();
			link = "<%=basePath%>music/managemusic?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function previouspage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = nowpageno - 1;
			havevalue();
			link = "<%=basePath%>music/managemusic?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function nextpage() {
		if (nowpageno == pageno) {
			alert("没有下一页了!");
		} else {
			topageno = nowpageno + 1;
			havevalue();
			link = "<%=basePath%>music/managemusic?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function lastpage() {
		topageno = pageno;
		if(topageno == nowpageno){
			alert("已经是最后一页了！");
		}else{
			havevalue();
			link = "<%=basePath%>music/managemusic?newsk=" + newsk + "&topageno="+ topageno;
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
					link = "<%=basePath%>music/managemusic?newsk=" + newsk + "&topageno="+ topageno;
					location.href = link;
				}
			}
		}else{
			alert("请输入数字");
		}
	}
	function changeStatus() {
		var nids ='';
		var r=confirm('注意该行为会改变音乐状态，是否继续？')
		if(r){
			$("input[type='checkbox']").each(function(i){
				   if($(this).prop("checked")){
					  nids+=($(this).prop("id"))+',';
					}
				});
			if(nids.length!=0){
				$.ajax({
					data :{'mids':nids},
					type : "POST",
					dataType : 'json',
					url : "<%=basePath%>music/changemusicstatus",
					error : function(data) {
						alert("出错了！！:" + data.msg);
					},
					success : function(data) {
						alert(data.msg);
						location.href = '<%=basePath%>music/managemusic';
					}
				});
			} else {
				alert('请选择后在点此按钮！');
			}

		}
	}
	function checkedAll() {
		$("input[type='checkbox']").each(function(i) {
			$(this).prop("checked", true);
		});
	}
	function giveUpAll() {
		$("input[type='checkbox']").each(function(i) {
			$(this).prop("checked", false);
		});
	}
</script>
<body>
	<div id="web_title">音乐管理</div>
	<div>
		<input type="button" value="返回"
			onclick="javascript:window.location.href='<%=basePath%>music/searchmusic';">
		<form action="managemusic" method="get">
			<input type="txt" placeholder="请输入用帖子..." name="newsk" id="newsk">
			<input type="submit" value="搜索">
		</form>
	</div>
	<div id="userinfo">
		<form>
			<table border="1" cellspacing="0">
				<tr>
					<td>选择</td>
					<td>序号</td>
					<td>歌名</td>
					<td>歌手</td>
					<td>专辑</td>
					<td>发行时间</td>
					<td>上传用户</td>
					<td>上传时间</td>
					<td>当前状态</td>
				</tr>
				<c:forEach items="${PageBean.source}" var="music" varStatus="status">
					<tr>
						<td style="width:50px"><input id="${music.musicId}"
							class="cb" type="checkbox"></td>
						<td style="width:80px">${music.musicId}</td>
						<td style="width:300px"><a
							href="<%=basePath%>music/listenandread?mid=${music.musicId}&topageno=">${music.musicName}</a></td>
						<td style="width:120px">${music.singer}</td>
						<td style="width:120px">${music.album}</td>
						<td style="width:120px">${music.year}</td>
						<td style="width:120px">${music.user.userName}</td>
						<td style="width:200px"><fmt:formatDate
								value="${music.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td style="width:120px"><c:choose>
								<c:when test="${music.status}">
								可浏览
   							</c:when>
								<c:otherwise> 
								不可浏览
   							</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<div id="operation">
			<input type="button" value="全选" onclick="checkedAll()"> <input
				type="button" value="取消" onclick="giveUpAll()"> <input
				type="button" value="改变状态" onclick="changeStatus()">
		</div>
		<div id="pageno"></div>
	</div>
	<div>
		<input type="button" value="首页" onclick="firstpage();"> <input
			type="button" value="上一页" onclick="previouspage();"> <input
			type="button" value="下一页" onclick="nextpage();"> <input
			type="button" value="尾页" onclick="lastpage();">
		<div>
			跳转到第<input id="jump" type="text">页 <input type="button"
				value="跳转" onclick="jumppage();">
		</div>
	</div>
</body>
</html>