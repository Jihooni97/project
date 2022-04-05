package co.kr.board.service.impl;

import java.util.HashMap;
import java.util.List;

import co.kr.board.web.BoardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("testMapper")
public interface BoardMapper {
	
	public List<HashMap<String, Object>> select(HashMap<String, Object>page);
	
	public HashMap<String, Object> detailPage(int no);
	
	public void delete(int no);
	
	public void deleteFile(int no);
	
	public void update(HashMap<String, Object> params);
	
	public HashMap<String, Object> updatePage(int no);
	
	public void insert(HashMap<String, Object> params);
	
	public void fileInsert(HashMap<String, Object> fileInfoInsert);
	
	public List<BoardVO> list();
	
	public int totalPage();

}
