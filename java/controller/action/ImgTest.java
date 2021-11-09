package controller.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import model.images.ImageServiceImpl;
import model.images.ImageVO;

@Controller
public class ImgTest {

	@Autowired
	private ImageServiceImpl ImagesService;

	@RequestMapping(value="/uploadImages.do", method=RequestMethod.POST)
	public String upload(HttpServletRequest req, ImageVO vo) throws IllegalStateException, IOException {
		
		System.out.println("컨트롤러 통과");
		
		String realFolder = "C:\\JAVA_0622\\workspace\\HotelReview\\src\\main\\webapp\\images\\";
		File dir = new File(realFolder);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		MultipartFile file = vo.getFileUpload();
		
		// 넘어온 파일을 리스트로 저장
		/*List<MultipartFile> mf = mhsq.getFiles("uploadFile");*/
		
		System.out.println("imageController if문 직전");
		System.out.println("imageContoller VO : "+ vo);
		// System.out.println(mf.get(1).getOriginalFilename());

		if (file.isEmpty() || file.getOriginalFilename().equals("")) {
			System.out.println("imageController if문에 걸림");
			System.out.println("imageContoller VO : "+ vo);
			System.out.println(file.getOriginalFilename());
		}
		else {
			// 파일 중복명 처리
			String genId = UUID.randomUUID().toString();
			// 본래 파일명
			String originalfileName = file.getOriginalFilename();
			
			String saveFileName = genId + "." + FilenameUtils.getExtension(originalfileName);
			// 저장되는 파일 이름
			String savePath = realFolder + saveFileName; // 저장 될 파일 경로
			
			file.transferTo(new File(savePath)); // 파일 저장
			
			vo.setOname(originalfileName);
			vo.setImgId(saveFileName);
			
			System.out.println("imageContoller VO : "+ vo);
			
			ImagesService.insert(vo);
			System.out.println("imageController insert 통과 ");
		}
		
		/*if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
			System.out.println("imageController if문에 걸림");
			System.out.println("imageContoller VO : "+ vo);
			System.out.println(mf.get(1).getOriginalFilename());

		} 
		else {
			for (int i = 0; i < mf.size(); i++) {
				// 파일 중복명 처리
				String genId = UUID.randomUUID().toString();
				// 본래 파일명
				String originalfileName = mf.get(i).getOriginalFilename();

				String saveFileName = genId + "." + FilenameUtils.getExtension(originalfileName);
				// 저장되는 파일 이름
				String savePath = realFolder + saveFileName; // 저장 될 파일 경로

				mf.get(i).transferTo(new File(savePath)); // 파일 저장

				vo.setOname(originalfileName);
				vo.setImageId(saveFileName);

				System.out.println("imageContoller VO : "+ vo);

				ImagesService.insert(vo);
				System.out.println("imageController insert 통과 ");
			}
		}*/
		
		return "redirect:main.jsp";
	}

}
