/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author racevedo319
 */
public class Pokemon
{
    private String name;
    private String type;
    private String gender;
    private String nature;
    private ArrayList<String> abilities;
    private ArrayList<String> moves;
    private String nickname;
    private String description;
    
    
    public Pokemon(String name, String type, String gender, String nature, ArrayList<String>abilities, ArrayList<String> moves, String nickname, String description)
    {
        this.abilities = abilities;
        this.moves = moves;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.nature = nature;
        this.nickname = nickname;
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @return the ability
     */
    public ArrayList<String> getAbilities() {
        return abilities;
    }

    /**
     * @param ability the ability to set
     */
    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    /**
     * @return the moves
     */
    public ArrayList<String> getMoves() {
        return moves;
    }

    /**
     * @param moves the moves to set
     */
    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }
    
    public String toStringForWriting(){
        String result = "";
        
        //add nickname below
        result += "Name: " + this.getName() + " (" + this.getNickname() + ")" + "\n";
        result += "Type: " + this.getType() + "\n";
        result += "Gender: " + this.getGender() + "\n";
        result += "Nature: " + this.nature + "\n";
        result += "Ability: " + this.getAbilities().get(0) + "\n";
        result += "Moves: ";
        for (int i = 0; i != 4; i++){
            result += this.getMoves().get(i);
            if (i != 3){
                result += ", ";
            }
        }
        result += "\n";
        return result;
    }
    
    @Override
    public String toString()
    {
        String result = "";
        
        //add nickname below
        result += "<html>Name: " + this.getName() + " (" + this.getNickname() + ")" + "<br/>";
        result += "Type: " + this.getType() + "<br/>";
        result += "Gender: " + this.getGender() + "<br/>";
        result += "Nature: " + this.nature + "<br/>";
        result += "Ability: " + this.getAbilities().get(0) + "<br/>";
        result += "Moves: ";
        for (int i = 0; i != 4; i++){
            result += this.getMoves().get(i);
            if (i != 3){
                result += ", ";
            }
        }
        result += "<br/>";
        return result;
    }
    
    public static ArrayList<Pokemon> makeAllPokemon(){
        //declare arraylist that will hold pokemon
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        //returns arraylist with all names
        ArrayList<String> names = getAllPokemonNames();
        //returns arraylist with all types
        ArrayList<String> types = getAllPokemonTypes();
        //returns arraylist of arraylists with all moves
        ArrayList<ArrayList> moves = Pokemon.getMovesFromPokemonInfoForAllPokemon(151);
        //returns arraylist of arraylists with all abilities
        ArrayList<ArrayList> abilities = Pokemon.getAllPokemonAbilities();
        //returns arraylist with all natures
        ArrayList<String> natures = Pokemon.getAllPokemonNatures();
        //returns arraylist with all descriptions
        ArrayList<String> descriptions = Pokemon.getAllDescriptions();
        //pass all to constructor through for loop
        for (int i = 0; i != 151; i++){
            
            //add description as last argument
            pokemonList.add(new Pokemon(names.get(i), types.get(i), "Unspecified", natures.get(1), abilities.get(i), moves.get(i), "", descriptions.get(i)));
        }
        
        return pokemonList;
        
    }
    
    //gets all moves ever in existence in the pokemon games as an arraylist
    public static ArrayList<String> getAllMoves(){
        
        Path thePath = Paths.get("PokemonInfo//AllMoves.txt");
        
        try (
            BufferedReader reader = new BufferedReader(new FileReader("PokemonInfo//AllMoves.txt"));
                ){
            ArrayList<String> moves = new ArrayList<>();
            String[] lineArray;
            String curLine = reader.readLine();
            while (curLine != null){
                lineArray = curLine.split("= ");
                moves.add(lineArray[1]);
                curLine = reader.readLine();
            }
            return moves;
            }
            
        
        catch (Exception e){
            //eat the exception
            //System.out.println("hi");
            return null;
        }
        
        
    }
    
    //returns an arraylist of all legal moves for each pokemon
    public static ArrayList<String> getMovesFromPokemonInfo(String pokemonName, int numOfLines){
        String line = FileIO.readPokemonInfo(pokemonName, numOfLines);
        //System.out.println(line);
        //split by spaces
        ArrayList<String> legalMoves = new ArrayList<>();
        ArrayList<String> allMoves = getAllMoves();
        String[] splitLine = line.split(" ");
        for (int x = 0; x != splitLine.length; x++){
            for (int i = 0; i != allMoves.size(); i++){
                try {
                String maybeMoveWithSpace = splitLine[x] + " " + splitLine[x+1];
                if (splitLine[x].equals(allMoves.get(i))){
                    legalMoves.add(splitLine[x]);
                }
                else if (maybeMoveWithSpace.equals(allMoves.get(i))){
                    legalMoves.add(maybeMoveWithSpace);
                }
                }
                catch (Exception e){
                    //do nothing
                }
                
            }
        }
        //make array with every move in the game
        //check each element to see if it contains any of the elements of the move array
        //only keep the ones that do
        return legalMoves;
        
    }
    
    public static ArrayList<ArrayList> getAllPokemonAbilities(){
        
        ArrayList<ArrayList> a = new ArrayList<>();
        String line;
        for (int i = 0; i != 151; i++){
            ArrayList<String> b = new ArrayList<>();
            //gets line #i+1
            line = FileIO.readPokemonInfo("PokemonInfo//Abilities.txt", i+1);
            //split line for each ability
            String[] lineArray = line.split(", ");
            for (String s : lineArray){
                b.add(s);
            }
            a.add(b);
            
        }
        return a;
    }
    
    public static ArrayList<String> getAllPokemonNatures(){
        return FileIO.readByLines("PokemonInfo//Natures.txt");
    }
    
    public static ArrayList<String> getAllPokemonTypes(){
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> lines = FileIO.readByLines("PokemonInfo//PokemonList.txt");
        for (int i = 0; i != lines.size(); i++){
            //System.out.println(lines.get(i));
            String[] lineArray = lines.get(i).split("\\(");
            types.add("(" + lineArray[1]);
        }
        return types;
    }
    
    public static ArrayList<String> getAllPokemonNames(){
        return FileIO.readByLines("PokemonInfo//Pokemon.txt");
    }
    
    
    //this calls the other getMovesFromPokemonInfo for multiple lines of information to go through
    //takes one argument that is equal to the amount of pokemon/lines
    public static ArrayList<ArrayList> getMovesFromPokemonInfoForAllPokemon(int numPokemon){
            //as arraylist that contains every pokemons moves, stored in different arraylists
            ArrayList<ArrayList> a = new ArrayList<>();
            //returns the moves of one pokemon as an arraylist
            for (int i = 0; i != numPokemon; i++){
                //adds an arraylist of moves for each line/pokemon
                a.add(getMovesFromPokemonInfo("PokemonInfo//Moves.txt", i+1));
            }
            return a;
        }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public static ArrayList<String> getAllDescriptions(){
        ArrayList<String> descriptions = FileIO.readByLines("PokemonInfo//Descriptions.txt");
        return descriptions;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
