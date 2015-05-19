import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;

/*
 * Philip Clark
 * The Dalton School
 * 3055
 */

public class Navigate_Test {

	static Scanner input = new Scanner(System.in);
	static HashMap map = new HashMap<String, Double>();

	public static void main(String[] args) {

		map.put("A", 0.0);
		map.put("B", 450.0);
		map.put("C", 590.0);
		map.put("D", 715.0);
		map.put("E", 1080.0);
		map.put("F", 1330.0);
		map.put("G", 1490.0);
		map.put("H", 1870.0);
		map.put("J", 2105.0);
		map.put("K", 2425.0);

		for (int k = 0; k < 5; k++) {
			String rows = input.nextLine();
			String[] input_data = rows.split(", ");

			double time_one = Double.parseDouble(input_data[2]);

			double time_two = Double.parseDouble(input_data[4]);

			double time_difference = 0;

			double distance = Math.abs((double) map.get(input_data[0])  - (double) map.get(input_data[1]));

		//	System.out.println("distance: " + distance);
			double time_elapsed;
			double v1 = 0;
			double v2 = 0;

			if((input_data[3].equalsIgnoreCase("PM") && !input_data[2].equalsIgnoreCase("12")) || (input_data[3].equalsIgnoreCase("AM") && input_data[2].equalsIgnoreCase("12")))
			{
				time_one+=12;
			}
			if((input_data[5].equalsIgnoreCase("PM") && !input_data[4].equalsIgnoreCase("12")) || (input_data[5].equalsIgnoreCase("AM") && input_data[4].equalsIgnoreCase("12")))
			{
				time_two+=12;
			}

			if(Math.abs(time_one-time_two) > 12)
			{
				if(time_one>time_two)
				{
					time_two+=24;
					time_difference = time_two-time_one;

				}

				else{
					time_one+=24;
					time_difference = time_one-time_two;

				}
			}

			if(time_one>time_two)
			{
				v1 = Double.parseDouble(input_data[7]);
				v2 = Double.parseDouble(input_data[6]);
				time_difference = time_one-time_two;
			}
			else{
				v1 = Double.parseDouble(input_data[6]);
				v2 = Double.parseDouble(input_data[7]);
				time_difference = time_two-time_one;
			}

		//	System.out.println("time difference: " + time_difference);

		//	System.out.println("v1 " + v1);
		//	System.out.println("v2 " + v2);
			time_elapsed = (distance - v1*time_difference)/(v1+v2);


			if(time_one < time_two)
			{
				time_elapsed+=time_difference;
			}
			if(v1*time_difference >= distance)
			{
				if(time_two > time_one)
				{
					time_elapsed = distance/v1;
				}
				else
				{
					time_elapsed = 0;
				}
			}

			if(input_data[0].equalsIgnoreCase(input_data[1]))
			{
				time_elapsed = 0;
			}
			if(time_elapsed < 0)
			{
				time_elapsed = 0;
			}
			double minutes = time_elapsed - (int)(time_elapsed);
			minutes*=60;
			minutes = round(minutes, 0);
			int hours = (int)(time_elapsed);
			String minutes_check = Integer.toString((int)minutes);
			if(minutes_check.length()==1)
			{
				minutes_check = "0" + minutes_check;
			}
			System.out.println(hours + ":" + minutes_check);
		}

	}

	//from stack overflow
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}



}
