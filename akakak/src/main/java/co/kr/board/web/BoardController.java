package co.kr.board.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import co.kr.board.service.BoardService;

@Controller
public class BoardController {
	
	@Resource(name = "testService")
	private BoardService testService;
	
	@RequestMapping(value="/main.do")
	public String mainPage(){
		
		return "/board/main";
	}
	
	//ajax 사용 
	@RequestMapping(value="/test.do")
	public String boardList(Model model){
		
		return "/board/select_test";
	}
	
	@RequestMapping(value = "/test_select.do")
	public ModelAndView getList(@RequestParam HashMap<String, Object> param){
		int nowPage = Integer.parseInt(param.get("nowPage").toString());
		
		String type = (String) param.get("type");
		String keyword = (String)param.get("keyword");
//		HashMap<String, Object> param2 = new HashMap<String, Object>();
//		param2.put("type", type);
//		param2.put("keyword", keyword);
		ModelAndView json = new ModelAndView("jsonView");
		int totalCount = testService.totalPage(param);
		int offset = (nowPage - 1) * 5;
		int limit = 5;
		List<HashMap<String, Object>> selectList = testService.select(limit, offset, type, keyword);
		json.addObject("list", selectList);
		json.addObject("totalCount", totalCount);
		json.addObject("type", type);
		json.addObject("keyword", keyword);
		
		return json;
	}
	
	
	/* 
	@RequestMapping(value = "/select.do")
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") String page, String type, String keyword) {
		int nowPage = Integer.parseInt(page); // 현재 페이지 기본값 1
		int totalCount = testService.totalPage(); // DB에 있는 데이터 카운트 값
		int totalPage = (int) Math.ceil(totalCount / 5.0); //만들어야 할 페이지 수(올림)
		int endNum = nowPage * 5; //1페이지면 0~5 2페이지는 6~10 끝페이지 게시물 번호찾기
		int startNum = endNum - 4; // 시작하는 게시물 번호
		int offset = (nowPage - 1) * 5; //보여질 게시물 수 1페이지면 0~5까지 보여지고 3페이지면 10~15까지 보여지도록
		int limit = 5; //5개씩 끊어서
		
		int pageBlock = (int) Math.ceil(totalPage / 5.0); // 페이지 5개씩 나누는 페이지 블럭
		int nowPageBlock = (int) Math.ceil(nowPage / 5.0); //현재 페이지 블럭 위치 (1블럭이면 1~5페이지까지 2블럭 6~10)
		int endPage = nowPageBlock * 5; // 현재 페이지블럭의 끝 페이지번호 구하기
		int startPage = endPage - 4; // 시작 페이지번호 구하기
		if(totalPage<endPage){ //마지막 페이지가 총 페이지개수보다 많을 때 마지막 페이지번호를 끝페이지번호로 대입
			endPage = totalPage;
		}
		
		boolean prev = false;
		if(nowPage>1){
			prev = true;
		}
		boolean next = true;
		if(nowPage == totalPage){
			next = false;
		}
		
		List<HashMap<String, Object>> selectList = testService.select(limit, offset, type, keyword);
		model.addAttribute("result", selectList);
		model.addAttribute("nowPage", nowPage); //뒤로가기 눌렀을 때 현재페이지 가져와서 뒤로가기
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "/board/select";
	} */
	
	@RequestMapping(value="/detailPage.do")
	public String detailPage(Model model, int no, int nowPage){
		HashMap<String, Object> detailList = testService.detailPage(no);
		model.addAttribute("data", detailList);
		model.addAttribute("nowPage", nowPage); //뒤로가기 눌렀을 때 현재페이지 가져와서 뒤로가기
		
		return "/board/detail";
	}
	
