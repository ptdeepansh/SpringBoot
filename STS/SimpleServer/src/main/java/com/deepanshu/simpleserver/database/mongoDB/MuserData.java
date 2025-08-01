package com.deepanshu.simpleserver.database.mongoDB;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class MuserData {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String name;
	
	private String state, email_id;
	private Integer total_match, total_kills, age;
	private Long phone_no;
	private Date joined_date;
	
	public MuserData(){}
	
	public MuserData(String name, String state, String email_id, Integer totalmatches, Integer totalkills, Long phone_no, Integer age, Date date) {
		this.name = name;
		this.state = state;
		this.email_id = email_id;
		this.total_match = totalmatches;
		this.total_kills = totalkills;
		this.phone_no = phone_no;
		this.age = age;
		this.joined_date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Integer getTotalmatches() {
		return total_match;
	}

	public void setTotalmatches(Integer totalmatches) {
		this.total_match = totalmatches;
	}

	public Integer getTotalkills() {
		return total_kills;
	}

	public void setTotalkills(Integer totalkills) {
		this.total_kills = totalkills;
	}

	public Long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(long phone) {
		this.phone_no = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getJoined_date() {
		return joined_date;
	}

	public void setJoined_date(Date joined_date) {
		this.joined_date = joined_date;
	}

}
