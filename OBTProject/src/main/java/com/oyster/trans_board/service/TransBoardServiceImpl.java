package com.oyster.trans_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.trans_board.dao.TransBoardDAOImpl;
import com.oyster.trans_board.vo.ImageVO;
import com.oyster.trans_board.vo.TransBoardVO;

@Service("transBoardService")
@Transactional
public class TransBoardServiceImpl implements TransBoardService {

	@Autowired
	TransBoardDAOImpl transBoardDAO;
	@Autowired
	ReplyDAOImpl replyDAO;

	public List<TransBoardVO> listArticles() throws Exception {
		List<TransBoardVO> articlesList = transBoardDAO.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int tb_number = transBoardDAO.insertNewArticle(articleMap);
		articleMap.put("tb_number", tb_number);
		transBoardDAO.insertNewImage(articleMap);

		return tb_number;
	}

	@Override
	public void removeArticle(int tb_number) throws Exception {
		transBoardDAO.deleteArticle(tb_number);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map viewArticle(int tb_number) throws Exception {
		Map articleMap = new HashMap();
		TransBoardVO freeboardvo = transBoardDAO.selectArticle(tb_number);
		List<ImageVO> imageFileList = transBoardDAO.selectImageFileList(tb_number);
		articleMap.put("article", freeboardvo);
		articleMap.put("imageFileList", imageFileList);
		transBoardDAO.boardHit(tb_number);
		return articleMap;
	}


	@Override
	public void modArticle(Map articleMap) throws Exception {
		transBoardDAO.updateArticle(articleMap);
	}
	
}