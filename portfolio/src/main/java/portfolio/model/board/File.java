package portfolio.model.board;

public class File {
	private int id;
	private int board_id;
	private String originname;
	private String filename;
	private String type;
	private String reg_date;
	
	public File() {
		
	}

	public File(int board_id, String originname, String filename, String type) {
		this.board_id = board_id;
		this.originname = originname;
		this.filename = filename;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", board_id=" + board_id + ", originname=" + originname + ", filename=" + filename
				+ ", type=" + type + ", reg_date=" + reg_date + "]";
	}

}
