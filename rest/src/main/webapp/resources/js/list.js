/**
 * list.jsp 스크립트
 */
$(function(){
	
	$(".move").click(function(e){
		// a 태그 속성 중지
		e.preventDefault();
		
		// 제목 클릭시 href 값 가져오기
		let code = $(this).attr("href");
		
		// $.ajax(기본), $.getJSON()
		$.getJSON({
			url:code,
			success:function(data){
				// console.log(data);
				
				let str = '<ul>';
				str += "<li>code : "+data.code+"</li>";
				str += "<li>title : "+data.title+"</li>";
				str += "<li>writer : "+data.writer+"</li>";
				str += "<li>price : "+data.price+"</li>";
				str += "</ul>";
				
				$("#result").html(str);
			}
		})
	})
})

