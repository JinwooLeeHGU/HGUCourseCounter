package edu.handong.analysis.datamodel;

import org.apache.commons.csv.CSVRecord;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;

	public Course(CSVRecord csvRecord) {
		studentId = csvRecord.get(0).trim();
     	yearMonthGraduated = csvRecord.get(1).trim();
     	firstMajor = csvRecord.get(2).trim();
     	secondMajor= csvRecord.get(3).trim();
     	courseCode= csvRecord.get(4).trim();
     	courseName= csvRecord.get(5).trim();
     	courseCredit= csvRecord.get(6).trim();
     	yearTaken= Integer.parseInt(csvRecord.get(7).trim());		            	
     	semesterCourseTaken= Integer.parseInt(csvRecord.get(8).trim());	
	}
		
		public String getStudentId() {
			return studentId;
		}

		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}

		public String getYearMonthGraduated() {
			return yearMonthGraduated;
		}

		//public void setYearMonthGraduated(String yearMonthGraduated) {
		//	this.yearMonthGraduated = yearMonthGraduated;
		//}

		//public String getFirstMajor() {
		//	return firstMajor;
		//}

		//public void setFirstMajor(String firstMajor) {
		//	this.firstMajor = firstMajor;
		//}

		//public String getSecondMajor() {
		//	return secondMajor;
		//}

		//public void setSecondMajor(String secondMajor) {
		//	this.secondMajor = secondMajor;
		//}

		public String getCourseCode() {
			return courseCode;
		}

		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		//public String getCourseCredit() {
		//	return courseCredit;
		//}

		//public void setCourseCredit(String courseCredit) {
		//	this.courseCredit = courseCredit;
		//}

		public int getYearTaken() {
			return yearTaken;
		}

		public void setYearTaken(int yearTaken) {
			this.yearTaken = yearTaken;
		}

		public int getSemesterCourseTaken() {
			return semesterCourseTaken;
		}

		public void setSemesterCourseTaken(int semesterCourseTaken) {
			this.semesterCourseTaken = semesterCourseTaken;
		}
}