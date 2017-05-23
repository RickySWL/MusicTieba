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
			link = "<%=basePath%>mainpage/userlist?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function previouspage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = nowpageno - 1;
			havevalue();
			link = "<%=basePath%>mainpage/userlist?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function nextpage() {
		if (nowpageno == pageno) {
			alert("没有下一页了!");
		} else {
			topageno = nowpageno + 1;
			havevalue();
			link = "<%=basePath%>mainpage/userlist?newsk=" + newsk + "&topageno="+ topageno;
			location.href = link;
		}
	}
	function lastpage() {
		topageno = pageno;
		if(topageno == nowpageno){
			alert("已经是最后一页了！");
		}else{
			havevalue();
			link = "<%=basePath%>mainpage/userlist?newsk=" + newsk + "&topageno="+ topageno;
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
					link = "<%=basePath%>mainpage/userlist?newsk=" + newsk + "&topageno="+ topageno;
					location.href = link;
				}
			}
		}else{
			alert("请输入数字");
		}
	}
	function changeUserStatus(uid){
		var r=confirm('注意该行为会改变当前用户状态用户，是否继续？')
		if(r){
			link = '<%=basePath%>mainpage/changeuserstatus';
			location.href = link;
		}
	}
	function changeUserStatus() {
		var uids ='';
		var r=confirm('注意该行为会改变用户状态，是否继续？')
		if(r){
			$("input[type='checkbox']").each(function(i){
				   if($(this).prop("checked")){
					  uids+=($(this).prop("id"))+',';
					}
				});
			if(uids.length!=0){
				$.ajax({
					data :{'uids':uids},
					type : "POST",
					dataType : 'json',
					url : "<%=basePath%>mainpage/changeuserstatus",
					error : function(data) {
						alert("出错了！！:" + data.msg);
					},
					success : function(data) {
						alert(data.msg);
						location.href = '<%=basePath%>mainpage/userlist';
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
	<div id="web_title">用户管理</div>
	<div>
		<input type="button" value="返回主页"
			onclick="javascript:window.location.href='<%=basePath%>mainpage';">
		<form action="userlist" method="get">
			<input type="txt" placeholder="请输入用户名..." name="newsk" id="newsk">
			<input type="submit" value="搜索">
		</form>
	</div>
	<div id="userinfo">
		<form>
			<table border="1" cellspacing="0">
				<tr>
					<td>选择</td>
					<td>序号</td>
					<td>用户名</td>
					<td>账号</td>
					<td>密码</td>
					<td>邮箱</td>
					<td>可登陆状态</td>
					<td>创建时间</td>
				</tr>
				<c:forEach items="${PageBean.source}" var="user" varStatus="status">
					<tr>
						<td style="width:50px"><input id="${user.userId}"
							class="cb" type="checkbox"></td>
						<td style="width:50px">${user.userId}</td>
						<td style="width:120px">${user.userName}</td>
						<td style="width:120px">${user.account}</td>
						<td style="width:120px">${user.password}</td>
						<td style="width:200px">${user.email}</td>
						<td style="width:120px"><c:choose>
								<c:when test="${user.status}">
								可登陆
   							</c:when>
								<c:otherwise> 
								不可登陆
   							</c:otherwise>
							</c:choose></td>
						<td style="width: "><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<div id="operation">
			<input type="button" value="全选" onclick="checkedAll()"> <input
				type="button" value="取消" onclick="giveUpAll()"> <input
				type="button" value="改变登录状态" onclick="changeUserStatus()">
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