/**
 * read.jsp 관련 스크립트
 */

$(function(){
	
	let form = $("#actionForm");
	
	// List를 클릭하면 전체 리스트 보여주기
	$(".btn-info").click(function(){
		// actionForm에서 bno 제거
		form.find("input[name='bno']").remove();
		
		// actionForm action 수정 /board/list
		form.attr("action", "/board/list");
		
		// actionForm 전송
		form.submit();
	})
	
	// Modify를 클릭하면 actionForm 보내기
	// /board/modify + GET
	$(".btn-default").click(function(){
		form.attr("action", "/board/modify");
		form.submit();
	})
})