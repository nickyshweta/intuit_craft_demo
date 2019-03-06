package com.intuit.craft.beans;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author nicky
 * Entity class to store tweet and its owner
 *
 */
@Entity
@Table(name = "Tweet")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tweet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private Long userId;
	
	private Timestamp publishDate;

	
	
	public Tweet(){}
	public Tweet(Long id, String content, Long userId,Timestamp publishDate) {
		super();
		this.id = id;
		this.content = content;
		this.userId = userId;
		this.publishDate = publishDate;
	}

	public Timestamp getPublishDate() {
		return publishDate;
	}

	@PrePersist
	public void setPublishDateonCreate() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		this.publishDate = timestamp;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the user_id
	 */
	public Long getUserId() {
		return userId;
	}

	
	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", content=" + content + ", user_id=" + userId + ", publishDate=" + publishDate
				+ "]";
	}
	
	
	

}
