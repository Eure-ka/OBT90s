package com.oyster.member.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.member.vo.MemberVO;
import com.oyster.member.dao.MemberDAO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	   public MemberVO standard_login(MemberVO memberVO) throws Exception {
	      System.out.println("들어왔느뇨" + memberDAO.standard_login(memberVO));
	      return memberDAO.standard_login(memberVO);
	   }

	@Override
	public void addMember(MemberVO memberVO) throws Exception {
		memberDAO.insertNewMember(memberVO);
		
	}

	@Override
	public String overlapped(String id) throws Exception {
		return memberDAO.selectOverlappedID(id);
	}
	
	@Override
	public void removeMember(MemberVO memberVO) throws Exception {
		System.out.println("서비스 memberVO>>>>>>>"+memberVO);
		memberDAO.removeMember(memberVO);
	}
	
}
