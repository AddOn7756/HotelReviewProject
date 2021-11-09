package model.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	SpringRoomDAO springRoomDAO;

	@Override
	public List<RoomVO> getRoomList() {
		return springRoomDAO.getRoomList();
	}

	@Override
	public RoomVO getRoomData(RoomVO vo) {
		return springRoomDAO.getRoomData(vo);
	}

	@Override
	public boolean insert(RoomVO vo) {
		return springRoomDAO.insert(vo);
	}

	@Override
	public boolean delete(RoomVO vo) {
		return springRoomDAO.delete(vo);
	}

	@Override
	public boolean update(RoomVO vo) {
		return springRoomDAO.update(vo);
	}
	
	
	
	
}
