package com.company.persistence;

import static com.company.persistence.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private DataSource ds;
	
	// 삽입
	public boolean insert(BoardDTO insertDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertFlag = false;
		
		try {
			con = ds.getConnection();
			String sql = "insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getTitle());
			pstmt.setString(2, insertDto.getContent());
			pstmt.setString(3, insertDto.getWriter());
			
			int result = pstmt.executeUpdate();
			if(result>0) {
				insertFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return insertFlag;
	}
	
	// 전체조회
	public List<BoardDTO> list() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			con = ds.getConnection();
			String sql = "select * from spring_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
	
	// 특정 게시물 조회
	public BoardDTO getRow(int bno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try {
			con = ds.getConnection();
			String sql = "select * from spring_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return dto;
	}
	
	// 게시물 업데이트
	public boolean update(BoardDTO updateDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateFlag = false;
		
		try {
			con = ds.getConnection();
			// title, content, updatedate
			String sql = "update spring_board set title = ?, content = ?, updatedate = sysdate where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getTitle());
			pstmt.setString(2, updateDto.getContent());
			pstmt.setInt(3, updateDto.getBno());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				updateFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return updateFlag;
	}
	
	// 게시물 삭제
	public boolean delete(int bno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean deleteFlag = false;
		
		try {
			con = ds.getConnection();
			String sql = "delete from spring_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			int result = pstmt.executeUpdate();
			if(result > 0 ) {
				deleteFlag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return deleteFlag;
	}
}
