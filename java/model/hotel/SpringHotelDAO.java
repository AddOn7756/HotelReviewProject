package model.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.images.ImageVO;

class HotelRowMapper implements RowMapper<HotelVO>{

	@Override
	public HotelVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		HotelVO data = new HotelVO();

		data.setHotelNum(rs.getInt("hotelnum"));
		data.setName(rs.getString("name"));
		data.setPnum(rs.getString("pnum"));
		data.setAddr(rs.getString("addr"));
		data.setExpln(rs.getString("expln"));
		data.setRating(rs.getInt("rating"));
		data.setRecnt(rs.getInt("recnt"));
		data.setLttLng(rs.getString("lttlng"));

		return data;
	}
}

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
public class SpringHotelDAO {

	// 호텔
	private static final String sql_SELECT_ALL="SELECT * FROM hotel WHERE name LIKE '%'||?||'%' AND addr LIKE '%'||?||'%' ORDER BY hotelnum DESC";
	private static final String sql_SELECT_ONE="SELECT * FROM hotel WHERE hotelnum=?";
	private static final String sql_INSERT="INSERT INTO hotel(hotelnum, name, pnum, addr, expln, lttlng) values((SELECT NVL(MAX(hotelnum), 0)+1 FROM hotel), ?,?,?,?,?)";
	private static final String sql_DELETE="DELETE FROM hotel WHERE hotelnum=?";
	private static final String sql_UPDATE="UPDATE hotel SET name=?, pnum=?, addr=?, expln=? WHERE hotelnum=?";
	private static final String sql_CHECK_NUM="SELECT NVL(MAX(hotelnum),0) AS hotelnum FROM hotel";

	// 이미지
	private static final String sql_SELECT_ALL_IMG="SELECT * FROM image WHERE roomnum=0 ORDER BY imgnum DESC";
	private static final String sql_SELECT_ONE_IMG="SELECT * FROM image WHERE hotelnum=? ORDER BY imgnum DESC";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 마지막 호텔 pk 값
	public int checkNum(){
		int lastNum = (int)jdbcTemplate.queryForObject(sql_CHECK_NUM, Integer.class);
		return lastNum;
	}
	
	// 호텔 리스트
	public List<HotelSet> getHotelList(HotelVO vo){
		System.out.println("SpringHotelDAO getHotelList");
		List<HotelSet> hotelSetList = new ArrayList<HotelSet>();
		Object[] hargs = {vo.getName(), vo.getAddr() };
		// Object[] iargs = {};
		List<ImageVO> imageList = jdbcTemplate.query(sql_SELECT_ALL_IMG, new ImagesRowMapper());
		List<HotelVO> hotelList = jdbcTemplate.query(sql_SELECT_ALL, hargs, new HotelRowMapper());

		for(HotelVO hvo : hotelList) {
			HotelSet hs = new HotelSet();
			hs.setHotelVO(hvo);
			List<ImageVO> images = new ArrayList<ImageVO>();
			for(ImageVO ivo : imageList) {
				System.out.println("이중 포문 : "+hvo.getHotelNum()+", "+ivo.getHotelNum());
				if(hvo.getHotelNum()==ivo.getHotelNum()) {
					images.add(ivo);
				}
			}
			System.out.println("hoteolDAO for문 안에 : "+hvo);
			hs.setImageList(images);
			hotelSetList.add(hs);
			// System.out.println("DAO에서 get(0)번째 : "+hs.getImageList().get(0));
		}
		System.out.println("hotelDao 에서 출력 :"+hotelSetList);
		return hotelSetList;
	}
	
	// 호텔 한개
	public HotelSet getHotelData(HotelVO hvo) {
		System.out.println("SpringHotelDAO getHotelData");
		System.out.println(hvo);

		HotelSet hs = new HotelSet();
		Object[] hargs = {hvo.getHotelNum() };
		Object[] iargs = {hvo.getHotelNum() };


		hvo = jdbcTemplate.queryForObject(sql_SELECT_ONE, hargs, new HotelRowMapper());
		List<ImageVO> imageList = jdbcTemplate.query(sql_SELECT_ONE_IMG, iargs, new ImagesRowMapper());
		
		hs.setHotelVO(hvo);
		hs.setImageList(imageList);
		System.out.println("HotelDao getHotelDate : "+hs);
		
		System.out.println("HotelDao getHotelDate : "+hs);
		return hs;
	}
	
	// 호텔 입력
	public boolean insert(HotelVO vo) {
		boolean flag = false;
		Object[] args= {vo.getName(), vo.getPnum(), vo.getAddr(), vo.getExpln(), vo.getLttLng() };
		jdbcTemplate.update(sql_INSERT, args);
		flag=true;
		return flag;
	}
	
	// 호텔 삭제
	public boolean delete(HotelVO vo) {
		boolean flag = false;
		jdbcTemplate.update(sql_DELETE, vo.getHotelNum());
		flag=true;
		return flag;
	}
	
	// 호텔 수정
	public boolean update(HotelVO vo) {
		boolean flag = false;
		Object[] args= {vo.getName(), vo.getPnum(), vo.getAddr(), vo.getExpln(), vo.getHotelNum() };
		jdbcTemplate.update(sql_UPDATE, args);
		flag=true;
		return flag;
	}
}
