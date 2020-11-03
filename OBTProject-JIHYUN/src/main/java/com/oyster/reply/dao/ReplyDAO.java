package com.oyster.reply.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.oyster.reply.vo.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> selectAllReplyList() throws DataAccessException;
	public int insertNewReply(Map articleMap) throws DataAccessException;
}
