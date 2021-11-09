package model.images;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


class ImagesRowMapper implements RowMapper<ImageVO>{
	
	@Override
	public ImageVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ImageVO data = new ImageVO();

		data.setImgNum(rs.getInt("imgNum"));
		data.setHotelNum(rs.getInt("hotelnum"));
		data.setRoomNum(rs.getInt("roomnum"));
		data.setOname(rs.getString("oname"));
		data.setImgId(rs.getString("imgid"));

		return data;
	}
}

@Repository()
public class SpringImagesDAO {

	private static final String sql_SELECT_ALL="SELECT * FROM image ORDER BY imgnum ASC";
	private static final String sql_SELECT_ONE="SELECT * FROM image WHERE imagenum=?";
	private static final String sql_INSERT="INSERT INTO image(imgnum ,hotelnum, roomnum, oname, imgid) VALUES((SELECT NVL(MAX(imgnum), 0)+1 FROM image), ?,?,?,?)";
	private static final String sql_DELETE="DELETE FROM image WHERE imagenum=?";
	private static final String sql_UPDATE="UPDATE image SET oname=?, imageid=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 이미지 리스트
	public List<ImageVO> getImagesList(ImageVO vo){
		System.out.println("SpringHotelDAO getHotelList");
		Object[] args = { };
		return jdbcTemplate.query(sql_SELECT_ALL, args, new ImagesRowMapper());
	}
	// 이미지 한개
	public ImageVO getImagesData(ImageVO vo) {
		System.out.println("SpringHotelDAO getHotelData");
		Object[] args = { vo.getHotelNum() };
		return jdbcTemplate.queryForObject(sql_SELECT_ONE, args, new ImagesRowMapper());
	}
	// 이미지 입력
	public boolean insert(ImageVO vo) {
		System.out.println("SpringImagesDAO 통과");
		boolean flag = false;
		Object[] args= {vo.getHotelNum(), vo.getRoomNum(), vo.getOname(), vo.getImgId() };
		jdbcTemplate.update(sql_INSERT, args);
		flag=true;
		return flag;
	}
	// 이미지 삭제
	public boolean delete(ImageVO vo) {
		boolean flag = false;
		jdbcTemplate.update(sql_DELETE, vo.getHotelNum());
		flag=true;
		return flag;
	}
	// 이미지 수정
	public boolean update(ImageVO vo) {
		boolean flag = false;
		Object[] args= {vo.getOname(), vo.getImgId() };
		jdbcTemplate.update(sql_UPDATE, args);
		flag=true;
		return flag;
	}
	
}
