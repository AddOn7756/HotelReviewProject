package model.images;

import java.util.List;

public interface ImageService {

	List<ImageVO> getImagesList(ImageVO vo);
	ImageVO getImagesData(ImageVO vo);
	boolean insert(ImageVO vo);
	boolean delete(ImageVO vo);
	boolean update(ImageVO vo);
	
}
