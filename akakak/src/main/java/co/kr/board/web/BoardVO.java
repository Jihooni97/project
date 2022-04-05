package co.kr.board.web;

public class BoardVO {

	public int idx;
	public String name;
	public String title;
	public String content;
	public String file_real_name;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile_real_name() {
		return file_real_name;
	}

	public void setFile_real_name(String file_real_name) {
		this.file_real_name = file_real_name;
	}

	public BoardVO(int idx, String name, String title, String content, String file_real_name) {
		super();
		this.idx = idx;
		this.name = name;
		this.title = title;
		this.content = content;
		this.file_real_name = file_real_name;
	}

	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", file_real_name=" + file_real_name + "]";
	}

	public BoardVO() {
		super();
	}

}
