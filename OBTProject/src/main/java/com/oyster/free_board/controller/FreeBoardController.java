package com.oyster.free_board.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.free_board.service.FreeBoardServiceImpl;
import com.oyster.free_board.vo.FreeBoardVO;

@Controller("freeBoardController")
public class FreeBoardController  {

	@Autowired
	FreeBoardServiceImpl freeBoardService;
	@Autowired
	private FreeBoardVO freeBoardVO;

	@RequestMapping(value = "/*.do", method = { RequestMethod.POST, RequestMethod.GET })
	protected ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	
	@RequestMapping(value = "/board/fb_board/fb_listArticles.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView fb_listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("Controller 입장: >>>> " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		List<FreeBoardVO> list = freeBoardService.listArticles();
		mav.addObject("articlesList", list);
		System.out.println("Controller<<<<<<<<<<< list >>>> " + list);
		return mav;
	}

	@RequestMapping(value = "/board/fb_board/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

//	@RequestMapping(value="/board/addNewArticle.do" ,method = {RequestMethod.POST,RequestMethod.GET})
//	@ResponseBody
//	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
//			throws Exception {
//		multipartRequest.setCharacterEncoding("utf-8");
//		Map<String,Object> articleMap = new HashMap<String, Object>();
//		Enumeration enu=multipartRequest.getParameterNames();
//		while(enu.hasMoreElements()){
//			String name=(String)enu.nextElement();
//			String value=multipartRequest.getParameter(name);
//			//:"id" jj
//			//title= " ~~~~~{ , ~~~context
//			articleMap.put(name,value);
//		}
//		//리퀘스트에 포함된 파일들을 특정 디스크 경로에 저장하고 저장된 파일 이름ㅇ르 반환받음
////		String imageFileName= upload(multipartRequest);
////		HttpSession session = multipartRequest.getSession();
////		MemberVO memberVO = (MemberVO) session.getAttribute("member");
////		String id = memberVO.getId();
////		articleMap.put("parentNO", 0);
////		articleMap.put("id", id);
////		articleMap.put("imageFileName", imageFileName);
//		//서비스로 넘겨줄 매개 변수 객체(articleMap) 완서성
//		
//		//클라이언트에게 반환할 reaponse객체 만들기
//		String message;
//		ResponseEntity resEnt=null;
//		//reponse header
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
//		try {
//			//완성한 맵을 서비스 해당 메소드로 넘겨준다. -> 게시글 번호 반환
//			int fb_number = freeBoardService.addNewArticle(articleMap);
////			//파일 경ㄹ로가 db에 저장되었으므로 임시 폴더에 저장되어웠던  파일들을 진짜 경로로 옮겨짐
////			if(imageFileName!=null && imageFileName.length()!=0) {
////				File srcFile = new 
////				File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
////				//진짜 저장경로에 게시글 번호로 폴더 생성하고 파일 이동 저장
////				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
////				FileUtils.moveFileToDirectory(srcFile, destDir,true);
////			}
////	
//			message = "<script>";
//			message += " alert('저장 성공했숑~');";
//			message += " location.href='"+multipartRequest.getContextPath()+"/board/fb_listArticles.do'; ";
//			message +=" </script>";
//		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//		}catch(Exception e) {
//			//try 부분에서 어떤 익셉션이
////			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
////			srcFile.delete();
//			
//			message = " <script>";
//			message +=" alert('오류 발생, 저장 안됨.');');";
//			message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do'; ";
//			message +=" </script>";
//			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
//			e.printStackTrace();
//		}
//		return resEnt;
//	}


	@RequestMapping(value = "/board/fb_board/addNewArticle.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity addNewArticle(HttpServletRequest Request, HttpServletResponse response) throws Exception {
		Request.setCharacterEncoding("utf-8");
		System.out.println("Controller add 입장: >>>> " + Request);
		Map<String, Object> articleMap = new HashMap<String, Object>();
		System.out.println("Controller add 입장: >>>> " + articleMap);
		Enumeration enu = Request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = Request.getParameter(name);
			// :"id" jj
			// title= " ~~~~~{ , ~~~context
			articleMap.put(name, value);
		}
		// 리퀘스트에 포함된 파일들을 특정 디스크 경로에 저장하고 저장된 파일 이름ㅇ르 반환받음
//		String imageFileName= upload(multipartRequest);
//		HttpSession session = multipartRequest.getSession();
//		MemberVO memberVO = (MemberVO) session.getAttribute("member");
//		String id = memberVO.getId();
//		articleMap.put("parentNO", 0);
//		articleMap.put("id", id);
//		articleMap.put("imageFileName", imageFileName);
		// 서비스로 넘겨줄 매개 변수 객체(articleMap) 완서성

		// 클라이언트에게 반환할 reaponse객체 만들기
		String message;
		ResponseEntity resEnt = null;
		// reponse header
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			// 완성한 맵을 서비스 해당 메소드로 넘겨준다. -> 게시글 번호 반환
			int fb_number = freeBoardService.addNewArticle(articleMap);
			System.out.println("서브. -> 게시글 번호 반환" + fb_number);
//			//파일 경ㄹ로가 db에 저장되었으므로 임시 폴더에 저장되어웠던  파일들을 진짜 경로로 옮겨짐
//			if(imageFileName!=null && imageFileName.length()!=0) {
//				File srcFile = new 
//				File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
//				//진짜 저장경로에 게시글 번호로 폴더 생성하고 파일 이동 저장
//				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
//				FileUtils.moveFileToDirectory(srcFile, destDir,true);
//			}
//	
			message = "<script>";
			message += " alert('저장 성공했숑~');";
			message += " location.href='" + Request.getContextPath() + "/board/fb_board/fb_listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			// try 부분에서 어떤 익셉션이
//			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
//			srcFile.delete();

			message = " <script>";
			message += " alert('오류 발생, 저장 안됨.');');";
			message += " location.href='" + Request.getContextPath() + "/board/fb_board/fb_articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}


	
	@RequestMapping(value = "/board/fb_board/fb_viewArticle.do", method = { RequestMethod.GET })
	public ModelAndView fb_viewArticle(@RequestParam("fb_number") int fb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("Controller viewArticles 입장: >>>> " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		Map<String, Object> resultMap = freeBoardService.viewArticle(fb_number);
		mav.addObject("resultMap", resultMap);
		return mav;
	}


	@RequestMapping(value = "/board/fb_board/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("fb_number") int fb_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			freeBoardService.removeArticle(fb_number);
//			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
//			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('삭제 완료');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert(' 오류 발생.');";
			message += " location.href='" + request.getContextPath() + "/board/fb_board/fb_listArticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}