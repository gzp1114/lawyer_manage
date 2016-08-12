package com.lawyer.system.develop.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lawyer.system.develop.vo.Column;
import com.lawyer.system.develop.vo.PdmProject;
import com.lawyer.system.develop.vo.Table;

@Service("powerDesignerEngineService")
public class PowerDesignerEngineService {
	private static Logger logger = LoggerFactory.getLogger(PowerDesignerEngineService.class);

	/**
	 * 解析PDM文件到table对象中
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PdmProject> parsePDM(String filePath) {
		try {
			List<PdmProject> pList = new ArrayList<PdmProject>();
			
			File file = new File(filePath);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement(); 
			
			Map<String, PdmProject> map = new HashMap<String, PdmProject>();
			List<Element> projects= root.element("RootObject").element("Children").element("Model").element("PhysicalDiagrams").elements("PhysicalDiagram");
			for (Element a : projects) {
				PdmProject p = new PdmProject();
				p.setName(a.element("Name").getText());
				pList.add(p);
				
				List<Element> tbs = a.element("Symbols").elements("TableSymbol");
				for (Element tb : tbs) {
					Element c = tb.element("Object").element("Table");
					map.put("ref_"+c.attribute("Ref").getValue(), p);
				}
			}
			
			
			List<Element> list = doc.selectNodes("//c:Tables//o:Table");
			for (Element table_s : list) {
				Table table = new Table();
				table.setCode(table_s.elementText("Code"));
				table.setName(table_s.elementText("Name"));
				table.setComment(table_s.elementText("Comment"));
				Element columns = table_s.element("Columns");
				List<Column> columnList = new ArrayList<Column>();
				Iterator<Element> cols = columns.elementIterator("Column");
				while (cols.hasNext()) {
					Element column = cols.next();
					Column column2 = new Column();
					column2.setCode(column.elementText("Code"));
					column2.setComment(column.elementText("Comment"));
					column2.setName(column.elementText("Name"));
					column2.setDataType(column.elementText("DataType"));
					columnList.add(column2);
				}
				table.setColumns(columnList);
				
				String tableid = table_s.attribute("Id").getValue();
				PdmProject p = map.get("ref_"+tableid);
				p.getTbList().add(table);
			}
			
			return pList;
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("powerDesigner文件解析失败");
			return null;
		}
	}
	
	public static void main(String[] args) {
		PowerDesignerEngineService a = new PowerDesignerEngineService();
		printSQL(a.parsePDM("E:\\统一运营平台数据模型.pdm").get(0).getTbList().get(0));
	}
	
	public static void printSQL(Table tb) {
		StringBuffer sb =new StringBuffer();
		sb.append("-- Table: "+tb.getCode()+"\n");
		sb.append("drop table if exists " + tb.getCode() + ";\n");
		sb.append("create table " + tb.getCode() + "\n");
		sb.append("(\n");
		
		List<Column> clist = tb.getColumns();
		for (int i = 0; i < clist.size(); i++) {
			Column c = clist.get(i);
			sb.append(c.getCode() + "\t\t" + c.getDataType()+ " comment '" + c.getComment()+"'");
			if (i < clist.size() - 1) {
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(");\n");
		sb.append("alter table " + tb.getCode() + " comment '" + tb.getComment() + "';");
		System.out.println(sb.toString()+"\n");
	}
}
