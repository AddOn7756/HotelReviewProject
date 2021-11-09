package model.room;

import java.util.List;

public interface RoomService {

	List<RoomVO> getRoomList();
	RoomVO getRoomData(RoomVO vo);
	boolean insert(RoomVO vo);
	boolean delete(RoomVO vo);
	boolean update(RoomVO vo);
	
	
}
