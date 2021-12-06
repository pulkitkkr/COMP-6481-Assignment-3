//______________________________________________________________________________________________________________________
// Programming Assignment 3
// Team Members
// 1. Manish Sehgal - 40165366
// 2. Pulkit Kakkar - 40160971
//
//______________________________________________________________________________________________________________________

import java.io.FileReader;
import java.util.Scanner;

public class CleverSIDCDriver {
    private static String fileToRead = "NASTA_test_file1.txt";

    private static void putDataInSIDC(CleverSIDC cs){
        try {
            FileReader reader = new FileReader(fileToRead);
            Scanner sc = new Scanner(reader);
            int itemAdded = 0;

            while(sc.hasNextInt()){
                int SIDCToAdd = sc.nextInt();
                cs.add(cs,SIDCToAdd, "Data for "+SIDCToAdd);
                itemAdded++;
                System.out.println("Added Item "+itemAdded+" with Key:"+SIDCToAdd+" into "+cs.sidcHandler.getClass().getName());
            }

        } catch (Exception e) {
            System.out.println(fileToRead + " not found");
        }

    }

    private static void deleteDataInSIDC(CleverSIDC cs){
        try {
            FileReader reader = new FileReader(fileToRead);
            Scanner sc = new Scanner(reader);
            int itemAdded = 0;

            while(sc.hasNextInt()){
                int SIDCToAdd = sc.nextInt();
                cs.remove(cs,SIDCToAdd);
                itemAdded++;
            }

        } catch (Exception e) {
            System.out.println(fileToRead + " not found");
        }

    }

    public static void main(String args[]){
        CleverSIDC cs = new CleverSIDC(5);
        
        Scanner sc = new Scanner(System.in);
        while(true) {
	        System.out.println("=========================");
	        System.out.println("Choose your option(MC) :");
	        System.out.println();
	        
	        System.out.println("1. Insert Item");
	        System.out.println("2. Set Threshold");
	        System.out.println("3. Remove Item");
	        System.out.println("4. Get Previous key");
	        System.out.println("5. Get Next Key");
	        System.out.println("6. Print All Keys");
	        System.out.println("7. Get Values");
	        System.out.println("8. Range Keys");
	        System.out.println("9. Load data from file");
	        System.out.println("10. Exit");
	        System.out.println("=========================");
	        
	        int n = sc.nextInt();
	        
	        switch(n) {
	        case 1:
	        	System.out.println("Choose one of the following:");
	        	System.out.println("1. Insert Manually");
	        	System.out.println("2. Insert Randomly");
	        	int val = sc.nextInt();
	        	
	        	switch(val) {
	        		case 1:
	        			System.out.println("Enter a key to insert");
	        			int key = sc.nextInt();
	        			cs.add(cs, key, "Data for "+key);
	        			break;
	        			
	        		case 2:
	        			int x = cs.generate();
	        			cs.add(cs,x ,  "Data for "+x);
	        			break;	
	        	}
	        	break;
	        	
	        case 2:
	        	System.out.println("Enter threshold value");
	        	int threshval = sc.nextInt();
	        	cs.SetSIDCThreshold(threshval);
	        	break;
	        	
	        case 3:
	        	System.out.println("Enter key to delete");
	        	int delKey = sc.nextInt();
	        	cs.remove(cs, delKey);
	        	break;
	        
	        case 4:
	        	System.out.println("Enter the key to get its previous key");
	        	int prevKey = sc.nextInt();
	        	System.out.println("Prev Key is: "+cs.prevKey(cs, prevKey));
	        	break;
	        	
	        
	        case 5:
	        	System.out.println("Enter the key to get its next key");
	        	int nextKey = sc.nextInt();
	        	System.out.println("Next Key is: "+cs.nextKey(cs, nextKey));
	        	break;
	        	
	        case 6:
	        	System.out.println("Here are all the keys -->");
	        	System.out.println();
	        	cs.allKeys(cs);
	        	break;
	        
	        case 7:
	        	System.out.println("Enter the key to get its value");
	        	int getKey = sc.nextInt();
	        	System.out.println("Value is: "+cs.getValues(cs, getKey));
	        	break;
	        	
	        case 8:
	        	System.out.println("Enter key1");
	        	int key1 = sc.nextInt();
	        	System.out.println("Enter key2");
	        	int key2 = sc.nextInt();
	        	System.out.println("Range is: "+cs.rangeKey(cs, key1, key2));
	        	break;
	        	
	        case 9: 
	        	System.out.println("Enter file number");
	        	int fileNum = sc.nextInt();
	        	fileToRead = "NASTA_test_file"+fileNum+".txt";
	        	putDataInSIDC(cs);
	        	break;
	        
	        case 10:
	        	System.exit(0);
	        
	        }
        
        }
    }
}
