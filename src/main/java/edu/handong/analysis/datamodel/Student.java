package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록
	private HashMap<String,Integer> semestersByYearAndSemester; 
	                                                         // key: Yearprivate Semester
	                                                         // e.g., 2003private 1, 
	public Student(String studentId) {
		this.studentId = studentId; // constructor
		// coursesTaken = new ArrayList<Course>();
		// semestersByYearAndSemester = new HashMap<String, Integer>();
	}
	/*public String getStudentId(){
		return this.studentId;
	}*/
	
	public void addCourse(Course newRecord) {
		coursesTaken = new ArrayList<Course>(); // initialization!! 
		coursesTaken.add(newRecord);
	}
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		int semester = 1;
		for(Course course : coursesTaken) {
			String temp1 = Integer.toString(course.getYearTaken());
			String temp2 = Integer.toString(course.getSemesterCourseTaken());
			String temp3 = temp1 + "-" + temp2;
			
			if(!semestersByYearAndSemester.containsKey(temp3)) {
				semestersByYearAndSemester.put(temp3, semester);
				semester++;
			}
		}
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSemester(int semester) {
		int count = 0;
		for(Course course : coursesTaken) {
			String temp1 = Integer.toString(course.getYearTaken());
			String temp2 = Integer.toString(course.getSemesterCourseTaken());
			String temp3 = temp1 + "-" + temp2;
			
			if(semester == semestersByYearAndSemester.get(temp3)) {
				count++;
			}	
		}	
		return count;
	}
	/* field에 대한 getter setter 필요에 따라 추가 */

}
