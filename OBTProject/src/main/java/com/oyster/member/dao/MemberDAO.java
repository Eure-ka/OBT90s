package com.oyster.member.dao;

import org.springframework.dao.DataAccessException;

import com.oyster.member.vo.MemberVO;

public interface MemberDAO {

	public MemberVO standard_login(MemberVO memberVO) throws DataAccessException;
	
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	
	public String selectOverlappedID(String id) throws DataAccessException;
	
	public void removeMember(MemberVO memberVO) throws DataAccessException;

}
