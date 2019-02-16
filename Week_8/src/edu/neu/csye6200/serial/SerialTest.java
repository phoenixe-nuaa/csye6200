package edu.neu.csye6200.serial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

// 如果我们想要序列化一个对象，首先要创建某些OutputStream(如FileOutputStream、ByteArrayOutputStream等)，然后将这些OutputStream封装在一个ObjectOutputStream中。
// 这时候，只需要调用writeObject()方法就可以将对象序列化，并将其发送给OutputStream（记住：对象的序列化是基于字节的，不能使用Reader和Writer等基于字符的层次结构）。
// 而反序列的过程（即将一个序列还原成为一个对象），需要将一个InputStream(如FileInputstream、ByteArrayInputStream等)封装在ObjectInputStream内，然后调用readObject()即可。
public class SerialTest {
	public void streamOut(CarData cardata, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cardata);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CarData streamIn(String filename) {
		CarData cardata = null;	
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			cardata = (CarData) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cardata;
	}

	public static void main(String[] args) {
		SerialTest serTest = new SerialTest();
		CarData carData = new CarData(1, 1.0, 2.0,"Chevy");
		serTest.streamOut(carData, "chevy.data");
		CarData carData2 = serTest.streamIn("chevy.data");
		System.out.println("carData2 is " + carData2.getName());
		// A list for holding car data
		ArrayList<CarData> carList = new ArrayList<CarData>();
		carList.add(new CarData(0, 1.0, 2.0,"Chevy"));
		carList.add(new CarData(1, 1.0, 2.0,"BMW"));
		carList.add(new CarData(2, 1.0, 2.0,"Land Rover"));
		carList.add(new CarData(3, 1.0, 2.0,"Audi"));
		carList.add(new CarData(4, 1.0, 2.0,"Mercedes"));
		carList.add(new CarData(5, 1.0, 2.0,"Ford"));
		// Use Collections package to sort a list
		// Collections.sort(list);会自动调用compareTo
		Collections.sort(carList);
	}
}
