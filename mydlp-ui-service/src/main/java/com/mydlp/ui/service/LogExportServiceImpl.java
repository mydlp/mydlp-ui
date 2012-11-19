package com.mydlp.ui.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.mydlp.ui.dao.IncidentLogDAO;
import com.mydlp.ui.domain.AbstractLogFileBlueprint;
import com.mydlp.ui.domain.AuthUser;
import com.mydlp.ui.domain.IncidentLog;
import com.mydlp.ui.domain.IncidentLogFile;

@Service("logExportService")
public class LogExportServiceImpl implements LogExportService {

	private static Logger logger = LoggerFactory
			.getLogger(LogExportServiceImpl.class);

	@Autowired
	protected IncidentLogDAO incidentLogDAO;
	
	@Autowired
	protected TaskScheduler taskScheduler;

	@Autowired
	protected MessageSource messageSource;
	
	protected Map<String, byte[]> exportCache = new TreeMap<String, byte[]>();

	private SecureRandom random = new SecureRandom();

	private static final String[] titles = { "date", "src_addr", "src_user", "destination",
			"ruleId", "action", "channel", "file_name", "file_size",
			"file_type", "file_hash" };
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'GMT'");

	protected void toExcel(OutputStream outputStream, AuthUser authUser,
			List<List<Object>> criteriaList) throws IOException {

		List<IncidentLog> logs = incidentLogDAO.getIncidents(authUser,
				criteriaList, 0, 1000);

		XSSFWorkbook wb = new XSSFWorkbook();

		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(messageSource.getMessage("export.excel.title", null, Locale.US));

		// turn off gridlines
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		// the header row: centered text in 48pt font
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(12.75f);

		for (int i = 0; i < titles.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(messageSource.getMessage("export.excel.header."+titles[i], null, Locale.US)); // TODO: use resources for
											// localization
			cell.setCellStyle(styles.get("header"));
		}

		sheet.setColumnWidth(0, 256 * 30);
		sheet.setColumnWidth(1, 256 * 16);
		sheet.setColumnWidth(2, 256 * 24);
		sheet.setColumnWidth(3, 256 * 30);
		sheet.setColumnWidth(4, 256 * 8);
		sheet.setColumnWidth(5, 256 * 12);
		sheet.setColumnWidth(6, 256 * 12);
		sheet.setColumnWidth(7, 256 * 18);
		sheet.setColumnWidth(8, 256 * 12);
		sheet.setColumnWidth(9, 256 * 16);
		sheet.setColumnWidth(10, 256 * 40);

		// freeze the first row
		sheet.createFreezePane(0, 1);

		Row row;
		Cell cell;
		int rownum = 1;
		for (IncidentLog log : logs) {
			for (IncidentLogFile file : log.getFiles()) {
				row = sheet.createRow(rownum++);
				cell = row.createCell(0);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getDate() != null)
				{
					cell.setCellValue(simpleDateFormat.format(log.getDate()));
				}

				cell = row.createCell(1);
				cell.setCellStyle(styles.get("cell_normal"));
				if (log.getSourceIp() != null)
				{
					long l = log.getSourceIp().longValue();
				    String ipStr = ((l >> 24) & 0xFF) + "." + 
				                ((l >> 16) & 0xFF) + "." + 
				                ((l >> 8) & 0xFF) + "." + 
				                (l & 0xFF); 
					cell.setCellValue(ipStr);
				}

				cell = row.createCell(2);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getSourceUser() != null)
				{
					cell.setCellValue(log.getSourceUser());
				}
				
				cell = row.createCell(3);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getSourceUser() != null)
				{
					cell.setCellValue(log.getDestination());
				}

				cell = row.createCell(4);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getRuleId() != null)
				{
					cell.setCellValue(log.getRuleId().toString());
				}

				cell = row.createCell(5);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getAction() != null)
				{
					String m = null;
					try {
						m = messageSource.getMessage("export.excel.action." + log.getAction(), null, Locale.US);
					} catch (NoSuchMessageException e) {
						m = log.getAction();
					}
					cell.setCellValue(m);
				}

				cell = row.createCell(6);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (log.getChannel() != null)
				{
					String m = null;
					try {
						m = messageSource.getMessage("export.excel.channel." + log.getChannel(), null, Locale.US);
					} catch (NoSuchMessageException e) {
						m = log.getChannel();
					}
					cell.setCellValue(m);
				}

				cell = row.createCell(7);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (file.getFilename() != null)
				{
					cell.setCellValue(file.getFilename());
				}

				AbstractLogFileBlueprint bp = null;
				if (file.getContent() != null)
					bp = file.getContent();
				else if (file.getBlueprint() != null)
					bp = file.getBlueprint();
				
				cell = row.createCell(8);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (bp != null && bp.getSize() != null)
				{
					cell.setCellValue(FileSizeUtil.humanReadableByteCount(bp.getSize()));
				}
				
				cell = row.createCell(9);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (bp != null && bp.getMimeType() != null)
				{
					cell.setCellValue(bp.getMimeType());
				}

				cell = row.createCell(10);
				cell.setCellStyle(styles.get("cell_normal"));
				
				if (bp != null && bp.getMd5Hash() != null)
				{
					cell.setCellValue(bp.getMd5Hash());
				}
				
			}
		}

		wb.write(outputStream);
	}

	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("header", style);
		
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setWrapText(true);
		styles.put("cell_normal", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		styles.put("cell_normal_centered", style);

		return styles;
	}

	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}

	public String nextExportId() {
		return new BigInteger(130, random).toString(32);
	}

	@Override
	public String exportExcel(AuthUser authUser, List<List<Object>> criteriaList) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			toExcel(os, authUser, criteriaList);
			final String exportId = nextExportId();
			exportCache.put(exportId, os.toByteArray());
			scheduleCleanJob(exportId);
			return exportId;
		} catch (IOException e) {
			logger.error("Error occured when exporting to excel", e);
			return null;
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.error("Error occured when closing output stream", e);
			}
		}
	}
	
	protected void scheduleCleanJob(final String exportId) {
		Runnable cleanTask = new Runnable() {
			@Override
			public void run() {
				if (exportCache.containsKey(exportId))
					exportCache.remove(exportId);
			}
		};
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 15);
		
		taskScheduler.schedule(cleanTask, calendar.getTime());
	}

	@Override
	public byte[] getExport(String exportId) {
		byte[] export = null;
		if (exportCache.containsKey(exportId))
		{
			export = exportCache.get(exportId);
			exportCache.remove(exportId);
		}
		return export;
	}
	
	

}
