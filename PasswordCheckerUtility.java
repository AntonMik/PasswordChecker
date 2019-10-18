package password;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Anton Mikhaylenko
 *
 */

public class PasswordCheckerUtility {

	
	public PasswordCheckerUtility() {
	
	}
	
	
	public static boolean isValidPassword(String passwordString) throws LengthException,
    NoDigitException,
    NoUpperAlphaException,
    NoLowerAlphaException,
    InvalidSequenceException{
		
		//LengthException
		if (passwordString.length() < 6) {
			throw new LengthException();
		}
		
		//No digit exception
		if (!Pattern.compile( "[0-9]" ).matcher(passwordString).find()) {
			throw new NoDigitException();			
		}
		
		//No upper alpha exception
		if (!Pattern.compile("[A-Z]").matcher(passwordString).find()) {
			throw new NoUpperAlphaException();
		}
		
		//No lower alpha exception
		if (!Pattern.compile("[a-z]").matcher(passwordString).find()) {
			throw new NoLowerAlphaException();
		}
		
		//Invalid sequence exception
		for (int i = 0; i < passwordString.length()-2; i++) {
			if ((passwordString.charAt(i) == passwordString.charAt(i+1)) && passwordString.charAt(i) == passwordString.charAt(i+2)) {
				throw new InvalidSequenceException();				
			}
		}
		//Passed
		return true;
	}
	
	
	public static boolean isWeakPassword(String passwordString) {

		if (passwordString.length() > 5 && passwordString.length() < 10)
			return true;
		else 
			return false;
	}
	
	public static ArrayList validPasswords(ArrayList passwords) {
		
		ArrayList failed = new ArrayList<>();	
		
		
		for (int i = 0; i < passwords.size(); i++) {
			try {
			isValidPassword((String) passwords.get(i)); }
			catch (LengthException e) {
				System.out.println();
				failed.add(passwords.get(i) + " The password must be at least 6 character long");
			}
			catch (NoDigitException e) {
				System.out.println();
				failed.add(passwords.get(i) + " The password must contain at least one digit");
			}
			catch (NoUpperAlphaException e) {
				System.out.println();
				failed.add(passwords.get(i) + " The password must contain at least one uppercase alphabetic character");
			}
			catch (NoLowerAlphaException e) {
				System.out.println();
				failed.add(passwords.get(i) + " The password must contain at least one lowercase alphabetic character");
			}
			catch (InvalidSequenceException e) {
				System.out.println();
				failed.add(passwords.get(i) + " The password cannot contain more than two of the same character in sequence.");
			}
			
			
			
		}
		
		
		return failed;
	}
	
	
}




























