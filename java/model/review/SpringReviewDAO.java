package model.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class MemberRowMapper implements RowMapper<ReviewVO>{

	@Override
	public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ReviewVO data = new ReviewVO();
		
		data.setReNum(rs.getInt("renum"));
		data.setHotelNum(rs.getInt("hotelnum"));
		data.setMemNum(rs.getInt("memnum"));
		data.setContent(rs.getNString("content"));
		data.setRdate(rs.getDate("rdate"));
		data.setWriter(rs.getString("writer"));
		data.setImage(rs.getString("image"));
		
		return data;
	}
}

@Repository()
public class SpringReviewDAO {

	// 전체 리뷰
	private static final String sql_SELECT_ALL="SELECT * FROM review WHERE hotelnum=? ORDER BY rdate DESC";
	// 리뷰 한개 출력
	//private static final String sql_SELECT_ONE="SELECT * FROM review WHERE renum=?";
	// 리뷰 작성
	private static final String sql_INSERT="INSERT INTO review(renum, hotelnum, memnum, content, rdate, writer) VALUES((SELECT NVL(MAX(renum),0)+1 FROM review),?,?,?,SYSDATE,?)";
	// 리뷰 삭제
	private static final String sql_DELETE="DELETE FROM review WHERE renum=?";
	// 리뷰 수정
	private static final String sql_UPDATE="UPDATE review SET content=?, rdate=sysdate  WHERE renum=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 전체 리뷰
	public List<ReviewVO> getReviewList(ReviewVO vo){
		Object[] args= {vo.getHotelNum() };
		List<ReviewVO> datas = jdbcTemplate.query(sql_SELECT_ALL, args, new MemberRowMapper());
		System.out.println("리뷰DAO : "+datas);
		return datas;
	}
	
	// 리뷰 작성
	public boolean insert(ReviewVO vo) {
		boolean flag = false;
		Object[] args= {vo.getHotelNum(), vo.getMemNum(), vo.getContent(), vo.getWriter()};
		jdbcTemplate.update(sql_INSERT, args);
		flag=true;
		return flag;
	}
	// 리뷰 삭제
	public boolean delete(ReviewVO vo) {
		boolean flag = false;
		jdbcTemplate.update(sql_DELETE, vo.getReNum());
		flag=true;
		return flag;
	}
	// 리뷰 수정
	public boolean update(ReviewVO vo) {
		boolean flag = false;
		Object[] args= {vo.getContent(), vo.getReNum()};
		jdbcTemplate.update(sql_UPDATE, args);
		flag=true;
		return flag;
	}


}
