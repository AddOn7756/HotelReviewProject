package model.hotel;

import java.util.List;

public interface HotelService {

	List<HotelSet> getHotelList(HotelVO vo);
	HotelSet getHotelData(HotelVO vo);
	boolean insert(HotelVO vo);
	boolean delete(HotelVO vo);
	boolean update(HotelVO vo);
	public Integer checkNum();
	
}
