package portfolio.model.board;

public class DetailDto {
	private int board_id;
	private String category;
	private String title;
	private String content;
	private String writer;
	private String reg_date;
	private int hits;
	private int likes;
	private int dislikes;
	private int file_id;
	private String filename;

	public DetailDto(int board_id, String category, String title, String content, String writer, String reg_date,
			int hits, int likes, int dislikes) {
		this.board_id = board_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.reg_date = reg_date;
		this.hits = hits;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public DetailDto(int board_id, String category, String title, String content, String writer, String reg_date,
			int hits, int likes, int dislikes, int file_id, String filename) {
		this.board_id = board_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.reg_date = reg_date;
		this.hits = hits;
		this.likes = likes;
		this.dislikes = dislikes;
		this.file_id = file_id;
		this.filename = filename;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "DetailDto [board_id=" + board_id + ", category=" + category + ", title=" + title + ", content="
				+ content + ", writer=" + writer + ", reg_date=" + reg_date + ", hits=" + hits + ", likes=" + likes
				+ ", dislikes=" + dislikes + ", file_id=" + file_id + ", filename=" + filename + "]";
	}

}
