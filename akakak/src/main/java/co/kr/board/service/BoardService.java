package co.kr.board.service;

import java.util.HashMap;
import java.util.List;

import co.kr.board.web.BoardVO;

public interface BoardService {

	public List<HashMap<String, Object>> select(int limit, int offset, String type, String keyword);

	public HashMap<String, Object> detailPage(int no);

	public void delete(int no);

	public void update(HashMap<String, Object> params);

	public HashMap<String, Object> updatePage(int no);

	public void insert(HashMap<String, Object> params);
	
	public void fileInsert(HashMap<String, Object> fileInfoInsert);

	public List<BoardVO> list();

	public int totalPage();

	
}