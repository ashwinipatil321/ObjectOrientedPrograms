
package com.bridgelabz.objectoriented.utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Utility
{
	BufferedReader br;
	public Utility(){
		br = new BufferedReader(new InputStreamReader(System.in));

	}
	static Scanner scanner = new Scanner(System.in);
	public final static String REGEX_NAME = "<<name>>";
	public final static String REGEX_FULLNAME = "<<full name>> ";
	public final static String REGEX_MOBILE_NO = "xxxxxxxxxx";
	public final static String REGEX_DATE = "01/01/2016";
	private FileWriter fileWriter;

	//Regular Expression Demonstration Program
	/**regrex
	 * @param firstName
	 * @param lastName
	 * @param mobileNumber
	 * @param message
	 * @return
	 */
	public static String convertString(String firstName,String lastName,String mobileNumber,String message)
	{
		Pattern p = Pattern.compile(REGEX_NAME);
		Matcher m = p.matcher(message); 
		message = m.replaceAll(firstName);

		p = Pattern.compile(REGEX_FULLNAME);
		m = p.matcher(message); 
		message = m.replaceAll(firstName+" "+lastName);

		p = Pattern.compile(REGEX_MOBILE_NO);
		m = p.matcher(message); 
		message = m.replaceAll(mobileNumber);

		p = Pattern.compile(REGEX_DATE);
		m = p.matcher(message); 
		long mills=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(mills);	
		System.out.println(date);
		String dates=date.toString();
		message = m.replaceAll(dates);
		return message;
	}

	//Calculate Deck Of card Program
	/**calculateDeckOfCards
	 * 
	 */
	public void calculateDeckOfCards()
	{
		String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		int n = suits.length * ranks.length;
		String[] deck = new String[n];
		for (int i = 0; i < ranks.length; i++)
		{
			for (int j = 0; j < suits.length; j++) 
			{
				deck[suits.length*i + j] = ranks[i] + " of " + suits[j];
			}
		}
		for (int i = 0; i < n; i++) 
		{
			int r = i + (int) (Math.random() * (n-i));
			String temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
		for (int i = 0; i < n; i++) 
		{
			System.out.println(deck[i]);
		}
	}

	/**
	 * @param file
	 * @author
	 * <p> This method for writing data in to file
	 */
	@SuppressWarnings({ "unchecked" })
	public static void writeOnFile()
	{
		try {
			File file= new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/inventoryData.json");
			FileWriter fileWriter=new FileWriter(file);

			JSONObject products=new JSONObject();

			JSONObject jsonObject=new JSONObject();
			jsonObject.put("Name", "Sona Masuri");
			jsonObject.put("Weight", 50);
			jsonObject.put("Price", 55);

			JSONObject jsonObject1=new JSONObject();
			jsonObject1.put("Name", "wheat101");
			jsonObject1.put("Weight", 50);
			jsonObject1.put("Price", 45);

			JSONObject jsonObject2=new JSONObject();
			jsonObject2.put("Name", "Moong dal");
			jsonObject2.put("Weight", 50);
			jsonObject2.put("Price", 150);


			products.put("Rice", jsonObject);
			products.put("Wheat", jsonObject1);
			products.put("Pulses", jsonObject2);
			//System.out.println(products);
			fileWriter.write(JSONValue.toJSONString(products));
			fileWriter.flush();
			fileWriter.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**read data from file
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException
	 */
	public static void readOnFile() throws IOException, org.json.simple.parser.ParseException
	{
		File file= new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/inventoryData.json");
		FileReader fileReader=new FileReader(file);
		JSONParser parser=new JSONParser();
		JSONObject json=(JSONObject)parser.parse(fileReader);

		Iterator<?> iterator = json.keySet().iterator(); 

		while(iterator.hasNext()) 
		{
			String outerKey = (String) iterator.next();
			JSONObject jsonObject=(JSONObject)json.get(outerKey);
			Iterator<?> iterator1 = jsonObject.keySet().iterator(); 
			while(iterator1.hasNext())
			{
				String key=(String) iterator1.next();
				System.out.println(key+" : "+jsonObject.get(key));
			}
			System.out.println();
			System.out.println("The Toatal cost of "+jsonObject.get("Name")+" is: "
					+ Integer.parseInt(jsonObject.get("Price").toString()) *
					Integer.parseInt(jsonObject.get("Weight").toString())  );
			System.out.println("*****************************************");
		}
	}

	//Stock Account Program
	/**create User
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void createUser() throws Exception {

		JSONObject stock_User=new JSONObject();
		FileReader reader=new FileReader("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_details.json");
		String name;
		int number_Of_Share,amount;

		//Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the name of the user......");
		name=scanner.next();

		stock_User.put("user_Name",name);

		System.out.println("Enter the Number of Share");
		number_Of_Share=scanner.nextInt();
		stock_User.put("number_Share",number_Of_Share);


		System.out.println("Enter the amount......");
		amount=scanner.nextInt();
		stock_User.put("amount",amount);

		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
		boolean b=true;
		for(Object o : jsonArray)
		{
			JSONObject jsonDeatils = (JSONObject) o;
			String user_Name=(String)jsonDeatils.get("user_Name");
			if(user_Name.equals(name))
			{
				System.out.println("user Already Exsists.............");
				b=false;   
			}
		}
		if(b==true)
		{
			jsonArray.add(stock_User);
			FileWriter fileWriter=new FileWriter("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_details.json");
			fileWriter.write(jsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		}
	}
	/**buy items
	 * @throws IOException
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static void Buy() throws IOException, ParseException, org.json.simple.parser.ParseException
	{
		File file = new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_details.json");
		File file1 =new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_symbol.json");
		if(file.exists() && file1.exists())
		{
			Scanner scanner= new Scanner(System.in);
			// reading stock file
			FileReader fr = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONArray stock = (JSONArray) parser.parse(fr);
			//reading share file

			FileReader sf = new FileReader(file1);
			JSONParser parser1 = new JSONParser();
			JSONArray share = (JSONArray) parser1.parse(sf);

			System.out.println("Enter the user");
			String name = scanner.nextLine();
			Iterator<?> itr = stock.iterator();
			Iterator<?> itr1 = share.iterator();
			boolean flag = false;
			while (itr.hasNext())
			{
				JSONObject obj=(JSONObject) itr.next();
				if(obj.get("user_Name").equals(name))
				{
					System.out.println("Enter the share sysmbol to buy share:[@,!,#]");
					String sym = scanner.nextLine();
					while(itr1.hasNext())
					{
						JSONObject obj1 = (JSONObject) itr1.next();
						if(obj1.get("stock_Symbol").equals(sym))
						{   
							System.out.println("Enter the amount");
							int amt= scanner.nextInt();
							int bal =  Integer.parseInt(obj.get("amount").toString());
							int price = Integer.parseInt(obj1.get("amount").toString());
							int noShare =  Integer.parseInt(obj.get("number_Share").toString());
							int stockShare = Integer.parseInt(obj1.get("Count").toString());
							int numofshare = amt/price;
							int newbal = bal-amt;
							int sharecountcus = noShare+numofshare;
							int sharecountstock = stockShare-numofshare;
							obj.remove("amount");
							obj.remove("number_Share");
							obj1.remove("Count");
							long millis=System.currentTimeMillis(); 
							java.util.Date date=new java.util.Date(millis); 
							System.out.println("Transaction time is"+date); 
							obj.put("amount",newbal);
							obj.put("number_Share",sharecountcus);
							obj1.put("Count", sharecountstock);
							JSONArray jsonarray=new JSONArray();
							jsonarray.add(obj);
							System.out.println(jsonarray);
							flag= true;
							break;
						}
					}
				}
				FileWriter fs = new FileWriter(file);
				fs.write(JSONValue.toJSONString(stock));
				fs.flush();   
				fs.close();
			}
			if(flag == false)
			{
				System.out.println("User name not exits");
			}
			FileWriter fw = new FileWriter(file1);
			fw.write(JSONValue.toJSONString(share));
			fw.flush();
			fw.close();
		}
		else
		{
			System.out.println("File does not exits");
		}
	}

	/**shell items
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static void sell() throws Exception {
		File file = new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_details.json");
		File file1 =new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_symbol.json");
		if(file.exists() && file1.exists())
		{
			Scanner scanner = new Scanner(System.in);
			FileReader fr = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONArray stock = (JSONArray) parser.parse(fr);

			FileReader sf = new FileReader(file1);
			JSONParser parser1 = new JSONParser();
			JSONArray share = (JSONArray) parser1.parse(sf);

			System.out.println("Enter the user");
			String name = scanner.nextLine();
			Iterator<?> itr = stock.iterator();
			Iterator<?> itr1 = share.iterator();
			boolean flag = false;
			while (itr.hasNext())
			{
				JSONObject obj=(JSONObject) itr.next();
				if(obj.get("user_Name").equals(name))
				{
					System.out.println("Enter the share sysmbol to buy share:[@,!,#]");
					String sym = scanner.nextLine();

					while(itr1.hasNext())
					{
						JSONObject obj1 = (JSONObject) itr1.next();
						if(obj1.get("stock_Symbol").equals(sym))
						{   
							System.out.println("Enter the amount");
							int amt= scanner.nextInt();
							int bal =  Integer.parseInt(obj.get("amount").toString());
							int price = Integer.parseInt(obj1.get("amount").toString());
							int noShare =  Integer.parseInt(obj.get("number_Share").toString());
							int stockShare = Integer.parseInt(obj1.get("Count").toString());
							int numofshare = amt/price;
							int newbal = bal+amt;
							int sharecountcus = noShare-numofshare;
							int sharecountstock = stockShare+numofshare;
							obj.remove("amount");
							obj.remove("number_Share");
							obj1.remove("Count");
							long millis=System.currentTimeMillis(); 
							java.util.Date date=new java.util.Date(millis); 
							System.out.println("Transaction time is"+date); 
							obj.put("amount",newbal);
							obj.put("number_Share",sharecountcus);
							obj1.put("Count", sharecountstock);
							JSONArray jsonarray=new JSONArray();
							jsonarray.add(obj);
							System.out.println(jsonarray);
							flag= true;
							break;
						}
					}
				}
				FileWriter fs = new FileWriter(file);
				fs.write(JSONValue.toJSONString(stock));
				fs.flush();   
				fs.close();
			}
			if(flag == false)
			{
				System.out.println("User name not exits");
			}
			FileWriter fw = new FileWriter(file1);
			fw.write(JSONValue.toJSONString(share));
			fw.flush();
			fw.close();
		}
		else
		{
			System.out.println("File does not exits");
		}

	}
	/**save data
	 * @throws Exception
	 */
	public static void display() throws Exception
	{
		FileReader reader1=new FileReader("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stock_details.json");
		JSONParser jsonParser1 = new JSONParser();
		JSONArray jsonArrays_StackDtails = (JSONArray) jsonParser1.parse(reader1);
		for (Object o1 : jsonArrays_StackDtails)
		{
			JSONObject jsonDetails2 = (JSONObject) o1;
			String name=(String)jsonDetails2.get("user_Name");
			System.out.println("User Name:->"+name);

			Object share=jsonDetails2.get("number_Share");
			System.out.println("Number of share->"+share);

			Object amount=jsonDetails2.get("amount");
			System.out.println("Amount->"+amount);

			System.out.println("-----------------------------------------");
		}

	}

	// Clinique Management Program
	
	/**add Doctors
	 * 
	 */
	@SuppressWarnings("unchecked")
	public  void addDoctors() {
		System.out.println("Enter no. of doctors :");
		
		int num0fDoctor = scanner.nextInt();
		JSONArray array = new JSONArray();
		for (int i = 0; i < num0fDoctor; i++) {
			JSONObject json = new JSONObject();
			System.out.println("Enter name of doctor");
			String name = scanner.next();
			json.put("doctorName", name);
			System.out.println("Enter I.D of doctor");
			int doctorId = scanner.nextInt();
			json.put("doctorId", doctorId);
			System.out.println("Enter Specialization of doctor");
			String specilization = scanner.next();
			json.put("doctorSpecialization", specilization);
			System.out.println("Enter Availablity of doctor");
			String available = scanner.next();
			json.put("doctorAvailability", available);
			array.add(json);
		}
		try {
			System.out.println("Data has been uploaded :");
			fileWriter = new FileWriter("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/doctor.json");
			fileWriter.write(array.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println("Doctor Added:" + array);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**add Patient
	 * @throws IOException
	 */
	/**
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public  void addPatient() {
		System.out.println("Enter no. of Patient");
		int noOfPatient = scanner.nextInt();
		JSONArray array = new JSONArray();
		for (int i = 0; i < noOfPatient; i++) {
			JSONObject json1 = new JSONObject();
			System.out.println("Enter patient name :");
			String patientname = scanner.next();
			json1.put("patient_Name", patientname);
			System.out.println("Enter patient ID. :");
			int id = scanner.nextInt();
			json1.put("patientId", id);
			System.out.println("Enter patient age");
			int age = scanner.nextInt();
			json1.put("age", age);
			System.out.println("Enter patient mobile number :");
			long number = scanner.nextLong();
			json1.put("mobile_Number", number);
			
			array.add(json1);
		}
		try {
			System.out.println(" Patient data has been uploaded ...");
			fileWriter = new FileWriter("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/patient.json");

			fileWriter.write(array.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println("Pateint Added: " + array);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**search Doctor
	 * @throws Exception
	 */

	public void searchDoctor() {
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(new FileReader(
					"/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/doctor.json"));
			System.out.println("Search Doctor_Name :");
			String name = scanner.next();
			for (Object obj : array) {
				JSONObject object = (JSONObject) obj;
				String string = (String) object.get("doctorName");
				if (name.equals(string)) {
					System.out.println("Doctor_founded" + object);
				} else {
					System.out.println("Not Found !");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**search Patient
	 * 
	 */
	public void searchPatient() {
		System.out.println("Enter Name Of Patient");
		String name = scanner.next();
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(new FileReader(
					"/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/patient.json"));
			for (Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				String string = (String) jsonobject.get("patient_Name");
				if (name.equals(string)) {
					System.out.println("Patient_found " + jsonobject);
				} else {
					System.out.println("Not found !");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**dispalay Doctor information
	 * @throws Exception
	 */
	public  void displayDoctor() throws Exception
	{
		File file = new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/doctor.json");
		FileReader reader1 = new FileReader(file);
		JSONParser parser = new JSONParser();
		JSONArray jsonArrays = (JSONArray) parser.parse(reader1);
		for (Object o1 : jsonArrays)
		{
			JSONObject jsonDetails2 = (JSONObject) o1;
			String name=(String)jsonDetails2.get("doctorName");
			System.out.println("doctor Name:->"+name);
			Object doctorId=jsonDetails2.get("doctorId");
			System.out.println("doctor Id->"+doctorId);
			Object doctorSpecialization=jsonDetails2.get("doctorSpecialization");
			System.out.println("doctor Specialization->"+doctorSpecialization);
			Object doctorAvailability=jsonDetails2.get("doctorAvailability");
			System.out.println("doctor Availability->"+doctorAvailability);
			System.out.println("-----------------------------------------");
		}

	}
	/**display Patient information
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException
	 */
	public void displayPatient() throws IOException, org.json.simple.parser.ParseException
	{
		File file = new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/patient.json");
		FileReader reader1 = new FileReader(file);
		JSONParser parser = new JSONParser();
		JSONArray jsonArrays = (JSONArray) parser.parse(reader1);
		for (Object o1 : jsonArrays)
		{
			JSONObject jsonDetails2 = (JSONObject) o1;
			System.out.println(jsonDetails2);
			Object age=jsonDetails2.get("age");
			System.out.println("age->"+age);
			Object mobile_Number=jsonDetails2.get("mobile_Number");
			System.out.println("mobile_Number->"+mobile_Number);
			String name=(String)jsonDetails2.get("patient_Name");
			System.out.println("patient_Name:->"+name);
			Object patientId=jsonDetails2.get("patientId");
			System.out.println("patient Id>"+patientId);
			System.out.println("-----------------------------------------");
		}
	}
	
	public void doctorAppointment() {
		System.out.println("Please enter Patient_Name");
		String patient_name = scanner.next();
		System.out.println("Enter Doctor_Name for to take an Appointment");
		String doctername = scanner.next();
		System.out.println("Enter the date");
		String stringDate = scanner.next();

		String doctorInfo = null;

		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(new FileReader(
					"/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/doctor.json"));
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				String doctorName = (String) obj.get("doctorName");

				if (doctorName.equals(doctername)) {
					doctorInfo = doctorName;
				} else {
					System.out.println("doctors not found in this name");
					return;
				}
			}
			JSONArray appointmentFileObj = new JSONArray();

			JSONObject obj1 = new JSONObject();

			obj1.put("doctorName", doctorInfo);

			obj1.put("patient_Name", patient_name);
			obj1.put("BookingDate ", (stringDate));
			appointmentFileObj.add(obj1);
			FileWriter filewriter = new FileWriter(
					"/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/Appoinment.json");
			filewriter.write(appointmentFileObj.toJSONString());
			filewriter.flush();
			filewriter.close();
			System.out.println("hello " + patient_name + " Your Appointment is fixed  With Doctor " + doctorInfo
					+ " For: " + (stringDate));
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//AdressBook Program
	/**add a Person
	 * 
	 */

	@SuppressWarnings("unchecked")
	public static void addPerson(File file) {
		Scanner scanner=null;
		FileWriter fileWriter=null;

		scanner=new Scanner(System.in);
		try {
			System.out.println("Enter Your First Name: ");
			String userFirstName=scanner.nextLine();

			System.out.println("Enter your Last Name: ");
			String userLastName=scanner.nextLine();

			System.out.println("Enter your Address: ");
			String userAddress=scanner.nextLine();

			System.out.println("Enter your City: ");
			String userCity=scanner.nextLine();

			System.out.println("Enter your State: ");
			String userState=scanner.nextLine();

			System.out.println("Enter your ZIP: ");
			int userZIP=scanner.nextInt();

			System.out.println("Enter your Mobile No: ");
			long userMobileNumber=scanner.nextLong();

			FileReader fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			Object jsonArray= parser.parse(fileReader);
			//JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("FirstName",userFirstName);
			jsonObject.put("LastName", userLastName);
			jsonObject.put("Address", userAddress);
			jsonObject.put("City", userCity);
			jsonObject.put("State", userState);
			jsonObject.put("Zip", userZIP);
			jsonObject.put("MobileNumber", userMobileNumber);
			((ArrayList) jsonArray).add(jsonObject);
			try {
				fileWriter=new FileWriter(file);
				fileWriter.write(JSONValue.toJSONString(jsonArray));
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	/**
	 * edit Person
	 */
	@SuppressWarnings("unchecked")
	public static void editPerson(File file) {
		Scanner scanner=null;
		FileWriter fileWriter=null;
		FileReader fileReader=null;

		scanner=new Scanner(System.in);
		System.out.println("Enter name you want edit: ");

		try {
			String userEntry=scanner.nextLine();
			fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray=(JSONArray) parser.parse(fileReader);
			Iterator iterator=jsonArray.iterator();

			boolean isExist=false;
			while(iterator.hasNext()) {

				//     int key=(int) iterator.next();
				JSONObject jsonObject= (JSONObject)iterator.next();

				if(jsonObject.get("FirstName").equals(userEntry)) {

					System.out.println("Select what you want to edit: \n 1. Address \n2. City \n 3. State \n 4. ZIP \n 5. Mobile Number \n 6. Edit All ");
					int choice=scanner.nextInt();

					switch(choice){

					case 1:	 System.out.println("Enter your Address: ");
					scanner.nextLine();
					String userAddress=scanner.nextLine();

					// jsonObject.remove("Address");

					jsonObject.put("Address", userAddress);

					break;

					case 2 : System.out.println("Enter your City: ");
					scanner.nextLine();

					String userCity=scanner.nextLine();
					// jsonObject.remove("City");
					jsonObject.put("City", userCity);

					break;

					case 3 : System.out.println("Enter your State: ");
					scanner.nextLine();

					String userState=scanner.nextLine();
					// jsonObject.remove("State");
					jsonObject.put("State", userState);
					break;

					case 4 : System.out.println("Enter your ZIP: ");
					scanner.nextLine();

					int userZIP=scanner.nextInt();
					//	 jsonObject.remove("Zip");
					jsonObject.put("Zip", userZIP);

					break;

					case 5 : System.out.println("Enter your Mobile No: ");
					scanner.nextLine();

					long userMobileNumber=scanner.nextLong();
					// jsonObject.remove("MobileNumber");
					jsonObject.put("MobileNumber", userMobileNumber);

					break;

					case 6 :
						System.out.println("Enter your Address: ");
						scanner.nextLine();
						String userAddress1=scanner.nextLine();

						System.out.println("Enter your City: ");
						//scanner.nextLine();
						String userCity1=scanner.nextLine();

						System.out.println("Enter your State: ");
						// scanner.nextLine();
						String userState1=scanner.nextLine();

						System.out.println("Enter your ZIP: ");
						// scanner.nextLine();
						int userZIP1=scanner.nextInt();

						System.out.println("Enter your Mobile No: ");
						// scanner.nextLine();
						long userMobileNumber1=scanner.nextLong();	

						jsonObject.put("Address", userAddress1);
						jsonObject.put("City", userCity1);
						jsonObject.put("State", userState1);
						jsonObject.put("Zip", userZIP1);
						jsonObject.put("MobileNumber", userMobileNumber1);

						break;

					default:
						System.out.println("Wrong choice: ");
					}								
					fileWriter=new FileWriter(file);
					fileWriter.write(JSONValue.toJSONString(jsonArray));
					fileWriter.flush();
					fileWriter.close();
					System.out.println("Edited");
					isExist=true;

					break;
				} 

			}
			if (isExist==false) {
				System.out.println("User does not exist: ");
			}
		} catch ( IOException | org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**delete Person
	 * 
	 */
	@SuppressWarnings("resource")
	public static void deletePerson(File file) {
		Scanner scanner=null;
		FileWriter fileWriter=null;
		FileReader fileReader=null;

		scanner=new Scanner(System.in);
		System.out.println("Enter name you want delete: ");
		try {
			String userEntry=scanner.nextLine();
			fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray=(JSONArray) parser.parse(fileReader);
			Iterator iterator=jsonArray.iterator();
			boolean isExist=false;	
			while(iterator.hasNext()) {

				JSONObject jsonObject= (JSONObject) iterator.next();

				if(jsonObject.get("FirstName").equals(userEntry)) {

					jsonArray.remove(jsonObject);

					System.out.println("Deleted successfully: ");
					isExist=true;
					break;
				} 

			}

			fileWriter=new FileWriter(file);
			fileWriter.write(JSONValue.toJSONString(jsonArray));
			fileWriter.flush();
			fileWriter.close();
			if(isExist==false) {
				System.out.println("User does not exist: ");
			}
		} catch ( IOException | org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**sort Personal details
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void sortDetails(File file) {

		class SortDetails implements Comparator<Object> {
			String string=null;
			public SortDetails(String string) {
				this.string=string;
			}

			@Override
			public int compare(Object o1, Object o2) {
				JSONObject json1=(JSONObject)o1;  
				JSONObject json2=(JSONObject)o2;  

				return (json1.get(string).toString()).compareTo(json2.get(string).toString());
			}

		}

		FileReader fileReader=null;
		Scanner scanner=new Scanner(System.in);

		try {
			fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray=(JSONArray) parser.parse(fileReader);

			boolean check=true;
			do {
				System.out.println("Sort according to \n 1.First Name \n 2.Last Name \n 3. Address \n 4.City \n 5.State \n 6.Zip \n 7.Mobile Number  ");
				int choice=scanner.nextInt();
				switch(choice) {
				case 1 : 
					System.out.println("Sorting according to First Names: ");


					Collections.sort(jsonArray,new SortDetails("FirstName"));	

					Iterator nameIterator=jsonArray.iterator(); 

					while(nameIterator.hasNext()) {  
						JSONObject json=(JSONObject)nameIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")		
								);
					}  
					break;

				case 2 : //Last Name sorting
					System.out.println("Sorting according to Last Names: ");

					Collections.sort(jsonArray,new SortDetails("LastName"));	

					Iterator lastNameIterator=jsonArray.iterator();  

					while(lastNameIterator.hasNext()){  

						JSONObject json=(JSONObject)lastNameIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}  		 
					break;
				case 3: // Address sorting
					System.out.println("Sorting according to Address : ");
					Collections.sort(jsonArray,new SortDetails("Address"));	
					Iterator addressIterator=jsonArray.iterator();  
					while(addressIterator.hasNext()){  
						JSONObject json=(JSONObject)addressIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}
					break;

				case 4: //City sorting
					System.out.println("Sorting according to City : ");

					Collections.sort(jsonArray,new SortDetails("City"));	

					Iterator cityIterator=jsonArray.iterator();  
					while(cityIterator.hasNext()){  
						JSONObject json=(JSONObject)cityIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}
					break;

				case 5: //State Sorting
					System.out.println("Sorting according to State : ");

					Collections.sort(jsonArray,new SortDetails("State"));	

					Iterator stateIterator=jsonArray.iterator();  
					while(stateIterator.hasNext()){  
						JSONObject json=(JSONObject)stateIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}
					break;
				case 6: //Zip sorting
					System.out.println("Sorting according to Zip : ");

					Collections.sort(jsonArray,new SortDetails("Zip"));	

					Iterator zipIterator=jsonArray.iterator();  
					while(zipIterator.hasNext()){  
						JSONObject json=(JSONObject)zipIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}
					break;
				case 7: // Mobile number sorting
					System.out.println("Sorting according to Mobile number : ");

					Collections.sort(jsonArray,new SortDetails("MobileNumber"));	

					Iterator mobileNumberIterator=jsonArray.iterator();  
					while(mobileNumberIterator.hasNext()){  
						JSONObject json=(JSONObject)mobileNumberIterator.next();  
						System.out.println(json.get("FirstName")+"  "+json.get("LastName")+" "+json.get("Address")
						+" "+json.get("City")+" "+" "+json.get("State")+" "+json.get("Zip")+" "+json.get("MobileNumber")
								);
					}

					break;
				default:
					System.out.println("Wrong Choice :");

				}
				System.out.println("you want try again press\"Y\" else press \"N\" ");
				String ch=scanner.next().toLowerCase();
				if(ch.equals("y")) {
					check=true;
				} else {
					check=false;
				}
			}while(check);

		} catch ( IOException |  org.json.simple.parser.ParseException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @param file
	 * method to find the person
	 */

	public static void personDetails(File file) {

		FileReader fileReader=null;
		Scanner scanner=new Scanner(System.in);

		try {
			System.out.println("Enter a name want to search : ");
			String userName=scanner.nextLine();

			fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray=(JSONArray) parser.parse(fileReader);
			Iterator iterator=jsonArray.iterator();

			while(iterator.hasNext()) {

				JSONObject jsonObject= (JSONObject) iterator.next();
				if (jsonObject.get("FirstName").equals(userName)) {

					Iterator iterator1=  jsonObject.keySet().iterator();
					while(iterator1.hasNext()) {

						String jsonKey=(String) iterator1.next();

						System.out.println(jsonKey+" :" +jsonObject.get(jsonKey));
					}
				}
			}
		} catch ( IOException |  org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param elements
	 * @return
	 * method to sort the element
	 */
	public static<T extends Comparable> T[] bubbleSort(T[] elements) {
		int i,j;
		for(i=0;i<elements.length;i++) {
			for(j=1;j<(elements.length)-i;j++) {
				if( (elements[j-1]).compareTo(elements[j]) > 0) {
					T temp=elements[j-1];
					elements[j-1]=elements[j];
					elements[j]=temp;
				}
			}
		}
		return elements;
	}



	/**person Details
	 * 
	 */
	public static void personDetails()
	{
		File file=new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/PersonDetails.json");
		FileReader fileReader=null;
		try 
		{
			System.out.println("Enter a name want to search : ");
			String userName=scanner.nextLine();
			fileReader=new FileReader(file);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray=(JSONArray) parser.parse(fileReader);
			Iterator<?> iterator=jsonArray.iterator();
			while(iterator.hasNext())
			{
				JSONObject jsonObject= (JSONObject) iterator.next();
				if (jsonObject.get("FirstName").equals(userName)) 
				{
					Iterator<?> iterator1=  jsonObject.keySet().iterator();
					while(iterator1.hasNext())
					{
						String jsonKey=(String) iterator1.next();
						System.out.println(jsonKey+" :" +jsonObject.get(jsonKey));
						System.out.println("-----------------------------------------");
					}
				}
			}
		}

		catch( IOException |  org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void writeOnFile1(int numberOfStocks)
	{
		try {
			File file= new File("/home/bridgeit/Ashwini/java programs/ObjectOrientedPrograms/stockReport.json");

			FileWriter fileWriter=new FileWriter(file);
			JSONObject products=new JSONObject();
			JSONObject jsonObject=new JSONObject();
			for(int i=0;i<numberOfStocks;i++)
			{
				jsonObject.put("Stack_name", "shareName");
				jsonObject.put("Number_Of_Share", "numberOfShares");
				jsonObject.put("stock_Amount", "sharePrice");
				fileWriter.write(JSONValue.toJSONString(products));
			}
			fileWriter.flush();
			fileWriter.close();


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static ArrayList<ArrayList<String>> stocs=new ArrayList<ArrayList<String>>();
	public static  ArrayList<ArrayList<String>> creatingStocks( String shareName, String numberOfShares, String sharePrice) {

		try {
			ArrayList<String> stock=new ArrayList<String>();
			stock.add(shareName);
			stock.add(numberOfShares);
			stock.add(sharePrice);
			stocs.add(stock);
		} catch(Exception ex) {
			ex.getMessage();
		}
		return stocs;
	}
	public static void valueOfEachStock()
	{
		try 
		{
			for (ArrayList<String> value:stocs)
			{
				System.out.println("*****************************************");
				System.out.println("Share Name: "+value.get(0));
				System.out.println("Number of shares are: "+value.get(1));
				System.out.println("Share Price is: "+value.get(2));
				System.out.println();
				System.out.println(value.get(0)+" Stock value is: "+Integer.parseInt(value.get(1).toString())*Integer.parseInt(value.get(2).toString()));
			}
		} 
		catch(Exception ex)
		{
			System.out.println(ex);		
		}
	}
	public static void valueOfTotalStock()
	{
		int total=0;
		try
		{
			for (ArrayList<String> value:stocs)
			{
				total=total+Integer.parseInt(value.get(1))*Integer.parseInt(value.get(2));
			}
			System.out.println("*****************************************");

			System.out.println("The total Stock Price is : "+total);
		} 
		catch(Exception ex) 
		{
			System.out.println(ex);
		}
	}
}











