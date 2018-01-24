/**
 * @author Marcus Trujillo
 * Assignment #2 : Binary Converter
 * Date: 1.20.18
 * 
 * Description: The program takes a positive binary value, either from user input on the keyboard or from a document, and if it is valid, converts it to a decimal value. Validation 
 * requires that the value be 8bits, and an actual binary number (meaning contains no values of 2 or higher).
 * 
 * Input: Phase one took input from user on the keyboard, but the specs said to leave
 *  it in a form that read from a file called P2Input.txt . 
 * Output: Phase one printed them to the CLI. Phase two writes results to P2Output.txt 
 * 
 * Assumptions/Limitations: As per the spec sheet, program assumes there is a .txt file that exists, 
 *  is readable and is not empty. 
 *  
 * CS2050-003
 * @version 1
 */


import java.util.*; 
import java.nio.file.*; 
import java.io.*; 
import java.nio.charset.Charset;

public class Project2
{
    private Scanner keyboard; 
  
    /**
     * instantiates our scanner to get user input
     */
    public Project2(){
        keyboard = new Scanner(System.in);
    }
    
    
    /**
     * Prints an introduction with instructions on what the user is to do.
     */
    private void printInfo(){
        //System.out.println("First enter the number of values you have, we'll then see if their valid, and if they are convert them to decimal"); 
        //System.out.println("Please enter all binary numbers without spaces."); 
        System.out.println("Please enter the filename you wish to use including the suffix. e.g. values.txt\n>>"); 
    }
    
    
    /**
     * This takes the user's input from the keyboard and processes it and prints results . 
     */
    private void processUserNumbers(){
        System.out.println("How many values do you have?"); 
        String input = keyboard.nextLine(); 
        int rounds = Integer.parseInt(input); 
        for(int i = 0; i < rounds; i++){
            System.out.println("Enter your 8bit value \n >>"); 
            String userValue = keyboard.nextLine(); 
            if(validateNumber(userValue)){
                int value = Integer.parseInt(userValue); 
                evaluateNumber(userValue); 
            } else {
                System.out.println("string: " + userValue); 
                System.out.println("status: invalid"); 
            }
            
        }
        
    }
    
    
    /**
     * This validates whatever the current value is
     * @param userValue is the string of whatever value we need to validate. 
     * @return true if the number is valid and false if it isn't. 
     */
    private boolean validateNumber(String userValue){
        boolean valid = false;
        if(userValue.length() == 8){
            for(int i = 0; i < userValue.length(); i++){
                int evaluated = Character.getNumericValue(userValue.charAt(i));
                if(-1 < evaluated && evaluated < 2 ){
                    valid = true; 
                } else valid = false;             
             }
        }
        return valid; 
    }
    
    
    /**
     * Converts the value given into decimal. It's assumed that validation has already occurred
     * @param userValue is the string of the value we're evaluating. 
     * @return the newly converted decimal number. 
     */
    private int evaluateNumber(String userValue){
        int decimal = Integer.parseInt(userValue, 2);
        //System.out.println("string: " + userValue);
        //System.out.println("status: valid"); 
        //System.out.println("decimal value: " + decimal); 
        return decimal; 
        
    }
    
    
    /**
     * Reads from an input .txt file and writes out a report giving the string, if it was valid, and the conversion to decimal if it was valid. 
     */
    private void processFileNumbers(){
        try {
            String filename = keyboard.nextLine(); 
            Path filePath = Paths.get(filename); 
            System.out.println("Please enter the filename for your output report (include the suffix)"); 
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(keyboard.nextLine())); 
            BufferedReader reader = Files.newBufferedReader(filePath);
            String value = null; 
            writer.write("Marcus Trujillo" + System.lineSeparator() + "Project #2 - Binary Numbers" + System.lineSeparator() + System.lineSeparator()); 
            while((value = reader.readLine()) != null){
                writer.write("string: " + value + System.lineSeparator()); 
                if(validateNumber(value)){
                    writer.write("status: valid" + System.lineSeparator());   
                    writer.write("decimal value: " + evaluateNumber(value) + System.lineSeparator() + System.lineSeparator());    
                } else {
                    writer.write("status: invalid" + System.lineSeparator() + System.lineSeparator());
                }
            } 
            writer.close();
        } catch (IOException ex) {
            System.err.println(ex);
            System.out.println("IO error"); 
        }
    }
    
    
    /**
     * The main method that calls all the necessary methods. 
     */
    public static void main(String args[]){
        Project2 converter = new Project2(); 
        converter.printInfo(); 
        //converter.processUserNumbers();     
        converter.processFileNumbers(); 
    }
}