	@RequestMapping(value="/updatePage.do", method = RequestMethod.GET )
	public String updatePage(Model model, int no){
		HashMap<String, Object> updateList = testService.updatePage(no);
		model.addAttribute("data", updateList);
		return "/board/updatePage";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String Update(@RequestParam HashMap<String, Object>params, Model model, MultipartFile file){
		params.put("boardIdx", Integer.parseInt((String)params.get("boardIdx")));
		testService.update(params);
		
		return "redirect:/test.do";
	}
	
	@RequestMapping(value="/delete.do")
	public String delete(int no){
		testService.delete(no);
		
		return "redirect:/test.do";
	}
	
	@RequestMapping(value="/insertPage.do", method = RequestMethod.GET)
	public String insertPage(){
		return "/board/write";
	}
	
	@RequestMapping(value="/mapPage.do")
	public String mapPage(){
		return "/board/map";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insert(@RequestParam HashMap<String, Object> params, MultipartFile file) {
		ModelAndView jsonView = new ModelAndView("jsonView");
		
//		params.put("boardIdx", Integer.parseInt((String) params.get("boardIdx")));
		
		testService.insert(params);

		//******************************fileupload******************************//		
		
		String uploadPath = "D:\\practice2\\upload"; //파일경로 지정	
		UUID uuid = UUID.randomUUID();

		try {
			if (!file.isEmpty()) {
				String fileOri = file.getOriginalFilename();
				String filename = uuid.toString()+"_"+file.getOriginalFilename();

//				String path = request.getServletContext().getRealPath("/") + "upload"; 	// 파일저장위치
				
				File pathFile = new File(uploadPath);  //파일경로에 폴더생성


				if (!pathFile.exists()) { //pathFile이 존재하지 않을경우
					pathFile.mkdirs(); 
				}
				
				String fullPath = String.format("%s/%s", uploadPath, filename);
				File newFile = new File(fullPath);
				file.transferTo(newFile); //파일경로에 파일생성
				
				HashMap<String, Object> fileInfoInsert = new HashMap<String, Object>();
				
				fileInfoInsert.put("fileName", filename);
				fileInfoInsert.put("filePath", fullPath);
				fileInfoInsert.put("fileOri", fileOri);
				
				testService.fileInsert(fileInfoInsert);
				
				System.out.println("파일경로 : " + uploadPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("test_error");
		}
		jsonView.addObject("result","success");
		return jsonView; 
	}
	//****************************fileupload end****************************//
	
	
	//******************************filedown*******************************//	
	@RequestMapping(value = "/fileDown.do")
	public void fileDown(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
		try {
			String path = "D:\\practice2\\upload\\" + fileName; //파일경로와 파일이름
			
			String encordedFilename = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20"); //인코딩
			
			//파일을 Byte배열로 변환
			byte[] fileByte = FileUtils.readFileToByteArray(new File(path)); 
			
			response.setContentType("application/octet-stream");
			// 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더(한글 인코딩(encordedFilename))
			response.setHeader("Content-Disposition", "attachment;filename=" + encordedFilename); //+ ";fileName*=UTF-8" + encordedFilename
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
			
		} catch (IOException e) {
			throw new IOException("download error");
		}
	}
	//****************************filedown end*****************************//
	
	
	@RequestMapping(value = "/excelDown.do")
	public void excelDown(HttpServletResponse response) throws IOException{
		
		List<BoardVO> list = testService.list();
		
		XSSFWorkbook workbook = new XSSFWorkbook(); //새 Excel Workbook 생성
		XSSFSheet sheet = workbook.createSheet("sheet"); //새로운 Sheet 생성
		
		//Sheet -> Row -> Cell 순서로 접근하여 값 입력
		XSSFRow row = null;
		XSSFCell cell = null;
		int rowNum = 0;
		
		//스타일
		CellStyle headStyle = workbook.createCellStyle();
		
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
	    headStyle.setBorderLeft(CellStyle.BORDER_THIN);
	    headStyle.setBorderBottom(CellStyle.BORDER_THIN);
	    headStyle.setBorderTop(CellStyle.BORDER_THIN);

		//헤더 생성
		row = sheet.createRow(rowNum++);
		
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("번호"); //번호, 작성자, 제목, 내용, 파일이름
		
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성자");
		
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("제목");

		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("내용");

		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("파일이름");		
		
		for(BoardVO vo  : list){
			row = sheet.createRow(rowNum++);
			
			cell = row.createCell(0);
			cell.setCellValue(vo.getIdx());
			
			cell = row.createCell(1);
			cell.setCellValue(vo.getName());
			
			cell = row.createCell(2);
			cell.setCellValue(vo.getTitle());
			
			cell = row.createCell(3);
			cell.setCellValue(vo.getContent());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getFile_real_name());
		}
		
		//컨텐츠 타입과 파일명
		String fileName = "게시판Excel.xlsx";
		String encordedFilename = URLEncoder.encode(fileName, "UTF-8"); //인코딩
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + encordedFilename);
		
		//엑셀 출력
		workbook.write(response.getOutputStream());
//		workbook.close();
		
	}
}