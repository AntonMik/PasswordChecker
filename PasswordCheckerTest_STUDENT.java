package password;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Anton Mikhaylenko
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> passwords;
	@Before
	public void setUp() throws Exception {
		String[] p = {"DQWDWQ88", "ddd22dadD", "jjIn9", "wdfef8", "dnwd8qd"
				,"dwqdjqijonJHO", "dNIDUQNQin"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p)); 
	}

	@After
	public void tearDown() throws Exception {
	passwords = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			PasswordCheckerUtility.isValidPassword("a");
			assertTrue("Did not throw lengthException",false);
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try{
			PasswordCheckerUtility.isValidPassword("wdoqnd80");
			assertTrue("Did not throw NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try{
			PasswordCheckerUtility.isValidPassword("DIAQBI79");
			assertTrue("Did not throw NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("Uhd9DJNA9uh9nh"));
			assertTrue(PasswordCheckerUtility.isWeakPassword("fqWDs9W"));
		}
		catch(Exception e)
		{
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			
			assertTrue(PasswordCheckerUtility.isValidPassword("DniDuuuu88q"));
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("fefnwiifwFU"));
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw an NoDigitExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides an NoDigitException",false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try{
			assertEquals(true,PasswordCheckerUtility.isValidPassword("DniDN78d"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("DQdnq97hn"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("DQnq98uhdhduiq"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Dqdji09jd"));
			assertEquals(true,PasswordCheckerUtility.isValidPassword("Dq8djmf"));
		}
		
		catch(Exception e)
		{
			assertTrue("Threw an exception",false);
		}
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testValidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.validPasswords(passwords);
		Scanner scan = new Scanner(results.get(0)); //
		assertEquals("DQWDWQ88", scan.next());
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1)); //
		assertEquals("ddd22dadD", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("more than two"));
		
		scan = new Scanner(results.get(2)); //
		assertEquals("jjIn9", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		System.out.println(nextResults);
		assertTrue(nextResults.contains("at least 6"));
		
		scan = new Scanner(results.get(3)); //
		assertEquals("wdfef8", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4)); //
		assertEquals("dnwd8qd", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(5)); //
		assertEquals("dwqdjqijonJHO", scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
		
		scan = new Scanner(results.get(6)); //a
		assertEquals("dNIDUQNQin",scan.next());
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit"));
	}
	
}
