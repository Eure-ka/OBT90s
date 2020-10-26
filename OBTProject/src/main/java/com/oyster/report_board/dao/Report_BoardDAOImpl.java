package com.oyster.report_board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.report_board.vo.Report_boardVO;


@Repository("Report_BoardDAO")
public class Report_BoardDAOImpl implements Report_BoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<Report_boardVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}
	
	@Override
	public void deleteArticle(int rb_number) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", rb_number);
		
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		//마지막 게시글 번호에 1을 더한 숫자를 받아옴
		int articleNO = selectNewArticleNO();
		//매개변수로 받은 맵에 글번호 추가
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
}
