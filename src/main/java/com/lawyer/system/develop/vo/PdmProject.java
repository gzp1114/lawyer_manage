package com.lawyer.system.develop.vo;

import java.util.ArrayList;
import java.util.List;

public class PdmProject {
	private String name;
	private List<Table> tbList = new ArrayList<Table>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Table> getTbList() {
		return tbList;
	}

	public void setTbList(List<Table> tbList) {
		this.tbList = tbList;
	}

}
