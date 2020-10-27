package com.oyster.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.oyster.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO standard_login(Map loginMap) throws DataAccessException;
	
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	
	public String selectOverlappedID(String id) throws DataAccessException;

}
