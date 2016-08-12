package com.lawyer.system.develop.service.daoengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.cores.tools.other.ExceptionUtils;
import com.lawyer.system.develop.service.utils.DevelopContents;

public class DaoEngineService {
	
	private static Logger logger = LoggerFactory.getLogger(DaoEngineService.class);

	/**
	 * xml ui转换方法
	 * 
	 * @param fileOrFolder
	 *            文件或文件夹
	 */
	public void engine(String fileOrFolder, String destFolder,
			String templePath, String fileName, String tableName,
			String packagePath) {
		File file = new File(fileOrFolder);
		File destFile = new File(destFolder);
		analysisEngine(file, destFile, templePath, fileName, tableName,packagePath);

	}

	/**
	 * velocity转换
	 * 
	 * @param file
	 */
	private void analysisEngine(File file, File destFile, String templePath,
			String fileName, String tableName, String packagePath) {

		FileOutputStream fos = null;
		BufferedWriter writer = null;

		SAXBuilder builder;
		Document root = null;

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

			try {
				builder = new SAXBuilder();
				root = builder.build("file:///"+file.getAbsolutePath());
			} catch (Exception ee) {
				logger.error(ExceptionUtils.printDetailException(ee));
				return;
			}

			context.put("root", root);
			context.put("tableService", new TableFieldKit(tableName,
					packagePath));

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
			builder = null;
			root = null;
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
