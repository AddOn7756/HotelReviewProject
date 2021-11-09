package model.images;

import org.springframework.web.multipart.MultipartFile;

public class ImageVO {

	private int imgNum;
	private int roomNum;
	private int hotelNum;
	private String imgId;
	private String oname;
	private MultipartFile fileUpload;
	
	public MultipartFile getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(MultipartFile fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getHotelNum() {
		return hotelNum;
	}
	public void setHotelNum(int hotelNum) {
		this.hotelNum = hotelNum;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	@Override
	public String toString() {
		return "ImageVO [imgNum=" + imgNum + ", roomNum=" + roomNum + ", hotelNum=" + hotelNum + ", imgId=" + imgId
				+ ", oname=" + oname + ", fileUpload=" + fileUpload + "]";
	}
	
	
	
}
