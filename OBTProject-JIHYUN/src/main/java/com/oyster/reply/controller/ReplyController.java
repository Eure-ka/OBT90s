package com.oyster.reply.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oyster.reply.service.ReplyServiceImpl;
import com.oyster.reply.vo.ReplyVO;

@Controller("replyController")
public class ReplyController {

	@Autowired
	ReplyServiceImpl replyService;
	@Autowired
	private ReplyVO replyVO;

	@RequestMapping(value = "/reply/addNewArticle.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity addNewReply(HttpServletRequest Request, HttpServletResponse response) throws Exception {
		Request.setCharacterEncoding("utf-8");
		System.out.println("Controller add 입장: >>>> " + Request);
		Map<String, Object> articleMap = new HashMap<String, Object>();
		System.out.println("Controller add 입장: >>>> " + articleMap);
		Enumeration enu = Request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = Request.getParameter(name);
			articleMap.put(name, value);
		}

		// 클라이언트에게 반환할 reaponse객체 만들기
		String message;
		ResponseEntity resEnt = null;
		// reponse header
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			// 완성한 맵을 서비스 해당 메소드로 넘겨준다. -> 게시글 번호 반환
			int reply_no = replyService.addNewArticle(articleMap);
			System.out.println("서브. -> 게시글 번호 반환" + reply_no);
//				
			message = "<script>";
			message += " alert('댓글 저장 성공했숑~');";
			message += " location.href='" + Request.getContextPath() + "//common/reply/fb_listArticles.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {

			message = " <script>";
			message += " alert('오류 발생, 저장 안됨.');');";
			message += " location.href='" + Request.getContextPath() + "/common/reply/fb_articleForm.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	public ModelAndView viewReply(int reply_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("Controller viewArticles 입장: >>>> " + viewName);
		replyVO = replyService.viewReply(reply_no);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("article", replyVO);
		return mav;
	}

	@RequestMapping(value = "/reply/removeReply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeReply(@RequestBody Map<String, Object> replyMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("Remove controller>>>>check>>>>>>>>>>" + request.getRequestURI());
		System.out.println("Remove controller>>>>>>>>>>>>>>" + replyMap.get("boardKind"));
		System.out.println("Remove controller>>>>>>>>>>>>>>" + replyMap.get("reply_no"));
		int reply_no = Integer.parseInt((String)replyMap.get("reply_no"));
		ResponseEntity resEnt;
		//String message;

		try {
			replyService.deleteReply(reply_no);

//			message = "<script>";
//			message += " alert('댓글을 삭제했습니다.');";
//			message += " location.href='" + request.getContextPath()
//					+ "/board/fb_board/fb_viewArticle.do?fb_number=1';";
//			message += " </script>";
			resEnt = new ResponseEntity("success", HttpStatus.OK);

		} catch (Exception e) {
//			message = "<script>";
//			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
//			message += " </script>";
			resEnt = new ResponseEntity("fail", HttpStatus.NOT_MODIFIED);
			e.printStackTrace();
		}
		return resEnt;
	}

	

}
