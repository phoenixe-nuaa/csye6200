package edu.neu.csye6200.enumT;

public class EnumTest {
	
	enum WeekDay {SUNDAY(0), MONDAY(10), TUESDAY(20), WEDNESDAY(30), THURSDAY(40), FRIDAY(50), SATURDAY(60);
		
	private int value;
		
	// Constructor
	WeekDay(int n) {value = n;}
	// Getter and Setter
	public void setValue(int value) {this.value = value;}
	public int getValue() {return value;}
	};
	
	private WeekDay currDay = WeekDay.TUESDAY;
	
	// Constructor
	public EnumTest() {
		System.out.println("Current day is: " + currDay + " ordinal value " + currDay.ordinal());
		
		// static 获取枚举类中的所有变量，并作为数组返回
		WeekDay days[] = WeekDay.values();
		for (WeekDay d : days) {
			System.out.println("Day: " + d.name() + " ordinal: " + d.ordinal() + " value: " + d.getValue());
		}
		setCurrDay(WeekDay.SATURDAY);
		setCurrDay(WeekDay.FRIDAY);
		
		// valueOf()
		WeekDay select;
		select = WeekDay.valueOf("FRIDAY");
		System.out.println("Selected: " + select);
	}
	
	public WeekDay getCurrDay() {
		return currDay;
	}

	public void setCurrDay(WeekDay currDay) {
		this.currDay = currDay;
	}

	public static void main(String[] args) {
		// create a class extends from java.lang.Enum
		EnumTest et = new EnumTest();
	}
}
