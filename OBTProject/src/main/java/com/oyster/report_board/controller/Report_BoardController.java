package com.oyster.report_board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



public interface Report_BoardController {
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity  removeArticle(@RequestParam("rb_number") int rb_number,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
}
