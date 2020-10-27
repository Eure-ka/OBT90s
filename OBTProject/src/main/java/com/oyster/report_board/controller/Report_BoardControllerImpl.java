package com.oyster.report_board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.report_board.vo.ImageVO;
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

	// 게시판 목록 보여주기
	@Override
	@RequestMapping(value = "/report_boardlist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<Report_boardVO> articlesList = report_boardserivce.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}
	

	 //글 삭제하기
	@Override
	@RequestMapping(value = "/removereport_board.do", method = {RequestMethod.POST })
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("rb_number") int rb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("컨트롤러 도착했어요");
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.removeArticle(rb_number);

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/report_boardlist.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/report_boardlist.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 글 보여주기
	@RequestMapping(value="/report_boardView.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewArticle(@RequestParam("rb_number") int rb_number,
			  HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String viewName = (String)request.getAttribute("viewName");
		report_boardvo=report_boardserivce.viewArticle(rb_number);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("articleMap", report_boardvo);
		return mav;
	}
	
	 //글쓰기
	@Override
	@RequestMapping(value="/addNewreport_board.do" ,method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addNewArticle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> articleMap = new HashMap<String, Object>();
		//Enumeration enu=multipartRequest.getParameterNames();
//		while(enu.hasMoreElements()){
//			String name=(String)enu.nextElement();
//			String value=multipartRequest.getParameter(name);
//			articleMap.put(name,value);
//		}
		
		/* String imageFileName= upload(multipartRequest); */
//		HttpSession session = multipartRequest.getSession();
//		MemberVO memberVO = (MemberVO) session.getAttribute("member");
//		String id = memberVO.getMember_id();
//		articleMap.put("parentNO", 0);
//		articleMap.put("id", id);
		/* articleMap.put("imageFileName", imageFileName); */
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int articleNO = report_boardserivce.addNewArticle(articleMap);
//			if(imageFileName!=null && imageFileName.length()!=0) {
//				File srcFile = new 
//				File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
//				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
//				FileUtils.moveFileToDirectory(srcFile, destDir,true);
//			}
	
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+request.getContextPath()+"/board/report_boardlist.do'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
//			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
//			srcFile.delete();
			
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message +=" location.href='"+request.getContextPath()+"/board/articleForm.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	
	//글쓰기 창 넘어가기
	@RequestMapping(value = "/*Form.do", method =  {RequestMethod.GET,RequestMethod.POST})
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
}