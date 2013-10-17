package fr.k2i.adnjoy.stripes;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class WebUtil {
	public static boolean isValidEmailAddress(String aEmailAddress){
	    if (aEmailAddress == null) return false;
	    boolean result = true;
	    try {
	      InternetAddress emailAddr = new InternetAddress(aEmailAddress);
	      if ( ! hasNameAndDomain(aEmailAddress) ) {
	        result = false;
	      }
	    }
	    catch (AddressException ex){
	      result = false;
	    }
	    return result;
	  }

	  private static boolean hasNameAndDomain(String aEmailAddress){
	    String[] tokens = aEmailAddress.split("@");
	    return 
	     tokens.length == 2 &&
	     tokens[0].length()>0 && tokens[1].length()>0;
	  }
}
