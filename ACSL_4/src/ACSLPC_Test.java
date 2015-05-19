import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * Philip Clark
 * ACSL 4
 * 4/12/15
 */
public class ACSLPC_Test {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        
    for (int s = 0; s < 5; s++) {
      String[] input_data = input.nextLine().split(", ");
      List<String> terms = new ArrayList<String>();

      for (int i = 0; i < input_data.length-1; i++) {
          String binary = Integer.toBinaryString(Integer.parseInt(input_data[i]));
          if(s < 3)
          {
            binary = String.format("%3s", binary).replace(' ', '0');
          }
          else
          {
            binary = String.format("%4s", binary).replace(' ', '0');
          }
          terms.add(i, binary);
        }
      
      
      List<String> simplified = new ArrayList<String>(terms);

      while(simplify(simplified).size() > simplified.size())
      {
        List<String> new_simp = new ArrayList<String>();
       new_simp = simplify(simplified);
        Set<String> aSet = new HashSet<String>(new_simp);
        simplified.clear();
       simplified.addAll(aSet);
      }
      /*
       List<String> simp_one = new ArrayList<String>();
            List<String> simp_two = new ArrayList<String>();
       
           simp_one = simplify(terms);
           Set<String> aSet = new HashSet<String>(simp_one);
           simp_one.clear();
           simp_one.addAll(aSet);
   
          simp_two = simplify(simp_one);
          Set<String> bSet = new HashSet<String>(simp_two);
          simp_two.clear();
          simp_two.addAll(bSet);
        
        for (int i = 0; i < simp_two.size()-1; i++) {
          System.out.print(convert_out(simp_two.get(i)) + " + ");
        }
        System.out.print(convert_out(simp_two.get(simp_two.size()-1)));
        System.out.println();*/
      for (int i = 0; i < simplified.size()-1; i++) {
        System.out.print(convert_out(simplified.get(i)) + " + ");
      }
      System.out.print(convert_out(simplified.get(simplified.size()-1)));
      System.out.println();
        
    }
  }
  
  public static List<String> simplify (List<String> terms)
  {
    List<String> simplified = new ArrayList<String>();
    boolean combined = false;
    
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < terms.size(); j++) {
        if(findIndex(terms.get(j)) == i)
        {
          combined = false;
          for (int k = 0; k < terms.size(); k++) {
            if(can_combine(terms.get(j), terms.get(k)))
            {
              simplified.add(evaluate(terms.get(j), terms.get(k)));
              combined = true;
            }
          }
          if(combined == false)
          {
            simplified.add(terms.get(j));
          }
        }
      }
    }
    return simplified;
  }
  
  public static String convert_out (String value)
  {
    String converted = "";
        if(value.charAt(0) == '0')
        {
          converted+="a";
        }
        if(value.charAt(0) == '1')
        {
          converted+="A";
        }
        if(value.charAt(1) == '0')
        {
          converted+="b";
        }
        if(value.charAt(1) == '1')
        {
          converted+="B";
        }
        if(value.charAt(2) == '0')
        {
          converted+="c";
        }
        if(value.charAt(2) == '1')
        {
          converted+="C";
        }
        
        if(value.length() == 4)
        {
          if(value.charAt(3) == '0')
          {
            converted+="d";
          }
          if(value.charAt(3) == '1')
          {
            converted+="D";
          }
        }
    return converted;
  }
  
  public static String evaluate (String a, String b)
  {
    String value = "";
    for (int i = 0; i < a.length(); i++) {
      if(a.charAt(i)==b.charAt(i))
      {
        value += a.charAt(i);
      }
      else{
        value += "x";
      }
    }
    return value;
  }
  
  public static boolean can_combine(String a, String b){
    boolean check = false;
    if(Math.abs(findIndex(a)-findIndex(b)) == 1){
      if(differ_one(a, b))
        check= true; 
      else 
        check = false; 
    }
    return check; 
  }
  
  public static int findIndex(String string){
    int index = 0; 
    for (int i = 0; i < string.length(); i++) {
      if(string.charAt(i) == '1')
        index++; 
    }
    return index; 
  }
  
  public static boolean differ_one (String a, String b)
  {
    int differ_counter = 0;
    for (int i = 0; i < a.length(); i++) {
      if(!(a.charAt(i)==b.charAt(i)))
      {
        differ_counter++;
      }
    }
    boolean one_diff = false;
    
    if(differ_counter == 1)
    return true;
      
    return false;
  }
}