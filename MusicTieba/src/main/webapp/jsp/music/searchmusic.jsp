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
<title>搜索音乐</title>
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

table td {
	border: 2px solid #ccc;
}

.musicid {
	width: 100px;
}

.musicname {
	width: 500px;
}

.singer {
	width: 200px;
}

.album {
	width: 200px;
}

.year {
	width: 300px;
}
</style>
</head>
<script type="text/javascript" charset="utf-8">

var un='${sessionScope.ui.userName}';
var pu='${sessionScope.ui.photoUrl}';
var ut='${sessionScope.ui.admin}'==='true';
var oldsk='${PageBean.key}';
var count = '${PageBean.count}';
var newsk='';
var topageno=1;
var pageno = ${PageBean.pageLength};
var nowpageno = ${PageBean.nowPageNo};
var link='';


function listinfomation(){
	document.getElementById("listinfo").innerHTML = "共" + pageno + "页，" + count + "条记录，当前第"
		+ nowpageno + "页";
}

function searchmusic(newsk,topageno){
	link = "<%=basePath%>music/searchmusic?newsk=" + newsk + "&topageno="
				+ topageno;
		location.href = link;
	}

	function dosearch() {
		newsk = document.getElementById("newsk").value;
		searchmusic(newsk, topageno);
	}
	function firstpage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = 1;
			searchmusic(oldsk, topageno);
		}
	}
	function previouspage() {
		if (nowpageno == 1) {
			alert("已经是第一页了！");
		} else {
			topageno = nowpageno - 1;
			searchmusic(oldsk, topageno);
		}
	}
	function nextpage() {
		if (nowpageno == pageno) {
			alert("没有下一页了!");
		} else {
			topageno = nowpageno + 1;
			searchmusic(oldsk, topageno);
		}
	}
	function lastpage() {
		if (pageno == nowpageno) {
			alert("已经是最后一页了！");
		} else {
			topageno = pageno;
			searchmusic(oldsk, topageno);
		}
	}

	function jumppage() {
		var t = document.getElementById("jump").value;
		if (!isNaN(t)) {
			if (t == 0) {
				alert("无效页数");
			} else {
				if (t > pageno) {
					alert("无效页数");

				} else {
					topageno = t;
					searchmusic(oldsk, topageno);
				}
			}
		} else {
			alert("请输入数字");
		}
	}
	function managemusic() {
		if (ut) {
			document.getElementById("managemusic").style.display = "";
		} else {
			document.getElementById("managemusic").style.display = "none";
		}
	}

	function isMenber() {
		if (un.length == 0) {
			document.getElementById("upmusic").style.display = "none";
			document.getElementById("userinfo").style.display = "none";
			document.getElementById("login_tips").style.display = "";
		} else {
			document.getElementById("upmusic").style.display = "";
			document.getElementById("userinfo").style.display = "";
			document.getElementById("login_tips").style.display = "none";
		}
	}

	window.onload = function() {
		listinfomation();
		isMenber();
		managemusic();
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
				<span><b>会员:${sessionScope.ui.userName},您好！ </b></span>
			</div>
			<div id="login_tips">
				<b>游客您好,只有登录了之后才能听歌哦！</b>
			</div>
			<div id="button_a">
				<input type="button" id="upmusic" value="上传歌曲"
					onclick="javascript:window.location.href='<%=basePath%>music/addmusic';">
				<input type="button" value="返回主页"
					onclick="javascript:window.location.href='<%=basePath%>mainpage';">
				<input id="managemusic" type="button" value="音乐管理"
					onclick="javascript:window.location.href='<%=basePath%>music/managemusic';">
			</div>
		</div>
	</div>


	<div>
		<div id="searchaction">
			搜索歌曲：<input type="text" id="newsk" placeholder="歌曲或歌手或专辑名"><input
				id="dosearch" type="button" value="搜索" onclick="dosearch();">
		</div>
		<div id="musiclist">
			<form>
				<table>
					<tr>
						<td>序号</td>
						<td>歌名</td>
						<td>艺术家</td>
						<td>专辑</td>
						<td>发行时间</td>
					</tr>
					<c:forEach items="${PageBean.source}" var="music"
						varStatus="status">
						<tr class="musicstyle">
							<td class="musicid">${music.musicId}</td>
							<td class="musicname"><a
								href="<%=basePath%>music/listenandread?mid=${music.musicId}">${music.musicName}</a></td>
							<td class="singer"><c:choose>
									<c:when test="${!empty music.singer}">
									${music.singer}
									</c:when>
									<c:otherwise>无</c:otherwise>
								</c:choose></td>
							<td class="album"><c:choose>
									<c:when test="${!empty music.album}">
									${music.album}
									</c:when>
									<c:otherwise>无</c:otherwise>
								</c:choose></td>
							<td class="year"><c:choose>
									<c:when test="${!empty music.year}">
									${music.year}
									</c:when>
									<c:otherwise>无</c:otherwise>
								</c:choose></td>
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
	</div>
</body>
</html>