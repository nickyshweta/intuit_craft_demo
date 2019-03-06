/**
 * 
 */
package com.intuit.craft.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author nicky
 * Entity class to store relation between user and its follower
 *
 */

@Entity
@Table(name = "follower")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Follower {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long followerId; // User id of the follower id whom the logged in user is following
	
	
	//private Integer status;
	public Follower(){}

	public Follower(Long id, Long userId, Long followerId) {
		super();
		this.id = id;
		this.userId = userId;
		this.followerId = followerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long user_id) {
		this.userId = user_id;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long follower_id) {
		this.followerId = follower_id;
	}


	@Override
	public String toString() {
		return "Follower [id=" + id + ", user_id=" + userId + ", follower_id=" + followerId 
				+ "]";
	}

}
