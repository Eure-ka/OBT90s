package com.oyster.free_board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.oyster.free_board.vo.FreeBoardVO;

@Repository("freeBoardDAO")
public class FreeBoardDAOImpl implements FreeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<FreeBoardVO> selectAllArticlesList() throws DataAccessException {
		System.out.println("DAO 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>>>>   " + sqlSession.selectList("mapper.freeboard.selectAllArticlesList"));
		
		List<FreeBoardVO> list = (ArrayList)sqlSession.selectList("mapper.freeboard.selectAllArticlesList");
		
		System.out.println("DAO<<<<<<<<<<< 조회 완료" + list);
		return list;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int fb_number = selectNewActicleNO();
		articleMap.put("fb_number", fb_number);
		System.out.println("DAO new번호 가릿  insert 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   "+fb_number);
		sqlSession.insert("mapper.freeboard.insertNewArticle",articleMap);
		return fb_number;
	}

	private int selectNewActicleNO() {
		System.out.println("DAO new번호 가릿 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   ");
		return sqlSession.selectOne("mapper.freeboard.selectNewArticleNO");
	}
	

	@Override
	public FreeBoardVO selectArticle(int fb_number) throws DataAccessException {
		System.out.println("DAO view 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + fb_number);
		return sqlSession.selectOne("mapper.freeboard.selectArticle",fb_number);
	}


	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		System.out.println("DAO update 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + articleMap);
		sqlSession.update("mapper.freeboard.updateArticle", articleMap);
	}

	@Override
	public void deleteArticle(int fb_number) throws DataAccessException {
		System.out.println("DAO delete 도착 ㅋㅋㅋㅋ >>>>>>>>>>>>>>>>>>>   " + fb_number);
		sqlSession.delete("mapper.freeboard.deleteArticle", fb_number);
		
	}

	@Override
	public List selectImageFileList(int fb_number) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
