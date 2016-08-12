package com.lawyer.system.develop.service.serviceengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.cores.tools.other.ExceptionUtils;
import com.lawyer.system.develop.service.utils.DevelopContents;

public class ServiceEngineService {

	private static Logger logger = LoggerFactory.getLogger(ServiceEngineService.class);
	
	/**
	 * xml ui转换方法
	 * 
	 * @param fileOrFolder
	 *            文件或文件夹
	 */
	public void engine(ServiceElementKit serviceElementKit, String destPath,
			String templePath,  String fileName) {
		
		File destFile = new File(destPath);
		analysisEngine( serviceElementKit,destFile, templePath, fileName);

	}

	/**
	 * velocity转换
	 * 
	 * @param file
	 */
	private void analysisEngine(ServiceElementKit serviceElementKit,File destFile, String templePath,
			String fileName) {

		FileOutputStream fos = null;
		BufferedWriter writer = null;

		try {

			// 初始化参数
			Properties properties = new Properties();
			properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			// 设置velocity资源加载方式为class
			properties.setProperty("resource.loader", "class");
			// 设置velocity资源加载方式为file时的处理类
			properties
					.setProperty("class.resource.loader.class",
							"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			
			// 实例化一个VelocityEngine对象
			VelocityEngine velocityEngine = new VelocityEngine(properties);

			// 实例化一个VelocityContext
			VelocityContext context = new VelocityContext();
			
			context.put("serviceElementKit", serviceElementKit);

			File outFile = new File(destFile.getPath() + File.separator
					+ fileName);

			fos = new FileOutputStream(outFile);
			writer = new BufferedWriter(new OutputStreamWriter(fos,
					DevelopContents.ENCODE_UTF8));// 设置写入的文件编码,解决中文问题

			velocityEngine.mergeTemplate(templePath, DevelopContents.ENCODE_UTF8, context, writer);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.printDetailException(e));
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (Exception ee) {
					logger.error(ExceptionUtils.printDetailException(ee));
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(ExceptionUtils.printDetailException(e));
				}
			}
		}
	}
}
