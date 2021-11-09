package model.hotel;

import java.util.List;

import model.images.ImageVO;

public class HotelSet {
	
	private HotelVO hotelVO;
	private List<ImageVO> imageList;
	
	public HotelVO getHotelVO() {
		return hotelVO;
	}
	public void setHotelVO(HotelVO hotelVO) {
		this.hotelVO = hotelVO;
	}
	public List<ImageVO> getImageList() {
		return imageList;
	}
	public void setImageList(List<ImageVO> imageList) {
		this.imageList = imageList;
	}
	@Override
	public String toString() {
		return "HotelSet [hotelVO=" + hotelVO + ", imageList=" + imageList + "]";
	}
	
}
