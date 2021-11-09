package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import model.hotel.HotelServiceImpl;
import model.hotel.HotelSet;
import model.hotel.HotelVO;
import model.images.ImageServiceImpl;
import model.images.ImageVO;
import model.review.ReviewServiceImpl;
import model.review.ReviewVO;

@Controller
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelService;
	@Autowired
	private ImageServiceImpl imageService;
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@RequestMapping(value="/insertHotel.do", method=RequestMethod.POST)
	public String insertHotel(HotelVO hvo, ImageVO ivo) throws IllegalStateException, IOException {
		
		if(hotelService.insert(hvo)) {
			// System.out.println("컨트롤러 통과");
			// System.out.println("hvo : "+hvo+", ivo : "+ivo);
			
			String realFolder = "C:\\JAVA_0622\\workspace\\HotelReview\\src\\main\\webapp\\images\\";
			File dir = new File(realFolder);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}		
			
			// 방금 입력된 호텔의 PK 값
			int lastNum = hotelService.checkNum();
			MultipartFile file = ivo.getFileUpload();
			
			if (file.isEmpty() || file.getOriginalFilename().equals("")) {
				System.out.println(file.getOriginalFilename());
				
				ivo.setHotelNum(lastNum);
				ivo.setRoomNum(0);
				ivo.setImgId("defaultImg.png");
				ivo.setOname("defaultImg.png");
				
				imageService.insert(ivo);
			}
			else {
				// 파일 중복명 처리
				String genId = UUID.randomUUID().toString();
				// 본래 파일명
				String originalfileName = file.getOriginalFilename();
				// 저장되는 파일 이름
				String saveFileName = genId + "." + FilenameUtils.getExtension(originalfileName);
				// 저장 될 파일 경로
				String savePath = realFolder + saveFileName; 
				// 파일 저장
				file.transferTo(new File(savePath)); 
				
				ivo.setHotelNum(lastNum);
				ivo.setRoomNum(0);
				ivo.setImgId(saveFileName);
				ivo.setOname(originalfileName);
				
				System.out.println("imageContoller VO : "+ ivo);
				
				imageService.insert(ivo);
				System.out.println("imageController insert 통과 ");
				
			}
			return "redirect:getHotelList.do";
		}
		else {
			return "redirect:getHotelList.do";
		}
	}
	
	@RequestMapping("/getHotelList.do")
	public String getHotelList(HotelVO hvo, ImageVO ivo, Model model) {
		List<HotelSet> hotelList = hotelService.getHotelList(hvo);
		model.addAttribute("hotelList", hotelList);
		System.out.println("hotelcontroller에서 출력 : "+hotelList);
		
		return "hotel.jsp";
	}
	
	@RequestMapping("/main.do")
	public String main(HotelVO hvo, ImageVO ivo, Model model) {
		List<HotelSet> hotelList = hotelService.getHotelList(hvo);
		model.addAttribute("hotelList", hotelList);
		System.out.println("hotelcontroller에서 출력 : "+hotelList);
		return "main.jsp";
	}
	
	@RequestMapping("/hotelSingle.do")
	public String getHotelData(HotelVO hvo, ReviewVO rvo, Model model) {
		HotelSet hotel = hotelService.getHotelData(hvo);
		List<ReviewVO> review = reviewService.getReviewList(rvo);
		model.addAttribute("hotelData", hotel);
		model.addAttribute("reviewData", review);
		System.out.println("hotelController getHotelDate : "+hotel);
		return "hotelSingle.jsp";
	}
	
	@RequestMapping("/deleteHotel.do")
	public String deleteHotel(HotelVO vo) {
		hotelService.delete(vo);
		return "getHotelList.do";
	}
	
	@RequestMapping(value="/editHotel.do", method=RequestMethod.POST)
	public String editHotelGo(HotelVO vo) {
		hotelService.update(vo);
		return "redirect:getHotelList.do";
	}
	
	@RequestMapping(value="/editHotel.do", method=RequestMethod.GET)
	public String editHotel(HotelVO vo, Model model) {
		HotelSet data = hotelService.getHotelData(vo);
		model.addAttribute("hotelData", data);
		return "editHotel.jsp";
	}
	
}
