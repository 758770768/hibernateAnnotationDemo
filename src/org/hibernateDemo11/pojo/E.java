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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(query = "from E e where e.eid=:id", name = "queryE")
public class E {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;
	@Column
	private String enmae;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "f_id")
	private Set<F> fs = new HashSet<>();

	public String getEnmae() {
		return enmae;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public void setEnmae(String enmae) {
		this.enmae = enmae;
	}

	public Set<F> getFs() {
		return fs;
	}

	public void setFs(Set<F> fs) {
		this.fs = fs;
	}

	public E(String enmae, Set<F> fs) {
		super();
		this.enmae = enmae;
		this.fs = fs;
	}

	public E() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "E [eid=" + eid + ", enmae=" + enmae + "]";
	}

}
