package com.oyster.report_board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.member.vo.MemberVO;
import com.oyster.report_board.service.Report_BoardSerivce;
import com.oyster.report_board.vo.Report_boardVO;

@Controller("Report_BoardController")
@RequestMapping(value = "/board")
public class Report_BoardControllerImpl implements Report_BoardController {
	@Autowired
	private Report_BoardSerivce report_boardserivce;

	@Autowired
	private Report_boardVO report_boardvo;

	/*
	 * @RequestMapping(value = "/*.do", method = { RequestMethod.POST,
	 * RequestMethod.GET }) protected ModelAndView viewForm(HttpServletRequest
	 * request, HttpServletResponse response) throws Exception { String viewName =
	 * (String) request.getAttribute("viewName"); ModelAndView mav = new
	 * ModelAndView(viewName); return mav; }
	 */

	// 게시판 목록 보여주기

	@Override
	@RequestMapping(value = "/report_boardlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List articlesList = report_boardserivce.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}

	// 글 삭제하기
	@Override
	@RequestMapping(value = "/removereport_board.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("rb_number") int rb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.removeArticle(rb_number);
			/*
			 * File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
			 * FileUtils.deleteDirectory(destDir);
			 */

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 글쓰기
	@Override
	@RequestMapping(value = "/addNewreport_board.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;

		Map articleMap = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		// 로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getMember_id();
		articleMap.put("id", id);

		/*
		 * List<String> fileList =upload(multipartRequest); List<ImageVO> imageFileList
		 * = new ArrayList<ImageVO>(); if(fileList!= null && fileList.size()!=0) {
		 * for(String fileName : fileList) { ImageVO imageVO = new ImageVO();
		 * imageVO.setImageFileName(fileName); imageFileList.add(imageVO); }
		 * articleMap.put("imageFileList", imageFileList); }
		 */
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int rb_number = report_boardserivce.addNewArticle(articleMap);

//			if (imageFileList != null && imageFileList.size() != 0) {
//				for (ImageVO imageVO : imageFileList) {
//					imageFileName = imageVO.getImageFileName();
//					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
//					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO); // destDir.mkdirs();
//					FileUtils.moveFileToDirectory(srcFile, destDir, true);
//				}
//			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			/*
			 * if (imageFileList != null && imageFileList.size() != 0) { for (ImageVO
			 * imageVO : imageFileList) { imageFileName = imageVO.getImageFileName(); File
			 * srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" +
			 * imageFileName); srcFile.delete(); } }
			 */

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}