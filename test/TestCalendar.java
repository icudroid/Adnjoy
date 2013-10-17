import java.util.Calendar;
import java.util.GregorianCalendar;


public class TestCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar cal = new GregorianCalendar(2010,3,15);
		Calendar calStart = new GregorianCalendar();
		calStart.setTime(cal.getTime());
		calStart.add(Calendar.DATE,-cal.get(Calendar.DAY_OF_WEEK)+2);

		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(calStart.getTime());
		calEnd.add(Calendar.DATE,7);

		System.out.println(calStart.getTime());
		System.out.println(calEnd.getTime());
	}

}
