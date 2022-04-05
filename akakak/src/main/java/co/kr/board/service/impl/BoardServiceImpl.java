package co.kr.board.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.board.service.BoardService;
import co.kr.board.web.BoardVO;

@Service("testService")
public class BoardServiceImpl implements BoardService{
	
	@Resource(name = "testMapper")
	private BoardMapper testMapper;

	@Override
	public List<HashMap<String, Object>> select(int limit, int offset, String type, String keyword) {
		HashMap<String, Object> page = new HashMap<>();
		page.put("limit", limit);
		page.put("offset", offset);	
		page.put("type", type);
		page.put("keyword", keyword);	
		return testMapper.select(page);
	}

	@Override
	public HashMap<String, Object> detailPage(int no) {
		return testMapper.detailPage(no);
	}

	@Override
	public void delete(int no) {
		testMapper.deleteFile(no);
		testMapper.delete(no);	
	}

	@Override
	public void update(HashMap<String, Object> params) {
		testMapper.update(params);
		
	}

	@Override
	public HashMap<String, Object> updatePage(int no) {
		return testMapper.updatePage(no);
	}

	@Override
	public void insert(HashMap<String, Object> params) {
		testMapper.insert(params);
	}
	
	@Override
	public void fileInsert(HashMap<String, Object> fileInfoInsert) {
		testMapper.fileInsert(fileInfoInsert);
	}

	@Override
	public List<BoardVO> list() {
		return testMapper.list();
	}

	@Override
	public int totalPage(HashMap<String, Object> param) {
		return testMapper.totalPage(param);
	}
	
}