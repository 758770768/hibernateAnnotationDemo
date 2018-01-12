package org.hibernateDemo11.test;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernateDemo11.pojo.A;
import org.hibernateDemo11.pojo.B;
import org.hibernateDemo11.pojo.C;
import org.hibernateDemo11.pojo.D;
import org.hibernateDemo11.pojo.E;
import org.hibernateDemo11.pojo.F;
import org.junit.Before;
import org.junit.Test;

public class TestDemo {
	private SessionFactory sdf = null;

	/**
	 * oliver 2018年1月11日 hibernateDemo11 创建hibernate核心对象
	 */
	@Before
	public void before() {
		Configuration conf = new Configuration().configure("org/hibernateDemo11/cnf/hibernate.cfg.xml");
		sdf = conf.buildSessionFactory();
	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 onetooen添加
	 */
	@Test
	public void fn() {
		// System.out.println(123);
		A a = new A();
		B b = new B();

		a.setAge("100");
		a.setB(b);
		a.setName("aoliver");

		b.setA(a);
		b.setName("boliver");
		b.setSex("male");

		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(a);
		tx.commit();
	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 onetoone链接
	 */
	@Test
	public void fn1() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		A a = session.get(A.class, 1);
		System.out.println(a.getB().getName());

	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 测试manytoone链接
	 */
	@Test
	public void fn2() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		C c = new C();
		D d = new D();

		c.setCname("c");
		c.setCsex("csex");
		c.getD().add(d);

		d.setC(c);
		d.setDname("d");
		d.setDsex("dsex");

		session.save(c);
		tx.commit();

	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 测试onetomany懒加载
	 */
	@Test
	public void fn3() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		C c = session.get(C.class, 1);
		System.out.println(c.getD().size());
	}

	@Test
	public void fn4() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		D d = session.get(D.class, 1);
		System.out.println(d.getC().getCname());
	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 多对多添加
	 */
	@Test
	public void fn5() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		E e = new E();
		F f = new F();

		e.setEnmae("e");
		e.getFs().add(f);
		f.setFname("f");
		f.getEs().add(e);
		session.save(f);
		tx.commit();

	}

	/**
	 * oliver 2018年1月11日 hibernateDemo11 多对多懒加载
	 */
	@Test
	public void fn6() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		E e = session.get(E.class, 1);
		System.out.println(e.getFs().size());
	}

	/**
	 * oliver 2018年1月12日 hibernateDemo11 hql查询
	 */
	@Test
	public void fn7() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from E e where e.eid=:eid");
		query.setInteger("eid", 1);
		List<E> ql = query.list();
		for (E e : ql) {
			System.out.println(e);
			System.out.println(e.getEnmae() + ":f的个数为：:" + e.getFs().size());
		}
	}

	/**
	 * oliver 2018年1月12日 hibernateDemo11
	 */
	@Test
	public void fn8() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select  e from E e  where e.eid=:eid");
		query.setInteger("eid", 1);
		List<E> ql = query.list();
		for (E e : ql) {
			System.out.println(e);
			System.out.println(e.getEnmae() + ":f的个数为：:" + e.getFs().size());
		}
	}

	/**
	 * oliver 2018年1月12日 hibernateDemo11 namedQuery
	 */
	@Test
	public void fn9() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query nquery = session.getNamedQuery("queryE");
		nquery.setInteger("id", 1);
		List<E> ql = nquery.list();
		for (E e : ql) {
			System.out.println(":集合个数:" + e.getFs().size());
		}
	}

	/**
	 * oliver 2018年1月12日 hibernateDemo11 关联查询属性
	 */
	@Test
	public void fn10() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select e.fs from E e");
		List<Object> ql = query.list();
		for (Object object : ql) {
			System.out.println(object);
		}

	}

	/**
	 * oliver 2018年1月12日 hibernateDemo11 hql数据封装为list
	 */
	@Test
	public void fn11() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select  new List(e.enmae) from E e");
		List ql = query.list();
		for (Object object : ql) {
			System.out.println(object);
		}
	}

	/**
	 * oliver 2018年1月12日 hibernateAnnotationDemo11 封装为map
	 */
	@Test
	public void fn12() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select  new Map(e.enmae) from E e");
		List ql = query.list();
		System.out.println(ql.size());
		for (Object object : ql) {
			System.out.println(object);
		}
	}

	/**
	 * oliver 
	 * 2018年1月12日 
	 * hibernateAnnotationDemo11 报错
	 */
	@Test
	public void fn13() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select e  from E e");
		List ql = query.list();
		System.out.println(ql.size());
		for (Object object : ql) {
			System.out.println(object);
		}
	}

	/**
	 * oliver 
	 * 2018年1月12日 
	 * hibernateAnnotationDemo11
	 */
	@Test
	public void fn14() {
		Session session = sdf.openSession();
		Transaction tx = session.beginTransaction();
		SQLQuery sQuery = session.createSQLQuery("select * from e where eid=:id");
		sQuery.setInteger("id", 1);
		sQuery.addEntity(E.class);
		List ql = sQuery.list();
		for (Object object : ql) {
			System.out.println(object);
		}
	}

}
