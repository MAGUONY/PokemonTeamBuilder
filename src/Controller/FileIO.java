/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author racevedo319
 */
public class FileIO
{
    
    public static void delete(String fName){
        Path filePath = Paths.get("SavedTeams/" + fName);
        
        try { 
            Files.deleteIfExists(filePath);
        } catch (Exception e) {}
    }
    
    public static void write(String fName, ArrayList<String> pokemon){
        
        String allText = "";
        
        Path filePath = Paths.get(fName);
        
        if (Files.notExists(filePath)){
            try {
                Files.createFile(filePath);
            } catch (Exception e){
                System.err.println(e.toString());
            }
        }
        
        File theFile = filePath.toFile();
        
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(theFile)));
            for (int i = 1; i != 152; i++){
                allText += "#" + i + ": " + pokemon.get(i) + "\n";
                
            }
            out.print(allText);
            out.flush();
            out.close();
        }
        catch (Exception e){
            //eat the exception
            //System.out.println("hi");
        }
        
    }
    
    public static String writeTeam(String fName, ArrayList<Pokemon> team, boolean overwrite){
        String allText = "";
        
        String fileNamePath = "SavedTeams/" + fName + ".txt";
        Path filePath = Paths.get(fileNamePath);
        
        
        if (overwrite){
            try { 
                Files.delete(filePath);
                Files.createFile(filePath);
                File theFile = filePath.toFile();
        
       
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(theFile)));
                for (Pokemon p : team){
                    allText += p.toStringForWriting() + "\n";
                }
                out.print(allText);
                out.flush();
                out.close();
            } catch (Exception e) {
            }
        }
        else {
            int x = 2;

            boolean loopCheck = true;
            while (loopCheck){
                if (Files.notExists(filePath)){
                    try {
                        Files.createFile(filePath);
                        loopCheck = false;
                    } catch (Exception e){
                        System.err.println(e.toString());
                    }
                }
                else {
                    fileNamePath = "SavedTeams/" + fName + Integer.toString(x) + ".txt";
                    filePath = Paths.get(fileNamePath);
                    x++;
                }
            }

            File theFile = filePath.toFile();

            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(theFile)));
                for (Pokemon p : team){
                    allText += p.toStringForWriting() + "\n";
                }
                out.print(allText);
                out.flush();
                out.close();
            }
            catch (Exception e){
                //eat the exception
                //System.out.println("hi");
            }
        }
        
        return fileNamePath;
    }
    
    public static ArrayList<String> readPokedex(String fName){
        
        ArrayList<String> a = new ArrayList<>();
        
        a.add("Choose a Pokemon...");
        
        Path thePath = Paths.get(fName);
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fName));
                ){
            String curLine = reader.readLine();
            while (curLine != null){
                a.add(curLine);
                curLine = reader.readLine();
            }
        
        }
        catch (Exception e){
            //eat the exception
            //System.out.println("hi");
        }
        
        return a;
        
    }
    
    public static String readPokemonInfo(String fName, int lineNum){
        
       
        Path thePath = Paths.get(fName);
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fName));
                ){
            String curLine = reader.readLine();
            for (int i = 1; i != lineNum; i++){
                curLine = reader.readLine();
            }
            return curLine;
            }
            
        
        catch (Exception e){
            //eat the exception
            //System.out.println("hi");
            return null;
        }
        
        
        
    }
    
    public static ArrayList<String> readByLines(String fName){
        Path thePath = Paths.get(fName);
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader(fName));
                ){
            ArrayList<String> a = new ArrayList<>();
            String curLine = reader.readLine();
            while (curLine != null){
                a.add(curLine);
                curLine = reader.readLine();
            }
            return a;
            }
            
        
        catch (Exception e){
            //eat the exception
            //System.out.println("hi");
            return null;
        }
    }
    
    public static ArrayList<String> findAllTeamFiles(){
        
        File folder = new File("SavedTeams");
        
        File[] a = folder.listFiles();
        
        ArrayList<String> fileNames = new ArrayList<>();
        
        if (a.length == 0){
            fileNames.add("No teams created yet...");
        }
        
        else {
            for (int i = 0; i != a.length; i ++){
                fileNames.add(a[i].getName());
            }
        }
        
        return fileNames;
        
        /*ArrayList<String> allFileNames = new ArrayList<>();
        
        String stdName = "Team";
        
        String fileName = "SavedTeams/" + stdName + ".txt";
        
        boolean loopCheck = true;
        
        int x = 2;
        
        while(loopCheck){
        
            
            Path filePath = Paths.get(fileName);

            if (Files.exists(filePath)){ //when it does exist
                try {
                    allFileNames.add(fileName);
                    fileName = "SavedTeams/" + stdName + Integer.toString(x) + ".txt";
                    x++;
                } catch (Exception e){
                    System.err.println(e.toString());
                }
            }
            else {
                if (allFileNames.isEmpty()){
                    allFileNames.add("No teams created yet...");
                }
                loopCheck = false;
            }
        
        }
        
        return allFileNames;*/
        
        
    }
    
    
}
