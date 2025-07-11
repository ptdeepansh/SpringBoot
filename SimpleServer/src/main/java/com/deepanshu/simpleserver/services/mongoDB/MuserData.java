package com.deepanshu.simpleserver.services.mongoDB;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GG")
public class MuserData {
	@Id
	private String id;
	
	private String title;
	private String name;
	private String erp;
	private Double contact_no;
	
	public MuserData(){}
	
	public MuserData(String title, String name, String erp, Double phone) {
		this.title = title;
		this.name = name;
		this.erp = erp;
		this.contact_no = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErp() {
		return erp;
	}

	public void setErp(String erp) {
		this.erp = erp;
	}

	public Double getContact_no() {
		return contact_no;
	}

	public void setContact_no(Double contact_no) {
		this.contact_no = contact_no;
	}
}
