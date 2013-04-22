package ru.hh.school.webexample.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ru.hh.school.webexample.entity.User;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Session session = null;
		List<User> users = null;
		try{
			session = sessionFactory.openSession();
			users = session.createQuery("from User").list();
		} catch(HibernateException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} catch(Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		} finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		
		final OutputStreamWriter osw = new OutputStreamWriter(resp.getOutputStream(), Charset.forName("UTF-8"));
		for(User user : users){
			osw.write("\n" + user.toString());
		}
		osw.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}



}
