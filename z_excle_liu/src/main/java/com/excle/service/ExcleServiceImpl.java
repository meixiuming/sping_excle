package com.excle.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excle.dao.ExcleDao;
import com.excle.domain.Excle;
import com.excle.util.ReadExcelTest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service("excleService")
public class ExcleServiceImpl implements ExcleService{
	
	@Autowired
	private ExcleDao excleDao;
	
	private Excle excle=new Excle();

	public static String filePath = "E://Excle"; 	
	private static Logger logger = Logger.getLogger(ExcleServiceImpl.class);
	@Override
	@Transactional("masterTransactionManager")
	public String updFile() throws IOException {
		logger.info("開始寫入到數據庫");
		 // 获取Excel文档的路径  
		List<File> fileList = new ArrayList<File>();
		fileList = ReadFile(filePath);
		for(File file:fileList) {
			String file_path=file.getCanonicalPath();
			 ReadExcelTest helper = new ReadExcelTest();
			 try {
		        	List <String[]> codes= helper.readExcel(file_path);
		        	List list_no =new ArrayList();
		        	
		        		for (int i = 0; i < codes.size(); i++) {
		        			System.out.println("开始执行第"+i+"行");
		        			try {
		        				String no =codes.get(i)[0];
		        				list_no= excleDao.findByNo(no);
//		        				String smt =codes.get(i)[1];
//		        				String ffo =codes.get(i)[2];
//		        				String pratName =codes.get(i)[3];
//		        				String reqmTNo =codes.get(i)[4];
//		        			String reqmTTitle =codes.get(i)[5];
//		        				String procedureNo =codes.get(i)[5];
//		        				String procedureTitle =codes.get(i)[6];
//		        				String reg =codes.get(i)[7];
//		        				excleDao.saveOrUpdate(no,smt,ffo,pratName,reqmTNo,reqmTTitle,procedureNo,procedureTitle,reg);
//		        				if(null == list_no || list_no.size() ==0) {
//		        					excleDao.save(no,smt,ffo,pratName,reqmTNo,procedureNo,procedureTitle,reg);
//		        				}else {
//		        					excleDao.update(no, smt, ffo, pratName, reqmTNo,  procedureNo, procedureTitle, reg);
//		        				}
		        				excle.setNO(no);
		        				excle.setSmt(codes.get(i)[1]);
		        				excle.setFfo(codes.get(i)[2]);
		        				excle.setPartName(codes.get(i)[3]);
		        				excle.setReqmTNo(codes.get(i)[4]);
		        	
		        				excle.setProcedureNo(codes.get(i)[5]);
		        				excle.setProcedureTitle(codes.get(i)[6]);
		        				excle.setReg(codes.get(i)[7]);
		        				if(null == list_no || list_no.size() ==0) {
		        					excleDao.save(excle);
		        				}else {
		        					excleDao.update(excle);	
		        				}	
							} catch (Exception e) {
								logger.info("第"+i+"次打印出现问题"+e.getMessage());
								e.printStackTrace();
							}
		    			}  	        		
						
				} catch (Exception e) {
					e.printStackTrace();
					
				} 
		}
		return "上传成功！";
	}
	//读取给定路径文件夹中的xls或者xlsx文件
	private static List<File> ReadFile (String filePath) {  
        List<File> fileList = new ArrayList<File>();  
        File file = new File(filePath);  
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        if (files == null) {// 如果目录为空，直接退出  
            return null;  
        }  
        // 遍历，目录下的所有文件  
        for (File file_ : files) {  
            if (file_.isFile()) {  
            	String fileName = file_.getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                if("xls".equals(suffix)||"xlsx".equals(suffix)) {
                	fileList.add(file_);
                }   
            } else if (file_.isDirectory()) {  
                System.out.println(file_.getAbsolutePath());  
                ReadFile(file_.getAbsolutePath());  
            }  
        } 
        return  fileList;
    } 

	@Override
	public String downExcle()throws Exception {
		logger.info("从数据库中读取数据");
		List<Excle> excleList = excleDao.findAllUsers();
		WritableWorkbook book = null;  
		try {  
			 // 创建一个excle对象  
			 String fileName = "D://EXCLE/excle_liu.xls";
             File file=new File(fileName);
             if (!file.exists()) {
                 file.createNewFile();
             }
             //以fileName为文件名来创建一个Workbook
			 book = Workbook.createWorkbook(file);  
			 // 通过excle对象创建一个选项卡对象  
			 WritableSheet sheet = book.createSheet("sheet1", 0);  
            // 创建一个单元格对象 列 行 值  
//	             Label label = new Label(0, 2, "test");  
			 //要插入到的Excel表格的行号，默认从0开始
             Label labelNo= new Label(0, 0, "序号");//表示第
             Label labelSMT= new Label(1, 0, "SMT");
             Label labelFFO= new Label(2, 0, "FFO");
             Label labelPartName= new Label(3, 0, "Part Name");
             Label labelReqmtNo= new Label(4, 0, "Reqm't No.");
            
             Label labelProcedureNo= new Label(5, 0, "Procedure No.");
             Label labelProcedureTitle= new Label(6, 0, "Procedure Title");
             Label labelReg= new Label(7, 0, "Reg.");
             sheet.addCell(labelNo);
             sheet.addCell(labelSMT);
             sheet.addCell(labelFFO);
             sheet.addCell(labelPartName);
             sheet.addCell(labelReqmtNo);
//             sheet.addCell(labelReqmtTitle);
             sheet.addCell(labelProcedureNo);
             sheet.addCell(labelProcedureTitle);
             sheet.addCell(labelReg);

             for (int i = 0; i < excleList.size(); i++) {
                 
                 Label labelNo_i= new Label(0, i+1, excleList.get(i).getNO()+"");
                 Label labelSMT_i= new Label(1, i+1, excleList.get(i).getSmt());
                 Label labelFFO_i= new Label(2, i+1, excleList.get(i).getFfo());
                 Label labelPartName_i= new Label(3, i+1, excleList.get(i).getPartName()+"");
                 Label labelReqmtNo_i= new Label(4, i+1, excleList.get(i).getReqmTNo()+"");
//                 Label labelReqmtTitle_i= new Label(5, i+1, excleList.get(i).getReqmTTitle()+"");
                 Label labelProcedureNo_i= new Label(5, i+1, excleList.get(i).getProcedureNo()+"");
                 Label labelProcedureTitle_i= new Label(6, i+1, excleList.get(i).getProcedureTitle()+"");
                 Label llabelReg_i= new Label(7, i+1, excleList.get(i).getReg()+"");
                 sheet.addCell(labelNo_i);
                 sheet.addCell(labelSMT_i);
                 sheet.addCell(labelFFO_i);
                 sheet.addCell(labelPartName_i);
                 sheet.addCell(labelReqmtNo_i);
//                 sheet.addCell(labelReqmtTitle_i);
                 sheet.addCell(labelProcedureNo_i);
                 sheet.addCell(labelProcedureTitle_i);
                 sheet.addCell(llabelReg_i);
             }
            // 写入目标路径  
            book.write();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                book.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
		return "SUCCESS";
	}
	
	
}
