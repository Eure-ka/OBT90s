package com.oyster.main;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * 
	 * return "main"; }
	 * 
	 * @RequestMapping(value = "/main.do", method = RequestMethod.GET) public String
	 * main(Locale locale, Model model) {
	 * 
	 * return "main"; }
	 */
	
	@RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
	public String mainboard(Locale locale, Model model) {
		
		return "mainboard";
	}
	
}
