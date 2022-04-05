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
	<table class="table">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
			<th>파일이름</th>
		</tr>
		<c:forEach items="${result}" var="vo">
			<tr onClick="location.href='/detailPage.do?no=${vo.idx}&nowPage=${nowPage}'">
				<td>${vo.idx}</td>
				<td>${vo.name}</td>
				<td>${vo.title}</td>
				<td>${vo.content}</td>
				<td>${vo.file_real_name}</td>
			</tr>
		</c:forEach>
	</table>

<div id="pageBtn">
	<c:if test="${prev}">
		<a href="/select.do?page=${nowPage-1}">뒤로</a>
	</c:if>
	<c:forEach begin="${startPage}" end="${endPage}" var="a">
		<a href="/select.do?page=${a}">${a}</a>
	</c:forEach>
	<c:if test="${next}">
		<a href="/select.do?page=${nowPage+1}">다음</a>
	</c:if>
</div>
	<br>
	
	<a href="/main.do"><button class="btn btn-default">메인</button></a>
	<a href="/insertPage.do"><button class="btn btn-default">글쓰기</button></a>
	<a href="/excelDown.do"><button class="btn btn-default">엑셀다운로드</button></a>
</body>
</html>