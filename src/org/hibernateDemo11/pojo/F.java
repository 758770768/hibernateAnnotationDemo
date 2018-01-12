package org.hibernateDemo11.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class F {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fid;
	@Column
	private String fname;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@ManyToMany(mappedBy = "fs", cascade = CascadeType.ALL)
	private Set<E> es = new HashSet<>();

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Set<E> getEs() {
		return es;
	}

	public void setEs(Set<E> es) {
		this.es = es;
	}

	public F(Integer fid, String fname) {
		super();
		this.fid = fid;
		this.fname = fname;
	}

	public F() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "F [fid=" + fid + ", fname=" + fname + "]";
	}

}
