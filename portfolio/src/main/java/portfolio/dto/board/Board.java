package portfolio.dto.board;

public class Board {
	private int id;
	private int categoryId;
	private String category;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String reg_date;
	private int hits;
	private int likes;

	public Board() {
	}

	public Board(int categoryId, String title, String content, String writer, String password) {
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
	}

	public Board(int id, String category, String title, String content, String writer, String reg_date, int hits, int likes) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.reg_date = reg_date;
		this.hits = hits;
		this.likes = likes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Board [id=" + id + ", categoryId=" + categoryId + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", password=" + password + ", reg_date=" + reg_date
				+ ", hits=" + hits + ", likes=" + likes + "]";
	}

}
