package com.swust.vhas.dao;

import java.util.List;
import java.util.Map;

import com.swust.vhas.model.Author;

public interface AuthorDao {

	int deleteByPrimaryKey(Integer id);

	int insert(Author record);

	int insertSelective(Author record);

	Author selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Author record);

	int updateByPrimaryKey(Author record);

	Author exist(Map<String, Object> map);

	List<Author> selectUpdate(Map<String, Object> map);

	List<Author> selectTop(Map<String, Object> map);
}