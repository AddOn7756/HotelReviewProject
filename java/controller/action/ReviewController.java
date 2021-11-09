package controller.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.hotel.HotelVO;
import model.review.ReviewServiceImpl;
import model.review.ReviewVO;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@RequestMapping("/blogSingle.do")
	public String getReviewList(ReviewVO vo, Model model){
		List<ReviewVO> datas = new ArrayList<ReviewVO>();
		datas = reviewService.getReviewList(vo);
		System.out.println("리뷰 컨트롤러 겟리스트 : "+datas);
		model.addAttribute("datas", datas);
		return "blogSingle.jsp";
	}
	
	@RequestMapping(value="/insertReview.do", method=RequestMethod.POST)
	public String insertReview(ReviewVO vo) {
		int pageNum = vo.getHotelNum();
		if(reviewService.insert(vo)) {
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
		else {
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
	}
	
	@RequestMapping("/deleteReview.do")
	public String deleteReview(ReviewVO vo) {
		int pageNum = vo.getHotelNum();
		if(reviewService.delete(vo)) {
			System.out.println("댓글삭제 성공");
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
		else {
			System.out.println("댓글삭제 실패");
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
	}
	
	@RequestMapping("/updateReview.do")
	public String updateReview(ReviewVO vo) {
		int pageNum = vo.getHotelNum();
		if(reviewService.update(vo)) {
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
		else {
			return "redirect:hotelSingle.do?hotelNum="+pageNum;
		}
	}
	
}
