package com.oyster.free_board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.free_board.dao.FreeBoardDAOImpl;
import com.oyster.free_board.vo.FreeBoardVO;

@Service("freeBoardService")
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService  {
	
	@Autowired
	FreeBoardDAOImpl freeBoardDAO;
	
	@Override
	public List<FreeBoardVO> listArticles() throws Exception {
		
		return freeBoardDAO.selectAllArticlesList();
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FreeBoardVO viewArticle(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArticle(int articleNO) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
