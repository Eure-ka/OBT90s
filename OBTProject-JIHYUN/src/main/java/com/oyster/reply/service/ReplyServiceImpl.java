package com.oyster.reply.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.reply.dao.ReplyDAOImpl;
import com.oyster.reply.vo.ReplyVO;

@Service("replyService")
@Transactional
public class ReplyServiceImpl {

	@Autowired
	ReplyDAOImpl replyDAO;

	public int addNewArticle(Map<String, Object> articleMap) {
		// TODO Auto-generated method stub
		return replyDAO.insertNewArticle(articleMap);
	}

	public ReplyVO viewReply(int reply_no) {
		System.out.println("Service<<<<<view<<<<<< 들어왔  >>>>> " + reply_no);
		ReplyVO replyVO = replyDAO.selectReply(reply_no);
		return replyVO;
	}

	public void deleteReply(int reply_no) throws Exception{
		System.out.println("Service<<<<<removeReply<<<<<< 들어왔  >>>>> " + reply_no);
        replyDAO.deleteReply(reply_no);
	
	}
	
	
	
}
