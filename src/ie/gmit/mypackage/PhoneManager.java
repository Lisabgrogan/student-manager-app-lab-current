package ie.gmit.mypackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhoneManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private List<Phone> phoneList;

	// Constructor
	public PhoneManager() {
		// Instantiate a phone ArrayList
		phoneList = new ArrayList<Phone>();
	}

	// Getters and Setters
	public List<Phone> getPhone() {
		return phoneList;
	}

	public void setPhone(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	/**
	 * This method adds a Phone to the Phone List.
	 *
	 * @param phone a  object that is to be added to the phone list
	 * @return a boolean value indicating if the add was successful		
	 */                     
	public boolean addPhone(Phone phone) {
		try {
			// Using Collections add method. It returns true if this collection
			// changed as a result of the call
			return phoneList.add(phone);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deletePhone(Phone phone) {
		try {
			// Using Collections remove method. It returns true if this list 
			// contained the specified element
			return phoneList.remove(phone);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deletePhoneById(String phoneId) {
		// Search for the Phone by ID
		Phone phone = searchForPhoneById(phoneId);
		// If a Phone was found then delete the phone
		if (phone != null) {
			return deletePhone(phone);
		} else {
			// If no phone was found Return false
			return false;
		}
	}

	public Phone searchForPhoneById(String phoneId) {

		// Loop over (i.e. Iterate over) arrayList for Phone type elements in
		// the phones ArrayList

		// There are 3 ways you can iterate through a List.
		// 1. For Loop
		// 2. Advanced For Loop
		// 3. Iterator

		// 1. For Loop
//		for (int i = 0; i < phoneList.size(); i++) {
//			if (phoneList.get(i).getPhoneId().equals(phoneId)) {
//				return phoneList.get(i);
//			}
//		}

		// 2. Advanced For Loop
//		for (Phone phone : phoneList) {
//			// No need to check for null as ArrayList is dynamic and fills holes
//			if (phone.getPhoneId().equals(phoneId)) {
//				return phone;
//			}
//		}

		// 3. Iterator
		Iterator<Phone> phoneIterator = phoneList.iterator();
		Phone phoneObjectHolder;
		while (phoneIterator.hasNext()) {
			// Store next Phone
			phoneObjectHolder = phoneIterator.next();
			// Check if studentId equals that of current phone object
			if (phoneObjectHolder.getPhoneId().equals(phoneId)) {
				return phoneObjectHolder;
			}
		}

		// Return null if Phone ID was not found
		return null;
	}

	// Find a list of phone by model
	public List<Phone> getPhoneByModel(String Model) {
		// Create a new ArrayList to Hold Phones with same Model
		List<Phone> sameModels = new ArrayList<Phone>();
		// Loop over arrayList for Phone type elements in the phones ArrayList do
		for (Phone phone : phoneList) {
			// If I find a phone with the given model then add to list
			if (phone.getModel().equalsIgnoreCase(model)) {
				sameNames.add(phone);
			}
		}
		// Check if list has any phones
		if (sameModels.size() > 0) {
			// If phones were found then return the list
			return sameModels;
		}
		// If no phones were found with that model then return null
		return null;
	}

	public void loadPhoneFile(String pathToFile) {
		File inFile = new File(pathToFile);
		FileReader fileReader = null;
		BufferedReader br = null;
		String record = null;

		try {
			fileReader = new FileReader(inFile);
			br = new BufferedReader(fileReader);
			br.readLine(); //discard first line of csv file
			while ((record = br.readLine()) != null) {
				String[] elements = record.split(",");
				Student newPhone = new Phone(elements[0], elements[1], elements[2]);
				this.addPhone(newPhone);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int findTotalPhones() {
		// returns the current number of Phones in the ArrayList
		return phoneList.size();
	}
	
	public PhoneManager loadDB(String dbPath){
    	PhoneManager p = null;
    	try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			p = (PhoneManager) in.readObject();
    		in.close();
    	} catch (Exception e) {
    		System.out.print("[Error] Cannont load DB. Cause: ");
    		e.printStackTrace();
    	}
		return p;
    }

}