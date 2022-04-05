<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#cancelBackBtn").click(function() {
			history.back();
		});
	});
</script>
</head>
<body>
	<div class="container">
		<form id="update-form" action="/update.do" method="post" enctype="multipart/form-data">
			<h1>게시판 글수정</h1>
			<div class="form-group">
				<label>번호:</label> <input name="boardIdx" type="text"
					class="form-control" value="${data.idx}"
					readonly="readonly">
			</div>

			<div class="form-group">
				<label>작성자:</label> <input name="boardName" type="text"
					class="form-control" value="${data.name}"
					readonly="readonly">
			</div>

			<div class="form-group">
				<label>제목:</label>

				<input name="boardTitle" type="text" class="form-control"
					pattern=".{1,100}" maxlength="100" required="required"
					title="4자이상 100자 이하 입력" placeholder="4자이상 100자 이하 입력"
					value="${data.title}">
			</div>
			<div class="form-group">
				<label>내용:</label>
				<textarea name="boardContent" class="form-control" rows="5"
					placeholder="내용은 1자이상 700자 이하 입력" pattern=".{1,700}">${data.content}
				</textarea>
			</div>
			
<!-- 			<div class="form-group"> -->
<%-- 				<label>현재파일 : ${data.file_name}</label> --%>
<!-- 				<br> -->
<!-- 				<input type="file" name="file" id="file"/> -->
<!-- 			</div> -->

			<button type="submit" class="btn btn-default" onclick="updateF()">수정</button>
			<button type="reset" class="btn btn-default">새로입력</button>
			<button type="button" id="cancelBackBtn" class="btn btn-default">취소</button>
		</form>
	</div>

</body>
</html>