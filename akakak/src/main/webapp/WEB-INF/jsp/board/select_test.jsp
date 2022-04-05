<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/js/util.js"></script>
<script src="/js/paging.js"></script>
</head>
<style>
	h2{
	text-align: center;
	}

	#pageBtn{
	text-align: center;
	}
</style>
<body>
<h2>게시판 리스트</h2>
	<select name="type">
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="name">작성자</option>
	</select>
	<input name="keyword" type="text" value="${keyword}" >
	<button type="button" onclick="boardList(1)">검색</button>

<table class="table">
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>내용</th>
		<th>파일이름</th>
	</tr>
	<tbody id="table" ></tbody>
</table>
<div id="pageBtn"></div>
	<br>
	<a href="/main.do"><button class="btn btn-default">메인</button></a>
	<a href="/insertPage.do"><button class="btn btn-default">글쓰기</button></a>
	<a href="/excelDown.do"><button class="btn btn-default">엑셀다운로드</button></a>
</body>
</html>