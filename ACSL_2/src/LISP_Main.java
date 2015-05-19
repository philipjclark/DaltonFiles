/*
 * name, date, revision history
 */
import java.util.Scanner;
import java.util.Arrays;

public class LISP_Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String expres = input.nextLine();
		StringBuilder sb = new StringBuilder(expres);
		int length = expres.length();
		sb.deleteCharAt(0);
		sb.delete(sb.length()-2, sb.length());
		
		String exp = sb.toString();
		String[] subs = exp.split("\\(");
		
		for (int i = 0; i < subs.length; i++) {
			StringBuilder item = new StringBuilder(subs[i]);

			if(!(item.charAt(0) == '('))
			{
				item.insert(0, '(');
			}
			
			if(i == subs.length-1)
			{
				item.append(")");
			}

	subs[i] = item.toString();
		}
		
		for (int l = 0; l < 5; l++) {
			String operation = input.nextLine();

			String[] instrucs = operation.split(" ");
			String print = "";
			
				if(instrucs[0].equalsIgnoreCase("count"))
				{
					print = Integer.toString(subs.length-1);
				}
				else if(instrucs[0].equalsIgnoreCase("remove"))
				{
					print = Remove(subs, Integer.parseInt(instrucs[1]), Integer.parseInt(instrucs[2]));
				}
				
				else if(instrucs[0].equalsIgnoreCase("sort"))
				{
					print = Sort(subs, Integer.parseInt(instrucs[1]), Integer.parseInt(instrucs[2]));
				}
				else if(instrucs[0].equalsIgnoreCase("reverse"))
				{
					print = Reverse(subs, Integer.parseInt(instrucs[1]), Integer.parseInt(instrucs[2]));
				}
				else if(instrucs[0].equalsIgnoreCase("maximum"))
				{
					print = Maximum(subs);
				}
				System.out.println(print);
		}
		
	}
	
	public static String Maximum(String[] subs)
	{

		int max_items = 0;
		String max_list = "";
		for (int i = 0; i < subs.length; i++) {
			String[] items = subs[i].split(" ");
			if(items.length-1 > max_items)
			{
				max_items = items.length-1;
				max_list = subs[i];
			}
		}
		return max_list;
		
	}
	
	public static String Remove (String[] subs, int start, int end)
	{
		String deleted_string = "";
		for (int i = 0; i < subs.length; i++) {
			if(i < start || i > end)
			{
				deleted_string += subs[i];
			}
		}
		deleted_string = deleted_string.trim();
		return deleted_string + ")";
	}
	
	public static String Reverse(String[] subs, int start, int end)
	{
	String begin_string = "";
	String middle_string = "";
	String end_string = "";

	
		for (int i = 0; i < start; i++) {
			begin_string += subs[i];
		}
		
			int length = end - start +1;
			String[] reverse = new String[length];
			 for (int j = 0; j < reverse.length; j++) {
	
			reverse[j] = subs[end-j];
				if(j == 0)
				{
					middle_string += reverse[j] + " ";
				}
				else
				{
					middle_string += reverse[j];
				}
			}
			middle_string = middle_string.trim() + " ";
		
		for (int i = end + 1; i < subs.length; i++) {
			end_string += subs[i];
		}
		String final_string = begin_string + middle_string + end_string;
		final_string = final_string.trim();
		final_string = Fix(final_string);
	
	return final_string + ")";	
	}
	
	public static String Sort (String[] subs, int start, int end)
	{
		String begin_string = "";
		String middle_string = "";
		String end_string = "";

		
			for (int i = 0; i < start; i++) {
				begin_string += subs[i];
			}
			
			int length = end - start +1;
			String[] sort = new String[length];
			for (int j = 0; j < sort.length; j++) {
				StringBuilder item = new StringBuilder(subs[start+j]);
				item.deleteCharAt(0);
				sort[j] = item.toString();
			}
			
			Arrays.sort(sort);
			
			for (int j = 0; j < sort.length; j++) {
			
				if(j == 0)
				{
					middle_string += "(" + sort[j] + " ";
				}
				else{
					middle_string += "(" + sort[j];
				}
			}
			middle_string = middle_string.trim() + " ";
			
			for (int i = end + 1; i < subs.length; i++) {
				end_string += subs[i];
			}
			end_string = end_string.trim();
			
			String final_string = begin_string + middle_string + end_string;
			final_string = final_string.trim();
			final_string = Fix(final_string);
			
		return final_string + ")";
	}
	
	public static String Fix(String spaces)
	{
		StringBuilder fixer = new StringBuilder(spaces);
		for (int i = 1; i < fixer.length(); i++) {
			if(fixer.charAt(i) == '(' && fixer.charAt(i-2) == ' ')
			{
				fixer.deleteCharAt(i-2);
			}
		}
		return fixer.toString();
	}
}
