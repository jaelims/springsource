create table spring_board(
	bno number(10,0),				 -- 글번호
	title varchar2(200) not null,	 -- 제목
	content varchar2(2000) not null, -- 내용
	writer varchar2(50) not null,	 -- 작성자
	regdate date default sysdate,	 -- 작성날짜
	updatedate date default sysdate	 -- 수정날짜
);


create sequence seq_board;

alter table spring_board add constraint pk_spring_board primary key(bno);

-- 더미 데이터 삽입
insert into spring_board(bno, title, content, writer)
(select seq_board.nextval, title, content, writer from SPRING_BOARD);

select count(*) from spring_board;

select * from SPRING_BOARD;

-- 페이지 나누기
-- rownum
select rownum, bno, title from spring_board;

-- rownum 부여시 주의할 점 => order by 절과 같이 올 때(order by 구문에 index 값이 쓰이지 않는 경우)
-- ex) order by re-ref desc, re-lev asc;
-- ex) index(pk 만들면 index로 생성됨)

-- 서브쿼리 이용하기
select rownum, bno, title
from (select bno, title from spring_board where bno > 0 order by bno desc)
where rownum <= 10;

-- 오라클 힌트
select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
from SPRING_BOARD
where rownum <=10;
 
-- 1 : 최신글 10개 가지고 나오기
select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
	  from SPRING_BOARD
	  where rownum <= 10)
where rn > 0;

-- 2 : 그 다음 최신글 10개 가지고 나오기
select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
	  from SPRING_BOARD
	  where rownum <= 20)
where rn > 10;

-- 검색
-- 제목/내용/작성자 단일항목 검색
select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
	  from SPRING_BOARD
	  where title like '%Test%' and rownum <= 20)
where rn > 10;

-- 제목 or 내용 / 제목 or 작성자 / 제목 or 내용 or 작성자 다중항목 검색
select rn, bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/ rownum rn, bno, title
	  from SPRING_BOARD
	  where (title like '%게시판%' or content like '%게시판%') and rownum <= 20)
where rn > 10;

-- Mybatis 동적 태그


