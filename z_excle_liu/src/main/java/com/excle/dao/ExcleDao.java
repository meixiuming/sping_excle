package com.excle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.excle.domain.Excle;

@Mapper
public interface  ExcleDao {
	
	 List findByNo(String no);
	 List<Excle> findAllUsers();

	void saveOrUpdate(String no, String smt, String ffo, String pratName, String reqmTNo, 
			String procedureNo, String procedureTitle, String reg);
	void save(String no, String smt, String ffo, String pratName, String reqmTNo, 
			String procedureNo, String procedureTitle, String reg);
	void update(String no, String smt, String ffo, String pratName, String reqmTNo,
			String procedureNo, String procedureTitle, String reg);
	void save(Excle excle);
	void update(Excle excle);


}
