package com.lawyer.system.develop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lawyer.cores.tools.jackson.JacksonUtil;
import com.lawyer.system.develop.service.EngineContext;
import com.lawyer.system.develop.service.PowerDesignerEngineService;
import com.lawyer.system.develop.vo.PdmProject;
import com.lawyer.system.develop.vo.Table;
@Controller
@RequestMapping(value = "/autoDevelop")
public class AutoDevelopController {
	private static Logger logger = LoggerFactory.getLogger(AutoDevelopController.class);
	
	@Autowired
	private PowerDesignerEngineService powerDesignerEngineService;
	private static final String BASE_PATH="/fileResource/developCode/";
	
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping(value = "toCodeBuild" ,method = RequestMethod.GET)
	public String toCodeBuild() {
		return "com/lawyer/system/develop/codeBuild";
	}
	
	/**
	 * 上传PowerDesigner文件
	 * @param model
	 * @param myfile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadPDM" ,method = RequestMethod.POST)
	public String uploadPDM(Model model,MultipartFile myfile, HttpServletRequest request) {
		long size = myfile.getSize();
		if(size == 0){
			return toCodeBuild();
		}else{
			try {
				String root=request.getSession().getServletContext().getRealPath("/");
				File targetFile = new File(root+BASE_PATH, UUID.randomUUID()+"."+myfile.getOriginalFilename().split("\\.")[1]);
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), targetFile);
				String fileName = targetFile.getName();
				return parsePDM(model,fileName,request);
				//return fileName;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * 解析powerDesigner文件
	 * @param model
	 * @param fileName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/parsePDM" ,method = RequestMethod.POST)
	public String parsePDM(Model model,String fileName, HttpServletRequest request) {
		String root=request.getSession().getServletContext().getRealPath("/");
		String file = root+BASE_PATH+File.separator+fileName;
		List<PdmProject> list = powerDesignerEngineService.parsePDM(file);
		
		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
		for (PdmProject p : list) {
			for (Table t : p.getTbList()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("projectName", p.getName());
				map.put("name", t.getName());
				map.put("code", t.getCode());
				map.put("comment", t.getComment());
				l.add(map);
			}
		}
		model.addAttribute("pdmFile", fileName);
		model.addAttribute("list", new JacksonUtil().getJson(l));
		model.addAttribute("res", l);
		return "com/lawyer/system/develop/codeBuild";
	}
	
	/**
	 * 生成代码，并提供下载地址
	 * @param packagePath
	 * @param fileName
	 * @param tableNames
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/engine" )
	public String engine(String packagePath,String fileName,String tableNames, HttpServletRequest request){
		String root=request.getSession().getServletContext().getRealPath("/");
		String powerdesignerFile =root+ BASE_PATH + File.separator + fileName;
		String tartgetPath = root+BASE_PATH;
		List<PdmProject> list = powerDesignerEngineService.parsePDM(powerdesignerFile);
		
		String[] arr = tableNames.split(",");
		List<Table> tbList  = new ArrayList<Table>();
		
		for (String code : arr) {
			for (PdmProject p : list) {
				for (Table t : p.getTbList()) {
					if (t.getCode().equals(code)) {
						tbList.add(t);
					}
				}
			}
		}
		String downLoadPath = EngineContext.engine(tbList, packagePath, powerdesignerFile, tartgetPath);
		return new File(downLoadPath).getName();
	}
	
	/**
	 * 下载
	 * @param request
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,String fileName) throws IOException {
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root+ BASE_PATH + fileName;
		
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}
}
