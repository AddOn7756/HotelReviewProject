package model.images;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private SpringImagesDAO springImagesDAO;
	
	@Override
	public List<ImageVO> getImagesList(ImageVO vo) {
		return springImagesDAO.getImagesList(vo);
	}

	@Override
	public ImageVO getImagesData(ImageVO vo) {
		return springImagesDAO.getImagesData(vo);
	}

	@Override
	public boolean insert(ImageVO vo) {
		System.out.println("서비스임플리 통과");
		return springImagesDAO.insert(vo);
	}

	@Override
	public boolean delete(ImageVO vo) {
		return springImagesDAO.delete(vo);
	}

	@Override
	public boolean update(ImageVO vo) {
		return springImagesDAO.update(vo);
	}
	
}
