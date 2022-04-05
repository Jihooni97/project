<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.fileupload.js"></script>

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
		<h1>게시판 글쓰기</h1>
		<form id="upload-file-form">
				<div class="form-group">
					<label>작성자:</label> <input name="boardName" type="text"
						class="form-control" required="required"
						pattern="[A-Za-z가-힣][0-9A-Za-z가-힣]{1,9}"
						placeholder="이름은 1자부터 10자까지"
						title="이름은 1자부터 10자까지 첫글자를 숫자가 올 수 없다.">
				</div>
				<div class="form-group">
					<label>제목:</label>
					<input name="boardTitle" type="text" class="form-control"
						pattern=".{1,100}" maxlength="100" required="required"
						title="1자이상 100자 이하 입력" placeholder="4자이상 100자 이하 입력">
				</div>
				<div class="form-group">
					<label>내용:</label>
					<textarea name="boardContent" class="form-control" rows="5"
						placeholder="내용은 1자이상 700자 이하 입력" pattern=".{1,700}"></textarea>
				</div>
				<br>
				<div class="form-group">
					<input type="file" name="file" id="file" />
				</div>
				
<!-- 				<button type="submit" class="btn btn-default">등록</button> -->
		</form>
		<button id="uploadBtn" class="btn btn-default">등록</button>
		<button type="reset" class="btn btn-default">새로입력</button>
		<button type="button" id="cancelBackBtn" class="btn btn-default">취소</button>
	</div>
</body>
</html>