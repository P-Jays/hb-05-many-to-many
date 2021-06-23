package com.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.demo.entity.Course;
import com.hibernate.jdbc.demo.entity.Instructor;
import com.hibernate.jdbc.demo.entity.InstructorDetail;
import com.hibernate.jdbc.demo.entity.Review;
import com.hibernate.jdbc.demo.entity.Student;

public class CreateCourseForStudentDemo {

	public static void main(String[] args) {
		
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
		
			int id = 1;
			
			System.out.println("Creating courses for Student");
			
			session.beginTransaction();
			
			Student tempStudent = session.get(Student.class, id);
			
			System.out.println(tempStudent);
			
			Course tempCourse2 = new Course("the second course for student");
			
			Course tempCourse3 = new Course("the third course for student");
			
			tempCourse2.addStudents(tempStudent);
			
			tempCourse3.addStudents(tempStudent);
			
			session.save(tempCourse2);
			
			session.save(tempCourse3);
			
			session.getTransaction().commit();
			
			System.out.println("the course is saved and the student get new courses");
			
			
			
		}
		finally {
			factory.close();
		}
		
	}

}
