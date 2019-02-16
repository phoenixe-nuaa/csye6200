/**
* A file for implementing CSYE 6200 Assignment 1
* Filename: Student.java
* NUID: 001466077
* @author Jialin Chen
*/

// Student class
public class Student {

	private String  name;
	private Double x_region;
	private Double  y_region;
	private Integer grade;

	
	// Test-Print student info
	public static void main(String[] args) {
		
		System.out.println("\n\t\t Print Student Info");
		
		Student stu1 = new Student("Ross", 1234.3, 2293.2, 3);
		stu1.show();
		
		Student stu2 = new Student("Rachel", 110.3, 432.2, 6);
		stu2.show();
		
		Student stu3 = new Student("Monica", 1234.2, 61234.23, 2);
		stu3.show();
	}
	
	
	// show method
	public void show() {
		System.out.println(" Name: " + this.getName() +
				           " Region X: " + this.getX() + 
				           " Region Y:" +  this.getY() +
				           " Grade: " + this.getGrade() );
	}
	
	
	// Constructor
	public Student(String name, Double x_region, Double y_region, Integer grade) {
//		super();
		this.name = name;
		this.x_region = x_region;
		this.y_region = y_region;
		this.grade = grade;
	}

	
	// Name 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	// Region X
	public Double getX() {
		return x_region;
	}

	public void setX(Double x_region) {
		this.x_region = x_region;
	}

	
	// Region Y
	public Double getY() {
		return y_region;
	}

	public void setY(Double y_region) {
		this.y_region = y_region;
	}

	// Grade
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
