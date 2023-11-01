
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Manager {
    public int checkInputIntLimit(int min, int max) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice < min || choice > max) {
                throw new NumberFormatException();
            }
            return choice;
        } catch (NumberFormatException e) {
            System.err.println("Re-input");
        }
        return 0;
    }
    
    public int coutWordInFile(String fileSource, String word) {
        FileReader fr = null;
        
        try {
            fr = new FileReader("E:\\FileSearch.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int count = 0;
            while(line != null)  {
                String[] parts = line.split(" ");
                for (String w : parts) {
                    if(w.equalsIgnoreCase(word)) {
                        count++;
                    }
                }
                line = br.readLine();
            }
            return count;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public void getFileNameContainsWordInDirectory(String source, String word) {
        File folder = new File("E:\\Data\\1.txt");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if(file.isFile()) {
                if(coutWordInFile(file.getAbsolutePath(), word) != 0) {
                    System.out.println("File name: " + file.getName());
                }
            }
        }
    }
    
    public void wordProgram() {
        Scanner sc = new Scanner(System.in);
        String path;
        String word;
        while(true) {
            System.out.println("1. Count Word In File");
            System.out.println("2. Find File By Word");
            System.out.println("3. Exit");
            int choice = checkInputIntLimit(1, 3);
            switch (choice) {
                case 1:
                    System.out.print("Enter Path: ");
                    path = sc.nextLine();
                    System.out.println("Enter Word: ");
                    word = sc.nextLine();
                    int count = coutWordInFile(path, word);
                    System.out.println("Count: " + count);
                    break;
                case 2:
                    System.out.print("Enter Path: ");
                    path = sc.nextLine();
                    System.out.println("Enter Word");
                    word = sc.nextLine();
                    getFileNameContainsWordInDirectory(path, word);
                    break;
                case 3:
                    return;
            }
            
        }
    }
}
