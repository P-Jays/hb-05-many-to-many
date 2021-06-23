package com.hibernate.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.jdbc.demo.entity.Course;
import com.hibernate.jdbc.demo.entity.Instructor;
import com.hibernate.jdbc.demo.entity.InstructorDetail;
import com.hibernate.jdbc.demo.entity.Review;
import com.hibernate.jdbc.demo.entity.Student;

public class CreateCourseAndStudentDemo {

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
			
			System.out.println("Creating courses and Students");
			
			session.beginTransaction();
			
			// create course
			
			Course tempCourse = new Course("the course needs students");
			
			// save course
			
			session.save(tempCourse);
			
			// create students
			
			Student tempStudent1 = new Student("PJ","Prajnawi","pj@email.com");
			
			Student tempStudent2 = new Student("Love","Metta","metta@email.com");
			
			// add student to course
			
			tempCourse.addStudents(tempStudent1);
			
			tempCourse.addStudents(tempStudent2);
			
			// save students
			
			session.save(tempStudent1);
			
			session.save(tempStudent2);
			
			session.getTransaction().commit();
			
			System.out.println("save students "+tempCourse.getStudents());
			
			
			
		}
		finally {
			factory.close();
		}
		
	}

}
