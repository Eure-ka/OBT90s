package com.oyster.free_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.free_board.dao.FreeBoardDAO;
import com.oyster.free_board.dao.FreeBoardDAOImpl;
import com.oyster.free_board.vo.FreeBoardVO;
import com.oyster.free_board.vo.ImageVO;
import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;


@Service("freeBoardService")
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	FreeBoardDAOImpl freeBoardDAO;
	@Autowired
	ReplyDAOImpl replyDAO;

	public List<FreeBoardVO> listArticles() throws Exception {
		List<FreeBoardVO> articlesList = freeBoardDAO.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int fb_number = freeBoardDAO.insertNewArticle(articleMap);
		articleMap.put("fb_number", fb_number);
		freeBoardDAO.insertNewImage(articleMap);

		return fb_number;
	}

	@Override
	public void removeArticle(int fb_number) throws Exception {
		freeBoardDAO.deleteArticle(fb_number);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map viewArticle(int fb_number) throws Exception {
		Map articleMap = new HashMap();
		FreeBoardVO freeboardvo = freeBoardDAO.selectArticle(fb_number);
		List<ImageVO> imageFileList = freeBoardDAO.selectImageFileList(fb_number);
		articleMap.put("article", freeboardvo);
		articleMap.put("imageFileList", imageFileList);
		freeBoardDAO.boardHit(fb_number);
		return articleMap;
	}


	@Override
	public void modArticle(Map articleMap) throws Exception {
		freeBoardDAO.updateArticle(articleMap);
	}
}
