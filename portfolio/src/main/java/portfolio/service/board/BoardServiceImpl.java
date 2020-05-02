package portfolio.service.board;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import portfolio.dao.board.BoardDao;
import portfolio.model.board.Board;
import portfolio.model.board.BoardFile;

@Service
public class BoardServiceImpl implements BoardService {

	private static final String SAVE_PATH = "c:/upload/";

	@Autowired
	private BoardDao dao;

	@Override
	public List<Board> list(Integer start) {
		List<Board> list = dao.listAll(start, BoardService.LIMIT);
		ifTodayChangeRegDate(list);
		return list;
	}

	@Override
	public List<Board> list(Integer start, Integer categoryId) {
		List<Board> list = dao.listByCategory(start, BoardService.LIMIT, categoryId);
		ifTodayChangeRegDate(list);
		return list;
	}

	@Override
	public int count() {
		return dao.getCount();
	}

	@Override
	public int count(Integer categoryId) {
		return dao.getCountByCategory(categoryId);
	}

	@Override
	public void write(Board board, MultipartFile file) {
		dao.insert(board);
		int id = dao.getLastId("board");
		if (file != null) {
			upload(file, id);
		}
	}

	@Override
	public int edit(Board board, MultipartFile file) {
		if (file != null) {
			BoardFile fileDb = dao.selectFile(board.getId());

			if (fileDb != null) {
				dao.deleteFile(fileDb.getId());
			}
			upload(file, board.getId());
		}
		return dao.update(board);
	}

	@Override
	public int delete(Board board) {
		BoardFile fileDb = dao.selectFile(board.getId());
		if (fileDb != null) {
			dao.deleteFile(fileDb.getId());
		}
		return dao.delete(board);
	}

	@Override
	public Board read(int id) {
		return dao.select(id);
	}
	
	@Override
	public void hits(int id) {
		dao.updateReadCount(id);
	}

	@Override
	public int like(int id) {
		return dao.updateLikeCount(id);
	}

	@Override
	public int dislike(int id) {
		return dao.updateDislikeCount(id);
	}

	@Override
	public BoardFile getFile(int board_id) {
		return dao.selectFile(board_id);

	}

	public void upload(MultipartFile file, int board_id) {
		try {
			String originFileName = file.getOriginalFilename();
			String extensionName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());

			String saveFileName = convertFileName(extensionName);

			writeFile(file, saveFileName);
			dao.insertFile(new BoardFile(board_id, originFileName, saveFileName, extensionName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ifTodayChangeRegDate(List<Board> list) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());

		for (Board board : list) {
			String date = board.getReg_date().substring(0, 10);
			String time = board.getReg_date().substring(11, 16);

			if (today.equals(date)) {
				board.setReg_date(time);
			} else {
				board.setReg_date(date);
			}
		}
	}

	private String convertFileName(String extensionName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH) + 1;
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extensionName;

		return fileName;

	}

	private boolean writeFile(MultipartFile file, String saveFileName) throws IOException {
		boolean result = false;

		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + saveFileName);
		fos.write(data);
		fos.close();

		return result;
	}

}
