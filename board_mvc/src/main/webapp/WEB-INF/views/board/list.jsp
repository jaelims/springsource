<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><a href="/board/list?pageNum=1&amount=10&type=&keyword=">Board List</a></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type='button' class='btn btn-xs pull-right' onclick="location.href='/board/register'">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번 호</th>
                                        <th>제 목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>									
                                </thead>
								<!-- 게시판 리스트 반복문 -->
                                <tbody>
									<c:forEach var="dto" items="${list}">
										<tr>
											<td>${dto.bno}</th>
											<td><a href="${dto.bno}" class="move">${dto.title}</a><strong> [${dto.replycnt}]</strong></td>
											<td>${dto.writer}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.regdate}"/></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.updatedate}"/></td>
										</tr>
									</c:forEach>
								</tbody>
                            </table>
							<div class="row"> <!-- start search -->
                            	<div class="col-md-12">
                            	  <div class="col-md-8"><!--search Form-->
                            		<form action="" method="get" id="searchForm">
                            			<input type="hidden" name="pageNum" value="${pageDto.cri.pageNum}"/>
                            			<input type="hidden" name="amount" value="${pageDto.cri.amount}"/>
                            			<select name="type" id="">
                            				<option value="">---------------</option>
                            				<option value="T" <c:out value="${pageDto.cri.type == 'T' ? 'selected':''}"/>>제목</option>
                            				<option value="C" <c:out value="${pageDto.cri.type == 'C' ? 'selected':''}"/>>내용</option>
                            				<option value="W" <c:out value="${pageDto.cri.type == 'W' ? 'selected':''}"/>>작성자</option>
                            				<option value="TC" <c:out value="${pageDto.cri.type == 'TC' ? 'selected':''}"/>>제목 or 내용</option>
                            				<option value="TW" <c:out value="${pageDto.cri.type == 'TW' ? 'selected':''}"/>>제목 or 작성자</option>
                            				<option value="TCW" <c:out value="${pageDto.cri.type == 'TCW' ? 'selected':''}"/>>제목 or 내용 or 작성자</option>
                            			</select>
                            			<input type="text" name="keyword" id="" value='<c:out value="${pageDto.cri.keyword}"/>'/>
                            			<button class="btn btn-default">Search</button>
                            		</form>
                            	   </div>
                            	   <div class="col-md-2 col-md-offset-2">
                            	   	<!--페이지 목록 갯수 지정하는 폼-->
                            	   	<select name="" id="amount" class="form-control">
                            	   		<option value="10" <c:out value="${pageDto.cri.amount == 10 ? 'selected':''}"/>>10</option>
                            	   		<option value="20" <c:out value="${pageDto.cri.amount == 20 ? 'selected':''}"/>>20</option>
                            	   		<option value="30" <c:out value="${pageDto.cri.amount == 30 ? 'selected':''}"/>>30</option>
                            	   		<option value="40" <c:out value="${pageDto.cri.amount == 40 ? 'selected':''}"/>>40</option>
                            	   	</select>
								  </div>
                             	 </div>                             	 
                      		 </div><!-- end search -->
                            <!-- start Pagination -->
                            <div class="text-center">
                            	<ul class="pagination">
                            		<c:if test="${pageDto.prev}">
                            			<li class="paginate_button previous">
                            				<a href="${pageDto.startPage-10}">Previous</a>
                            			</li>
                            		</c:if>
                            		<c:forEach var="idx" begin="${pageDto.startPage}" end="${pageDto.endPage}">
                            			<li class="paginate_button ${pageDto.cri.pageNum==idx?'active':''}">
                            				<a href="${idx}">${idx}</a>
                            			</li>
                            		</c:forEach>
                            		<c:if test="${pageDto.next}">
	                            		<li class="paginate_button next">
	                            			<a href="${pageDto.endPage+1}">Next</a>
	                            		</li>
	                            	</c:if>
                            	</ul>
                            </div>
                            <!-- end Pagination -->   
                            </div>
                            <!-- end panel-body -->
                        </div>
                        <!-- end panel -->
                    </div>                   
                </div>               
            <!-- /.row -->
<!-- 페이지 나누기를 위한 폼 -->
<form action="" method="get" id="actionForm">
	<input type="hidden" name="pageNum" value="${pageDto.cri.pageNum}"/>
	<input type="hidden" name="amount" value="${pageDto.cri.amount}"/>
	<input type="hidden" name="type" value="${pageDto.cri.type}"/>
	<input type="hidden" name="keyword" value="${pageDto.cri.keyword}"/>
	<input type="hidden" name="bno" value=""/>
</form>

<!-- 모달 추가 -->
<div class="modal" tabindex="-1" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>처리가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
<!-- 스크립트 -->
<script>
let result = '${result}';
</script>
<script src="/resources/js/list.js"></script>
<%@include file="../includes/footer.jsp" %>       