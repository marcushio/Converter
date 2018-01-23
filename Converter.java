import java.util.*; 

/**
 * Description of class Converter here.
 *
 * @author Marcus Trujillo
 * @version 1/23/18
 */
public class Converter
{
    private Scanner keyboard; 
    ArrayList<Integer> binaries; 
    
    public Converter(){
        keyboard = new Scanner(System.in); 
        binaries = new ArrayList<Integer>(); 
    }
    private void printInfo(){
        System.out.println("First enter the number of values you have, we'll then see if their valid, and if they are convert them to decimal"); 
        System.out.println("Please enter all binary numbers without spaces.");        
    }
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
    private void evaluateNumber(String userValue){
        int decimal = Integer.parseInt(userValue, 2);
        System.out.println("string: " + userValue);
        System.out.println("status: valid"); 
        System.out.println("decimal value: " + decimal); 
    }
    private void processFileNumbers(){
    
    }
    public static void main(String args[]){
        Converter converter = new Converter(); 
        converter.printInfo(); 
        converter.processUserNumbers(); 
        
    }
}
