package portfolio.model.board;

public class Board {
	private int id;
	private int category_id;
	private String category;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String reg_date;
	private int hits;
	private int likes;
	private int dislikes;

	public Board() {
	}

	public Board(int category_id, String title, String content, String writer, String password) {
		this.category_id = category_id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", category_id=" + category_id + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", password=" + password + ", reg_date=" + reg_date
				+ ", hits=" + hits + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}

}
