package model.hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hotelService")
public class HotelServiceImpl implements HotelService{

	@Autowired
	private SpringHotelDAO SpringHotelDAO;
	
	@Override
	public List<HotelSet> getHotelList(HotelVO vo) {
		return SpringHotelDAO.getHotelList(vo);
	}
	
	@Override
	public HotelSet getHotelData(HotelVO vo) {
		return SpringHotelDAO.getHotelData(vo);
	}
	
	@Override
	public boolean insert(HotelVO vo) {
		return SpringHotelDAO.insert(vo);
	}
	
	@Override
	public boolean delete(HotelVO vo) {
		return SpringHotelDAO.delete(vo);
	}
	
	@Override
	public boolean update(HotelVO vo) {
		return SpringHotelDAO.update(vo);
	}

	@Override
	public Integer checkNum() {
		return SpringHotelDAO.checkNum();
	}

	
}
