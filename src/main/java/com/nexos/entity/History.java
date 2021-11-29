package com.nexos.entity;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "history")
public class History {

	@Id
	@Column(name = "id")
	private String id;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User id_user;
	@Column(name = "date")
	private Date date;
	@Column(name = "commentary")
	private String commentary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

}