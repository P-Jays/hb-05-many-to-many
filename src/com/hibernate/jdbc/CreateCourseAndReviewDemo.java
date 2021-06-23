package com.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.demo.entity.Course;
import com.hibernate.jdbc.demo.entity.Instructor;
import com.hibernate.jdbc.demo.entity.InstructorDetail;
import com.hibernate.jdbc.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
		
			int id = 1;
			
			System.out.println("Creating courses and Review");
			
			session.beginTransaction();
			
			Course tempCourse = new Course("the course needs review");
			
			Review review= new Review("it is good");
			
			Review review2= new Review("it is good too");
			
			Review review3= new Review("it is good but ...");
			
			tempCourse.addReview(review);
			
			tempCourse.addReview(review2);
			
			tempCourse.addReview(review3);
			
			session.save(tempCourse);
			
			session.getTransaction().commit();
			
			System.out.println("done");
			
			
			
		}
		finally {
			factory.close();
		}
		
	}

}
