/** 
 * fileupload
 * */

//$(function(){
//	$("#upload-file-form").submit(function(e) {
//	    e.preventDefault();
//	    var formData = new FormData(this);
//	})
//})

//$(document).ready(function() {
//	$("form#upload-file-form").submit(function() {
//
//		var formData = new FormData(this);
////		var formData = new FormData($("#upload-file-form")[0]);
//
//		$.ajax({
//			type : "POST",
//			url : "/insert.do",
//			data : formData,
//			contentType : false,
//			processData : false,
//			cache : false,
//			success : function(result) {
//				alert("성공");
//				location.href = "redirect:/select.do";
//			},
//			error : function() {
//				alert("오류");
//			}
//		})
//	})
//})

$(document).ready(function() {
	$("#uploadBtn").on("click", function() {

		var formData = new FormData($("#upload-file-form")[0]);

		$.ajax({
			type : "POST",
			url : "/insert.do",
			enctype: 'multipart/form-data',
			data : formData,
			dataType : "JSON",
			contentType : false,
			processData : false,
			cache : false,
			success : function(result) {
				alert("작성 완료");
				location.href = "/select.do";
			},
			error : function(){
				alert("오류");
			}
		})
	})
})