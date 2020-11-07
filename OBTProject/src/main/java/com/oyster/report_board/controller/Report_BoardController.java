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

import com.oyster.member.vo.MemberVO;
import com.oyster.report_board.service.Report_BoardSerivce;
import com.oyster.report_board.vo.ImageVO;
import com.oyster.report_board.vo.Report_boardVO;

@Controller("Report_BoardController")
@RequestMapping(value = "/board")
public class Report_BoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\upload";
	@Autowired
	private Report_BoardSerivce report_boardserivce;

	@Autowired
	private Report_boardVO report_boardvo;

	// 글쓰기 창 넘어가기
	@RequestMapping(value = "/rb_board/rb_articleForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView newArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// 수정하기 창 넘어가기
	@RequestMapping(value = "/rb_board/rb_articlemodForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView modArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int rb_number = Integer.parseInt(request.getParameter("rb_number"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("rb_number", rb_number);
		mav.setViewName(viewName);
		return mav;
	}

	// 답글쓰기 창 넘어가기
	@RequestMapping(value = "/rb_board/rb_rearticleForm.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	private ModelAndView reArticleform(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		int parent_no = Integer.parseInt(request.getParameter("parent_no")); 
		ModelAndView mav = new ModelAndView();
		mav.addObject("parent_no", parent_no);
		mav.setViewName(viewName);
		return mav;
	}

	// 게시판 목록 보여주기
	@RequestMapping(value = "/rb_board/rb_listarticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<Report_boardVO> articlesList = report_boardserivce.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;
	}
	
	// 글 보여주기
		@RequestMapping(value = "/rb_board/rb_View.do", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView viewArticle(@RequestParam("rb_number") int rb_number, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String viewName = (String) request.getAttribute("viewName");
			Map articleMap = (Map) report_boardserivce.viewArticle(rb_number);
			ModelAndView mav = new ModelAndView();
			mav.setViewName(viewName);
			mav.addObject("articleMap", articleMap);
			return mav;
		}
		
	// 글 삭제하기
	@RequestMapping(value = "/rb_board/removereport_board.do", method = { RequestMethod.POST })
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
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}


	// 답글 쓰기
	@RequestMapping(value = "/rb_board/report_boardNewReArticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity addnewReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			articleMap.put(name, value);
		}

		System.out.println("articleMap>>>>>>"+articleMap);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.insertNewreply(articleMap);
			message = "<script>";
			message += " alert('답글을 추가했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + request.getContextPath()
					+ "/board/rb_board/rb_rearticleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// 글 수정하기
	@RequestMapping(value = "/rb_board/modarticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			articleMap.put(name, value);
		}
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			report_boardserivce.modArticle(articleMap);
			message = "<script>";
			message += " alert('글이 수정되었습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_listarticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + request.getContextPath() + "/board/rb_board/rb_articlemodForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	 // 다중 이미지 글쓰기
	   @RequestMapping(value = "/rb_board/addNewreport_board.do", method = { RequestMethod.GET, RequestMethod.POST })
	   @ResponseBody
	   public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
	         throws Exception {
	      multipartRequest.setCharacterEncoding("utf-8");
	      String imageFileName = null;
	      Map articleMap = new HashMap();
	      Enumeration enu=multipartRequest.getParameterNames();
	      while (enu.hasMoreElements()) {
	         String name = (String) enu.nextElement();
	         String value = multipartRequest.getParameter(name);
	         articleMap.put(name, value);
	      }

	      // 로그인 시 세션에 저장된 회원 정보에서 글쓴이 아이디를 얻어와서 Map에 저장합니다.
	      HttpSession session = multipartRequest.getSession();
	      MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
	      if (memberVO != null && memberVO.getMember_id() != null) {
	         String id = memberVO.getMember_id();
	         articleMap.put("parentNO", 0);
	         articleMap.put("member_id", id);
	         articleMap.put("imageFileName", imageFileName);
	      }
	      System.out.println("upload Contoller >>> " + articleMap);
	      List<String> fileList = upload(multipartRequest);
	      List<ImageVO> imageFileList = new ArrayList<ImageVO>();
	      if (fileList != null && fileList.size() != 0) {
	         for (String fileName : fileList) {
	            ImageVO imageVO = new ImageVO();
	            imageVO.setImageFileName(fileName);
	            imageFileList.add(imageVO);
	            System.out.println("컨트롤러 fileList>>>>>>>>>" + fileName);
	         }
	         articleMap.put("imageFileList", imageFileList);
	      }
	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	      try {
	         int rb_number = report_boardserivce.addNewArticle(articleMap);
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (ImageVO imageVO : imageFileList) {
	               imageFileName = imageVO.getImageFileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
	               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + rb_number);
	               // destDir.mkdirs();
	               FileUtils.moveFileToDirectory(srcFile, destDir, true);
	               System.out.println("컨트롤러 imageVO>>>>>>>>>>" + imageVO);
	            }
	         }

	         message = "<script>";
	         message += " alert('새글을 추가했습니다.');";
	         message += " location.href='" + multipartRequest.getContextPath()
	               + "/board/rb_board/rb_listarticles.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

	      } catch (Exception e) {
	         if (imageFileList != null && imageFileList.size() != 0) {
	            for (ImageVO imageVO : imageFileList) {
	               imageFileName = imageVO.getImageFileName();
	               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
	               srcFile.delete();
	            }
	         }

	         message = " <script>";
	         message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
	         message += " location.href='" + multipartRequest.getContextPath()
	               + "/board/rb_board/rb_articleForm.do'; ";
	         message += " </script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	         e.printStackTrace();
	      }
	      return resEnt;
	   }

	   // 다중 이미지 업로드하기
	   private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
	      List<String> fileList= new ArrayList<String>();
	      Iterator<String> fileNames = multipartRequest.getFileNames();
	      while(fileNames.hasNext()){
	         String fileName = fileNames.next();
	         MultipartFile mFile = multipartRequest.getFile(fileName);
	         String originalFileName=mFile.getOriginalFilename();
	         fileList.add(originalFileName);
	         File file = new File(ARTICLE_IMAGE_REPO +"\\"+ fileName);
	         if(mFile.getSize()!=0){ //File Null Check
	            if(! file.exists()){ 
	               if(file.getParentFile().mkdirs()){ 
	                     file.createNewFile();
	               }
	            }
	            mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+originalFileName));
	         }
	      }
	      return fileList;
	   }
}