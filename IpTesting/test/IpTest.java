import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class IpTest {

	
	
	
	@Test
	public void testIpAddressToIPNumberForEmptyString() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"\"";
			assertEquals(expected, e.getMessage());
		}
		
	}
	
	@Test
	public void testIpAddressToIPNumberForEmptySubblock() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("192..0.1");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"\"";
			assertEquals(expected, e.getMessage());
		}
		
	}
	
	@Test
	public void testIpAddressToIPNumberForInvalidFormat() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("192-168-0-1");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"192-168-0-1\"";
			assertEquals(expected, e.getMessage());
		}
		
	}
	
	@Test
	public void testIpAddressToIPNumberForNotANumber() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("abcdsds");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"abcdsds\"";
			assertEquals(expected, e.getMessage());
		}
		
	}
	
	@Test
	public void testIpAddressToIPNumberForNotANumber2() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("192.asd.0.1");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"asd\"";
			assertEquals(expected, e.getMessage());
		}
	}

	@Test
	public void testIpAddressToIPNumberForInvalidIPAdress() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("-5.12.31.2");
			fail();
		}catch(NumberFormatException e){
			final String expected = "Ip address cannot contain negative number";
			assertEquals(expected, e.getMessage());
		}
		
	}
	
	@Test
	public void testIpAddressToIPNumberForInvalidFormat2() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("192.168:0.1");
			fail();
		}catch(NumberFormatException e){
			final String expected = "For input string: \"168:0\"";
			assertEquals(expected, e.getMessage());
		}
	}
	
	@Test
	public void testIpAddressToIPNumberFor2ManyFullStops() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("192.168.0.1.");
			fail();
		}catch(NumberFormatException e){
			final String expected = "Too many fullstops in the ip address";
			assertEquals(expected, e.getMessage());
		}
	}
	
	
	@Test
	public void testIpAddressToIPNumberForInvalidIpAddress() {
		Ip ip = new Ip();
		try{
			ip.ipAddressToIPNumber("255.255.257.255");
			fail();
			
		}catch(NumberFormatException e){
			final String expected = "An ip address cannot contain a number bigger than 255";
			assertEquals(expected, e.getMessage());
		}
	}
	
	@Test
	public void testIpAddressToIPNumberForValidIpAddress() {
		Ip ip = new Ip();
		assertEquals(0l, ip.ipAddressToIPNumber("0.0.0.0"));
	}
	
	@Test
	public void testIpAddressToIPNumberForValidIpAddress2() {
		Ip ip = new Ip();
		assertEquals(10l, ip.ipAddressToIPNumber("0.0.0.10"));
	}
	
}
