package com.oyster.report_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.report_board.dao.Report_BoardDAO;
import com.oyster.report_board.vo.ImageVO;
import com.oyster.report_board.vo.Report_boardVO;

@Service("report_BoardSerivce")
@Transactional(propagation = Propagation.REQUIRED)
public class Report_BoardSerivceImpl implements Report_BoardSerivce {
	@Autowired
	Report_BoardDAO report_Boarddao;

	public List<Report_boardVO> listArticles() throws Exception {
		List<Report_boardVO> articlesList = report_Boarddao.selectAllArticlesList();
		return articlesList;
	}

	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int rb_number = report_Boarddao.insertNewArticle(articleMap);
		articleMap.put("rb_number", rb_number);
		/* Report_Boarddao.insertNewImage(articleMap); */
		return rb_number;
	}

	@Override
	public void removeArticle(int rb_number) throws Exception {
		//System.out.println("서비스 도착했어요");
		report_Boarddao.deleteArticle(rb_number);
	}

//	@Override
//	public Report_boardVO viewArticle(int rb_number) throws Exception {
//		Map articleMap = new HashMap();
//		Report_boardVO report_boardvo = report_Boarddao.selectArticle(rb_number);
//		List<ImageVO> imageFileList = report_Boarddao.selectImageFileList(rb_number);
//		articleMap.put("article", report_Boarddao);
//		articleMap.put("imageFileList", imageFileList);
//		return report_boardvo;
//	}
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Report_boardVO viewArticle(int rb_number) throws Exception {
		Report_boardVO report_boardVO = report_Boarddao.selectArticle(rb_number);
		report_Boarddao.boardHit(rb_number);
		return report_boardVO;
	}
	
	/*
	 * @Transactional(isolation = Isolation.READ_COMMITTED)
	 * 
	 * @Override public Report_boardVO read(int rb_number) throws Exception {
	 * report_Boarddao.boardHit(rb_number); return report_Boarddao.read(int
	 * rb_number); }
	 */
	// 수정
	/*
	 * @Override public void modArticle(Map articleMap) throws Exception {
	 * report_boardVO.updateArticle(articleMap); }
	 */
}
