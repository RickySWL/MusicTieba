<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提示信息</title>
</head>
<script type="text/javascript">
var msg="${msg}";
var noteid="${noteId}";
switch (msg)
{
case "恭喜您，注册成功!":
	alert(msg);
	window.location.href="<%=basePath%>mainpage/login";
  	break;
case "已经存在相同的账号!":
	alert(msg);
	window.location.href="<%=basePath%>mainpage/register";
  	break;  	
case "更新头像成功！":
	alert(msg);
	window.location.href="<%=basePath%>mainpage/changeinfo";
	break;
case "您已退出成功!":	
	alert(msg);
	window.location.href="<%=basePath%>mainpage/login";
	break;
case "注销成功！":	
	alert(msg);
	window.location.href="<%=basePath%>mainpage/login";
	break;
case "发帖成功！":	
	alert(msg);
	window.location.href="<%=basePath%>note/listandadd";
	break;
case "回帖成功！":	
	alert(msg);
	window.location.href="<%=basePath%>note/readandadd?noteid="+noteid+"&topageno=";
	break;	
case "删除回复成功！":	
	alert(msg);
	window.location.href="<%=basePath%>note/readandadd?noteid="+noteid+"&topageno=";
	break;	
case "删除帖子成功！":	
	alert(msg);
	window.location.href="<%=basePath%>note/listandadd";
	break;
case "上传歌曲成功！":	
	alert(msg);
	window.location.href="<%=basePath%>music/searchmusic";
	break;
case "数据丢失！":	
	alert(msg);
	window.location.href="<%=basePath%>mainpage";
	break;	
case "修改信息成功！":	
	alert(msg);
	window.location.href="<%=basePath%>music/searchmusic";
	break;	
case "删除歌曲成功！":	
	alert(msg);
	window.location.href="<%=basePath%>music/searchmusic";
	break;			
default:
	alert(msg);
	window.location.href="<%=basePath%>mainpage";
	break;
	}
</script>
<body>

</body>
</html>