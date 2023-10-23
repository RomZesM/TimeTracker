package pl.romzes.timetracker.testSmthg;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTesting {

	public static void main(String[] args) throws InterruptedException {
		Calendar calendar = new GregorianCalendar();
		Date start = calendar.getTime();
		//Thread.sleep(5000);
		 calendar.add(Calendar.MINUTE, 1);

		Date finish = calendar.getTime();



		System.out.println(start);
		System.out.println(finish);

		System.out.println(finish.getTime());
		System.out.println(new Date(finish.getTime()));
	}

}
