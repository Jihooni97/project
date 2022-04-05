<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지도</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.13.0/build/ol.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="/js/map.js"></script>
</head>

<body>
	<div id="vmap" style="width: 100%; height: 600px;"></div>
	<br>
	<a href="/main.do"><input type="button" value="뒤로가기" class="btn btn-default" /></a>
	<button onclick="create_point()">point layer추가</button>
<!-- 	<button onclick="btnClick()">point layer 추가</button> -->
	<button onclick="startsetInterval()">마커자동이동</button>
	<button onclick="remove_point()" id="removeBtn">point layer제거</button>

</body>
</html>