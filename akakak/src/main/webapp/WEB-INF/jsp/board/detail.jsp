<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.fileupload.js"></script>
<style type="text/css">
.title_label {
	border: 1px dotted #ddd;
}

img {
	vertical-align: middle;
	margin-left: 20%;
	width: 50%;
	height: 50%;
}
</style>

</head>
<body>
	<div class="container">
		<h1>게시판 글보기</h1>

		<!-- 데이터 표시하는 부분 -->
		<ul class="list-group">
			<li class="list-group-item row">
				<div class="col-md-2 title_label">번호</div>
				<div class="col-md-10">${data.idx}</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">작성자</div>
				<div class="col-md-10">${data.name}</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">제목</div>
				<div class="col-md-10">${data.title}</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">내용</div>
				<div class="col-md-10">
					<pre>${data.content}</pre>
				</div>
			</li>
			<li class="list-group-item row">
				<div class="col-md-2 title_label">파일</div>
				<div class="col-md-10" id="fileInfo">
<%-- <pre><a href="/fileDown.do?filename=${data.file_name}">${data.file_name}</a></pre> --%>
				<pre><a href="/fileDown.do?fileName=${data.file_name}">${data.file_real_name}</a></pre>
				</div>
			</li>
		</ul>
		<img src='/img/${data.file_name}'><br> <a href="/updatePage.do?no=${data.idx}" class="btn btn-default">수정</a> 
		<a class="btn btn-default" href="/delete.do?no=${data.idx}">삭제</a> 
<%-- 		<a href="/select.do?page=${nowPage}" class="btn btn-default">뒤로가기</a> --%>
		<a href="/test.do?nowPage=${nowPage}" class="btn btn-default">뒤로가기</a>
	</div>
</body>
</html>