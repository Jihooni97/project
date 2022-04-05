$(function(){
//	var a  = $(location).attr('href');
//	console.log(a);
//	var b = a.split("?")
//	console.log(b);
//	var c = b[1].split('=')
//	console.log(c);
	
	if($(location).attr('href').split("?")[1].split('=')[1] != undefined){
		boardList($(location).attr('href').split("?")[1].split('=')[1]);
	}else{
		boardList(1);
	}
})

//function getSearch(){
//	$.ajax({
//		url : "/test_select.do",
//		data : 
//	})
//}


function boardList(page){
//	if(page==null){
//		page = "<button onclick='boardList("+(page-1)+")'> < </button>"
//	}
	var data = {"nowPage" : page, "type" : $("[name=type]").val(), "keyword" : $("input[name=keyword]").val() };
	
	 $.ajax({
		 url : "/test_select.do" ,
		 type: "GET",
		 data : data,
		 dataType : "JSON",
		 cache : false,
		 success : function(result){
			 
			 $("#table").empty();
			 $("#pageBtn").empty();
			 
			 var totalPage = Math.ceil(result.totalCount/5.0);
			 var pageBlock = Math.ceil(totalPage / 5.0); // 페이지 5개씩 나누는 페이지 블럭
			 var nowPageBlock = Math.ceil(page / 5.0); //현재 페이지 블럭 위치 (1블럭이면 1~5페이지까지 2블럭 6~10)
			 var endPage = nowPageBlock * 5; // 현재 페이지블럭의 끝 페이지번호 구하기
			 var startPage = endPage - 4; // 시작 페이지번호 구하기
			 
			 if(totalPage<endPage){ //마지막 페이지가 총 페이지개수보다 많을 때 마지막 페이지번호를 끝페이지번호로 대입
			 	endPage = totalPage;
			 }
			 
			 for(var i = 0 ; i < result.list.length ; i++){
				 var html = '<tr onclick="location.href=\'/detailPage.do?no='+result.list[i].idx+'&nowPage='+page+'\'" >'
				 html += "<td>"+result.list[i].idx+"</td>";
				 html += "<td>"+result.list[i].name+"</td>";
				 html += "<td>"+result.list[i].title+"</td>";				 				 
				 html += "<td>"+result.list[i].content+"</td>";
				 html += "<td>"+nval(result.list[i].file_real_name , '')+"</td>";
				 html += "</tr>";
				 $("#table").append(html);
			 }
			 if(page>1){
				 var prevBtn = "<button onclick='boardList("+(page-1)+")'> < </button>"
				 $("#pageBtn").append(prevBtn);
			 }
			 for(var i = startPage ; i <= endPage ; i++){
				 var pageBtn = "<button onclick='boardList("+i+")'>"+i+"</button>"
				 $("#pageBtn").append(pageBtn);
			 }
			 if (page != totalPage){
				 var nextBtn = "<button onclick='boardList("+(page+1)+")'> > </button>"
				 $("#pageBtn").append(nextBtn);
			 }
		 }
	 })
}