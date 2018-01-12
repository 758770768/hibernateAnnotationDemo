package org.hibernateDemo11.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class C {

	private Integer cid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	private String cname;
	private String csex;
	private Set<D> d = new HashSet<>();

	@Column
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column
	public String getCsex() {
		return csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	@OneToMany(mappedBy = "c",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<D> getD() {
		return d;
	}

	public void setD(Set<D> d) {
		this.d = d;
	}

}
