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
		System.out.println("써비스 report_Boarddao.insertNewArticle(articleMap)>>>>>>>>>>>>>>>>>"+rb_number);
		articleMap.put("rb_number", rb_number);
		System.out.println("써비스 articleMap>>>>>>>>>>>>>>>>>"+articleMap);
		report_Boarddao.insertNewImage(articleMap);
		
		return rb_number;
	}

	@Override
	public void removeArticle(int rb_number) throws Exception {
		report_Boarddao.deleteArticle(rb_number);
	}
	
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Map viewArticle(int rb_number) throws Exception {
		Map articleMap = new HashMap();
		Report_boardVO report_boardvo = report_Boarddao.selectArticle(rb_number);
		List<ImageVO> imageFileList = report_Boarddao.selectImageFileList(rb_number);
		articleMap.put("article", report_boardvo);
		articleMap.put("imageFileList", imageFileList);
		report_Boarddao.boardHit(rb_number);
		return articleMap;
	}
	
	
	//원래 뷰 아티클
	/*
	 * @Override public Report_boardVO viewArticle(int rb_number) throws Exception {
	 * Report_boardVO report_boardVO = report_Boarddao.selectArticle(rb_number);
	 * report_Boarddao.boardHit(rb_number); return report_boardVO; }
	 */
	
	// 수정
	/*
	 * @Override public void modArticle(Map articleMap) throws Exception {
	 * report_boardVO.updateArticle(articleMap); }
	 */
}
