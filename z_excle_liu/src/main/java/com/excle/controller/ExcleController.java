package com.excle.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.excle.service.ExcleService;

@Controller
@RequestMapping("/")
public class ExcleController {
	
	private static Logger logger = Logger.getLogger(ExcleController.class);
	@Autowired
    ExcleService excleService;
	
	@RequestMapping("greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	//文件上传相关代码
	@RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    
    public String upload(@RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E://Excle//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            String str =excleService.updFile();
            return str;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "上传失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        } catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
    }
	 //多文件上传
    @RequestMapping(value = "batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                    excleService.updFile();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }
	/**
	 * 导出excle 模板
	 * 
	 * */
	@RequestMapping(value="download")
	public String downExcle() {		   
		try {
			return excleService.downExcle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	
	
	   /** 
     * 首页 
     * @return 
     */  
    @RequestMapping("system/index")  
    public String page(){  
        return "system/index";  
    }  
    
    /** 
     * 跳转 
     * @return 
     */  
    @RequestMapping("redirect")  
    public String page2(){  
        return "redirect/redirect";  
    }  
	
	 /** 
     *视图 
     * @param model 
     * @return 
     */  
    @RequestMapping("model")  
    public String page3(Model model){  
        model.addAttribute("name","seawater");  
        return "hello";  
    }
    @RequestMapping("login")  
    public String page0(){  
        return "login";  
    }
   
}
