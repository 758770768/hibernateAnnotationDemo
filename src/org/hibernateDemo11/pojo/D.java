package org.hibernateDemo11.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class D {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer did;
	@Column
	private String dname;
	@Column
	private String dsex;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_id")
	private C c;

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDsex() {
		return dsex;
	}

	public void setDsex(String dsex) {
		this.dsex = dsex;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}

}
