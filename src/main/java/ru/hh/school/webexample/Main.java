package ru.hh.school.webexample;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ru.hh.school.webexample.entity.Department;
import ru.hh.school.webexample.entity.User;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		SessionFactory factory = new Configuration().configure(new File("D:\\java\\workspace_ejb\\web-example\\src\\main\\webapp\\WEB-INF\\classes\\hibernate.cfg.xml")).buildSessionFactory();;
		Session sess = factory.openSession();
		
		Transaction tr = sess.beginTransaction();
		User user = new User();
		user.setFirstName("name");
		user.setLastName("last");
		
		Department dept = new Department();
		dept.setName("dep1");
		System.out.println(sess.save(dept));
		
		user.setDept(dept);
		System.out.println(sess.save(user));
		
		sess.flush();
		
		tr.commit();
		
	}

}
