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
        CleverSIDC cs = new CleverSIDC(1);
        cs.add(cs, 1, "Data for 1");
        cs.add(cs, 2, "Data for 2");
        cs.add(cs, 3, "Data for 3");
        cs.add(cs, 4, "Data for 4");
        cs.add(cs, 5, "Data for 5");
        cs.add(cs, 6, "Data for 6");
        cs.add(cs, 7, "Data for 7");
        cs.add(cs, 8, "Data for 8");
        cs.remove(cs, 8);
        cs.allKeys(cs);

        cs.remove(cs, 7);
        cs.allKeys(cs);


        cs.remove(cs, 6);
        cs.allKeys(cs);

        cs.remove(cs, 5);
        cs.allKeys(cs);

        cs.remove(cs, 4);
        cs.allKeys(cs);

        cs.remove(cs, 3);
        cs.remove(cs, 2);
        cs.remove(cs, 1);

        cs.allKeys(cs);
    }
}
