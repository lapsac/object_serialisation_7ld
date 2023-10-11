
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

//================================================

class Student implements Serializable{
	public String name;
	public String surname;
	public int atz1, atz2, atz3;
	
	public Student(String name, String surname, int atz1, int atz2, int atz3) {
		this.name = name;
		this.surname = surname;
		this.atz1 = atz1;
		this.atz2 = atz2;
		this.atz3 = atz3;
	}
	
	public void print(int numurs) {
		System.out.printf("\n%-4d%-15s%-15s%-12d%-12d%-12d", numurs, name, surname, atz1, atz2, atz3);
	}
}

//================================================

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	static String filename = "Students.dat";

	public static void main(String[] args) {
		int choise;
		String choiseStr;

		loop: while (true) {

			System.out.println("\n1) Create");
			System.out.println("2) Calculate");
			System.out.println("3) View");
			System.out.println("4) About");
			System.out.println("5) Exit");
			System.out.print("\nInput number from 1 till 5: ");
			
			choiseStr = sc.nextLine();
			
			try {
				choise = Integer.parseInt(choiseStr);
				if (choise < 1 || choise > 5) {
					throw new Exception();
				}
			}
			catch (Exception ex) {
				System.out.println("Error, please, input number from 1 till 5");
				continue;
			}
			

			switch (choise) {
			case 1:
				create();
				break;
			case 2:
				calculate();
				break;
			case 3:
				view();
				break;
			case 4:
				about();
				break;
			case 5:
				break loop;
			}
		}

		sc.close();
	}

	public static void create() {
		Student student;
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			
			student = new Student("Andris", "Liepiņš", 5, 4, 3);
			out.writeObject(student);
			
			student = new Student("Maris", "Riekstiņš", 9, 9, 9);
			out.writeObject(student);
			
			student = new Student("Pēteris", "Ozols", 8, 7, 8);
			out.writeObject(student);
			
			student = new Student("Mara", "Liepa", 4, 2, 9);
			out.writeObject(student);
			
			student = new Student("Inga", "Kļaviņa", 7, 7, 7);
			out.writeObject(student);
			
			out.close();
			
			System.out.println("\nFile " + filename + " succesfully created");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}

	public static void calculate() {
		File f = new File(filename); // input file

			System.out.print("\nEnter students number: ");
			int kartasNr = sc.nextInt();
			sc.nextLine();
			
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.dat"));
			
				Student s;
				boolean EOF = false; //def End Of file
				int kartasNrNext = 1;
			
				while (!EOF) { // while not End of file
					try {
						s = (Student) in.readObject();
						if (kartasNrNext == kartasNr) {
							// found the student, update the grades
							System.out.print("Enter new grades " + s.name + " " + s.surname + " (separated with space): ");
							int atz1 = sc.nextInt();
							int atz2 = sc.nextInt();
							int atz3 = sc.nextInt();
							s.atz1 = atz1;
							s.atz2 = atz2;
							s.atz3 = atz3; 
						}
            
						out.writeObject(s);
						kartasNrNext++;
            
					} catch (EOFException e) {
						EOF = true;
					}
				}
			
				in.close();
				out.close();
			
				f.delete();
				File newFile = new File("temp.dat");
				newFile.renameTo(f);
			
				if (kartasNr < 1 || kartasNr >= kartasNrNext) {
					System.out.println("no such student");
				} else {
					System.out.println();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
	}

	public static void view() {
		File f = new File (filename);
		if (!f.exists()) {
			System.out.println("Faila " + filename + " nav, lūdzu sākumā izpildiet komandu \"Create\"");
			return;
		}
		
		System.out.println("\n-----------------------------------------------------------------------");
		System.out.printf("#   %-15s%-15s%-12s%-12s%-12s", "Vārds", "Uzvārds", "Matemātika", "Fizika", "Programmēšana");
		System.out.print("\n-----------------------------------------------------------------------");
		
		int numurs = 1;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			
			Student s;
		    boolean EOF = false;
		    
		    while (!EOF) {
		        try {
		            s = (Student) in.readObject();
		            s.print(numurs++);
		        } 
		        catch (EOFException e) {
		            EOF = true;
		        }
		    }
		    
		    in.close();  	
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("\n-----------------------------------------------------------------------");
	}

	public static void about() {
		// TODO insert information about authors
		System.out.println("");
	}
}

//================================================