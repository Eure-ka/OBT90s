package com.oyster.trans_board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.free_board.vo.FreeBoardVO;
import com.oyster.trans_board.dao.TransBoardDAOImpl;
import com.oyster.trans_board.vo.TransBoardVO;

@Service("transBoardService")
@Transactional
public class TransBoardServiceImpl implements TransBoardService {

	@Autowired
	TransBoardDAOImpl transBoardDAO;
	
	@Override
	public List<TransBoardVO> listArticles() throws Exception {
		System.out.println("Service<<<<<<<<<<< pass");
		return transBoardDAO.selectAllArticlesList();
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		System.out.println("글쓰기 service>>>>>>>>>>>>>>>" + articleMap);
		return transBoardDAO.insertNewArticle(articleMap);
	}

	@Override
	public TransBoardVO viewArticle(int tb_number) throws Exception {
		System.out.println("Service<<<<<view<<<<<< 들어왔  >>>>> " + tb_number);
		TransBoardVO transBoardVO =transBoardDAO.selectArticle(tb_number);
		return transBoardVO;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		System.out.println("Service<<<<mod<<<<< 들어왔  >>>>> " + articleMap);
		transBoardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int tb_number) throws Exception {
		System.out.println("Service<<<<removes<<<<< 들어왔  >>>>> " + tb_number);
		transBoardDAO.deleteArticle(tb_number);
	}
}