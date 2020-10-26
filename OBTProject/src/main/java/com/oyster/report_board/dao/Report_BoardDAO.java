package com.oyster.report_board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface Report_BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public void deleteArticle(int rb_number) throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
}
