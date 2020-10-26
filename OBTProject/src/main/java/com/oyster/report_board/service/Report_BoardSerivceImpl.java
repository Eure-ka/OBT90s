package com.oyster.report_board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.report_board.dao.Report_BoardDAO;
import com.oyster.report_board.vo.Report_boardVO;

@Service("Report_BoardSerivce")
@Transactional(propagation = Propagation.REQUIRED)
public class Report_BoardSerivceImpl implements Report_BoardSerivce {
	@Autowired
	Report_BoardDAO Report_Boarddao;

	public List<Report_boardVO> listArticles() throws Exception {
		List<Report_boardVO> articlesList = Report_Boarddao.selectAllArticlesList();
		return articlesList;
	}


	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int rb_number = Report_Boarddao.insertNewArticle(articleMap);
		articleMap.put("rb_number", rb_number);
		/* Report_Boarddao.insertNewImage(articleMap); */
		return rb_number;
	}

	@Override
	public void removeArticle(int rb_number) throws Exception {
		Report_Boarddao.deleteArticle(rb_number);
	}

//     @Override public Map viewArticle(int articleNO) throws Exception { Map
//		 articleMap = new HashMap(); ArticleVO articleVO=
//		 Report_Boarddao.selectArticle(articleNO); List<ImageVO> imageFileList
//		 =boardDAO.selectImageFileList(articleNO);
//		 articleMap.put("article",articleVO); articleMap.put("imageFileList",
//		 imageFileList); return articleMap; }
	/*
	 * @Override public Report_boardVO viewArticle(int rb_number) throws Exception {
	 * Report_boardVO articleVO = Report_Boarddao.selectArticle(rb_number); return
	 * articleVO; }
	 */

	// 수정
	/*
	 * @Override public void modArticle(Map articleMap) throws Exception {
	 * Report_boardVO.updateArticle(articleMap); }
	 */
}
