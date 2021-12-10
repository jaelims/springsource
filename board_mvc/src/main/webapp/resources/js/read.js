/**
 * read.jsp 관련 스크립트
 */

$(function(){
	
	// 댓글 전체 가져오기 함수 호출
	showList(1);
	// 댓글 보여줄 영역 가져오기
	let replyUl = $(".chat");
	// 댓글 페이지 나누기 영역 가져오기
	let replyPageFooter = $(".panel-footer");
	let pageNum = 1;
	
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
	
	// 댓글 작업
	// 댓글 모달 창 영역 가져오기
	let modal = $('#replyModal');
	
	// 모달 창 영역 안의 요소 가져오기
	let modalReply = modal.find("input[name='reply']");
	let modalReplyer = modal.find("input[name='replyer']");
	let modalReplyDate = modal.find("input[name='replyDate']");
	
	let modalRegisterBtn = modal.find("#modalRegisterBtn");
	let modalModifyBtn = modal.find("#modalModifyBtn");
	let modalRemoveBtn = modal.find("#modalRemoveBtn");
	
	// 댓글 삽입
	$("#addReplyBtn").click(function(){
		
		// input 안에 들어있는 value 제거
		modal.find("input").val("");
		
		// 댓글 등록일 요소 숨기기
		modalReplyDate.closest("div").hide();
		
		// 종료 버튼만 제외하고 모든 버튼 숨기기
		modal.find("button[id != 'modalCloseBtn']").hide();
		// 등록 버튼 보여주기
		modalRegisterBtn.show();
		
		// 댓글 모달창 보여주기
		modal.modal('show');
	}) // addReplyBtn end
	
	modalRegisterBtn.click(function(){
		
		var reply = {
			bno:bno,
			replyer:modalReplyer.val(),
			reply:modalReply.val()
		};
		
		replyService.add(reply,
			function(result){
				if(result) {
					// alert(result);
					if(result == 'success') {
						alert("댓글 등록 성공");
					}
					modal.find("input").val("");
					modal.modal("hide");
					showList(-1);
				}
		}); // add end
	})
	
	modalRemoveBtn.click(function(){
		
		// 댓글 삭제
		replyService.remove(modal.data("rno"),
			function(result){
				//alert(result);
				if(result == "success") {
					alert("댓글 삭제 성공");
				}
				modal.modal("hide");
				showList(pageNum);
			},
			function(msg){
				alert(msg);
			}
		); // remove end
	})
	
	modalModifyBtn.click(function(){
		
		var reply = {
			rno:modal.data("rno"),
			reply:modalReply.val()
		};
		
		replyService.update(reply,
			function(data){
				// alert(data);
				if(data == "success") {
					alert("수정성공");
				}
				modal.modal("hide");
				showList(pageNum);
			},
			function(msg){
				alert(msg);
			}
		); // update end
	})
	
	// 댓글이 반복되는 코드는 실제로 존재하는 것이 아닌 나중에 동적으로 생성되기 때문에
	// 있는 요소에 이벤트를 걸고 나중에 위임하는 형태로 작성
	replyUl.on("click", "li", function(){
		
		var rno = $(this).data("rno");
		
		console.log("rno : " + rno);
		
		replyService.get(rno, function(data){
			console.log(data);
			
			// 도착한 데이터를 모달창에 보여주기
			modalReply.val(data.reply);
			modalReplyer.val(data.replyer);
			modalReplyDate.val(replyService.displayTime(data.replyDate))
						  .attr("readonly","readonly");
			
			// 수정/삭제를 위한 기본키
			modal.data("rno", data.rno);
			
			// 작성날짜 영역 보여주기
			modal.find("[name='replyDate']").closest("div").show();
			
			// 모든 버튼 보여주기
			modal.find("button").show();
			
			// 등록버튼 숨기기
			modal.find("#modalRegisterBtn").hide();
			
			modal.modal("show");
			
		}); // get end
	})
	
	// 댓글 전체 가져오기
	function showList(page){
		replyService.getList({bno:bno, page:page || 1}, function(total, data){
			console.log("댓글 전체 개수 : " + total)
			console.log(data);
			
			// 새 댓글을 작성한 경우 마지막 페이지를 띄우기 위해 작성
			if(page == -1) {
				pageNum = Math.ceil(total / 10.0);
				showList(pageNum);
				return;
			}
			
			// 댓글이 없는 경우
			if(data == null || data.length == 0) {
				replyUl.html("");
				return;
			}
			// 댓글이 있는 경우
			let str = "";
			for(var i=0, len = data.length || 0; i<len; i++) {
				str += "<li class='left clearfix' data-rno='" + data[i].rno + "'>";
				str += "<div class='header'>";
				str += "<strong class='primary-font'>" + data[i].replyer + "</strong>";
				str += "<small class='pull-right text-muted'>" + replyService.displayTime(data[i].replyDate) + "</small>";
				str += "<p>" + data[i].reply + "</p>";
				str += "</div></div></li>";
			}
			replyUl.html(str);
			showReplyPage(total);
		}); // getList end
	} // showList end
	
	function showReplyPage(total) {
		// 마지막 페이지 계산
		let endPage = Math.ceil(pageNum / 10.0) * 10;
		// 시작 페이지 계산
		let startPage = endPage - 9;
		// 이전 버튼
		let prev = startPage != 1;
		// 다음 버튼
		var next = false;
		
		if(endPage * 10 >= total) {
			endPage = Math.ceil(total / 10.0);
		}
		if(endPage * 10 < total) {
			next = true;
		}
		
		let str = "<ul class='pagination pull-right'>";
		if(prev) {
			str += "<li class='paginate_button'>";
			str += "<a href='" + (startPage - 1) + "'>Previous</a></li>";
		}
		
		for(var i = startPage; i <= endPage; i++) {
			
			var active = pageNum == i ? "active" : "";
			
			str += "<li class='paginate_button " + active + "'>";
			str += "<a href='" + i + "'>"+ i + "</a></li>";
		}
		
		if(next) {
			str += "<li class='paginate_button'>";
			str += "<a href='" + (endPage + 1) + "'>Next</a></li>";
		}
		
		str += "</ul>";
		replyPageFooter.html(str);
	} // showReplyPage end
	
	// 댓글 페이지 번호 클릭시
	// 이벤트 위임 방식
	replyPageFooter.on("click", "li a", function(e){
		e.preventDefault(); // a태그 속성 중지
		
		pageNum = $(this).attr('href');
		showList(pageNum);
	})

	// 첨부파일 가져오기
	
	// 첨부파일 보여줄 영역 가져오기
	let uploadResult = $(".uploadResult ul");
	let str = "";
	
	// 게시물에 달려있는 전체 첨부파일 가져오기
	$.getJSON({
		url:'getAttachList', // /board/getAttachList
		data:{
			bno:bno
		},
		success:function(data) {
			console.log(data);
			
			// 도착한 첨부파일을 보여주기
			$(data).each(function(idx, obj){
				if(obj.fileType){
					
					// 썸네일 이미지 경로 생성
					var fileCallPath = encodeURIComponent(obj.uploadPath + "\\s_" + obj.uuid + "_" + obj.fileName);
					
					str += "<li data-path = '" + obj.uploadPath + "' data-uuid = '" + obj.uuid + "'";
					str += " data-filename = '" + obj.fileName + "' data-type = '" + obj.fileType + "'>";
					str += "<a><img src = '/display?fileName=" + fileCallPath + "'>";
					str += "<div>" + obj.fileName + "</a>";
					str += "</div></li>";
				} else {
					
					str += "<li data-path = '" + obj.uploadPath + "' data-uuid = '" + obj.uuid + "'";
					str += " data-filename = '" + obj.fileName + "' data-type = '" + obj.fileType + "'>";
					str += "<a><img src = '/resources/img/attach.png'><div>" + obj.fileName + "</a>";
					str += "</div></li>";
				}
			}) // $(data).each end
			uploadResult.html(str);
		}
	}) // 전체 첨부파일 가져오기 종료

	// 첨부파일 클릭시 이벤트
	$(".uploadResult").on("click", "li", function(){
		console.log("첨부파일 클릭");
		
		// 선택된 첨부 파일 가져오기
		let liObj = $(this);
		
		let path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_" + liObj.data("filename"));
		
		if(liObj.data("type")) {
			showImage(path.replace(new RegExp(/\\/g), "/"));
		} else {
			self.location = "/download?fileName=" + path;
		}
	}) // $(".uploadResult").on("click") end
	
	// 원본 이미지 창 닫기
	$(".bigPictureWrapper").on("click", function(){
		$(".bigPicture").animate({
						width: '0%',
						height: '0%'
						}, 1000);
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

function showImage(fileCallPath) {
	console.log(fileCallPath);
	
	// 안보였던 영역 보이기
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture").html("<img src = '/display?fileName=" + fileCallPath + "'>")
					.animate({
						width: '100%',
						height: '100%'
					}, 1000);
}