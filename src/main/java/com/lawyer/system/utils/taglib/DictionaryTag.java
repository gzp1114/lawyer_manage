package com.lawyer.system.utils.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.lawyer.system.utils.SystemContents;

public class DictionaryTag extends SimpleTagSupport {


	private int value = 0;
	private String type = "";

	@Override
	public void doTag() throws JspException, IOException {

        this.getJspContext().getOut().write(SystemContents.getDicDesc(type,value));  
        
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
