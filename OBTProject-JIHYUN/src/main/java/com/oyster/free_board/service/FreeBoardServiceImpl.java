package com.oyster.free_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.free_board.dao.FreeBoardDAOImpl;
import com.oyster.free_board.vo.FreeBoardVO;
import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;

@Service("freeBoardService")
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	FreeBoardDAOImpl freeBoardDAO;
	@Autowired
	ReplyDAOImpl replyDAO;

	@Override
	public List<FreeBoardVO> listArticles() throws Exception {
		System.out.println("Service<<<<<<<<<<< pass");
		return freeBoardDAO.selectAllArticlesList();
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		System.out.println("글쓰기 service>>>>>>>>>>>>>>>" + articleMap);
		return freeBoardDAO.insertNewArticle(articleMap);
	}

	@Override
	public Map<String, Object> viewArticle(int fb_number) throws Exception {
		System.out.println("Service<<<<<view<<<<<< 들어왔  >>>>> " + fb_number);
		FreeBoardVO freeBoardVO = freeBoardDAO.selectArticle(fb_number);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("freeBoard", freeBoardVO);
		String boardKind = "fb";
		List<ReplyVO> replList = replyDAO.selectAllReplyList(fb_number, boardKind);
		resultMap.put("replList", replList);
		return resultMap;
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {
		System.out.println("Service<<<<mod<<<<< 들어왔  >>>>> " + articleMap);
		freeBoardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int fb_number) throws Exception {
		System.out.println("Service<<<<removes<<<<< 들어왔  >>>>> " + fb_number);
		freeBoardDAO.deleteArticle(fb_number);
	}
}
