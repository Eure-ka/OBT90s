package com.oyster.trans_board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.trans_board.vo.TransBoardVO;


@Repository("transBoardDAO")
public class TransBoardDAOImpl implements TransBoardDAO{

	@Autowired
	private SqlSession sqlSession;

	public List<TransBoardVO> selectAllArticlesList() throws DataAccessException {
System.out.println("DAO 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>>>>   " + sqlSession.selectList("mapper.transboard.selectAllArticlesList"));
		
		List<TransBoardVO> list = (ArrayList)sqlSession.selectList("mapper.transboard.selectAllArticlesList");
		
		System.out.println("DAO<<<<<<<<<<< 조회 완료" + list);
		return list;
	}

	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int tb_number = selectNewActicleNO();
		articleMap.put("tb_number", tb_number);
		System.out.println("DAO new번호 가릿  insert 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   "+tb_number);
		sqlSession.insert("mapper.transboard.insertNewArticle",articleMap);
		return tb_number;
	}

	private int selectNewActicleNO() {
		System.out.println("DAO new번호 가릿 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   ");
		return sqlSession.selectOne("mapper.transboard.selectNewArticleNO");
	}

	public TransBoardVO selectArticle(int tb_number) throws DataAccessException {
		System.out.println("DAO view 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + tb_number);
		return sqlSession.selectOne("mapper.transboard.selectArticle",tb_number);
	}
	

	public void updateArticle(Map articleMap) throws DataAccessException {
		System.out.println("DAO update 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + articleMap);
		sqlSession.update("mapper.transboard.updateArticle", articleMap);
	}


	public void deleteArticle(int tb_number) throws DataAccessException {
		System.out.println("DAO delete 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + tb_number);
		sqlSession.delete("mapper.transboard.deleteArticle", tb_number);
		
	}

	public List selectImageFileList(int tb_number) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
