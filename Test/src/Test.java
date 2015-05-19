import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * Philip Clark
 * ACSL 3
 * 3/5/15
 * 3055
 */
public class Test {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

  for (int s = 0; s < 5; s++) {
    String[] input_data = input.nextLine().split(", ");
    int cross_pos = Integer.parseInt(input_data[0]);
    int x_pos = Integer.parseInt(input_data[1]);
    
    int [] [] board = new int [9] [9];
    
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if(i > 0 && i < 8 && j > 0 && j < 8){
          board[i][j] = 1;
        }
        else
        {
          board[i][j] = 0;
        }
      }
    }
    
    int [] removed = new int [input_data.length-3];

    for (int i = 2; i < input_data.length-1; i++) {
      removed[i-2] = Integer.parseInt(input_data[i]);
      int [] rem_conv = convert_in(removed[i-2]);
      int row = rem_conv[0];
      int col = rem_conv[1];
      board[row][col] = 0;
    }
        
    int [] cross_conv = convert_in(cross_pos);
    int cross_row = cross_conv[0];
    int cross_col = cross_conv[1];
    
    board[cross_conv[0]][cross_conv[1]] = 0;
    
    int [] x_coors = convert_in(x_pos);
    board[x_coors[0]][x_coors[1]] = 0;
   
    List<Integer> poss_moves = new ArrayList<Integer>();
    
    if(board[cross_row][cross_col+1] == 1)
    {
      poss_moves.add(convert_out(cross_row, cross_col+1));
    }
    if(board[cross_row][cross_col-1] == 1)
    {
      poss_moves.add(convert_out(cross_row, cross_col-1));
    }
    if(board[cross_row+1][cross_col] == 1)
    {
      poss_moves.add(convert_out(cross_row+1, cross_col));
    }
    if(board[cross_row-1][cross_col] == 1)
    {
      poss_moves.add(convert_out(cross_row-1, cross_col));
    }
    if(board[cross_row+1][cross_col+1] == 1)
    {
      poss_moves.add(convert_out(cross_row+1, cross_col+1));
    }
    if(board[cross_row-1][cross_col-1] == 1)
    {
      poss_moves.add(convert_out(cross_row-1, cross_col-1));
    }
    if(board[cross_row+1][cross_col-1] == 1)
    {
      poss_moves.add(convert_out(cross_row+1, cross_col-1));
    }
    if(board[cross_row-1][cross_col+1] == 1)
    {
      poss_moves.add(convert_out(cross_row-1, cross_col+1));
    }
  
  List<Integer> captured = new ArrayList<Integer>();

  if(poss_moves.size()>0)
  {
    int [] poss_coors = convert_in(poss_moves.get(0));

    int j = 1;
    int k = 1;
    
    if(poss_coors[0] == x_coors[0])
    {
      if(poss_coors[1] > x_coors[1])
      {
        for (int i = x_coors[1]+1; i < 8; i++) {
          if(board[x_coors[0]][i] == 0)
          {
            i=9;
          }
          else{
            captured.add(convert_out(x_coors[0], i));
        }
        }
      }
      
      else if (poss_coors[1] < x_coors[1])
      {
        for (int i = x_coors[1]-1; i > 0; i--) {
          if(board[x_coors[0]][i] == 0)
          {
            i=0;
          }
          else{
            captured.add(convert_out(x_coors[0], i));
        }
        }
      }
    }
    
    else if(poss_coors[1] == x_coors[1])
    {
      if(poss_coors[0] > x_coors[0])
      {

        for (int i = x_coors[0]+1; i < 8; i++) { 
          if(board[i][(x_coors[1])] == 0)
          {
            i=9;
          }
          else{
            captured.add(convert_out(i, x_coors[1]));        } 
        }
      }
      
      else if (poss_coors[0] < x_coors[0])
      {
        for (int i = x_coors[0]-1; i >0; i--) {
          if(board[i][(x_coors[1])] == 0)
          {
            i=0;
          }
          else{
            captured.add(convert_out(i, x_coors[1]));      }
        }
      }
    }
    
    else if(Math.abs(poss_coors[1]-poss_coors[0]) == Math.abs(x_coors[1]-x_coors[0]) && (!(Math.abs(poss_moves.get(0)-x_pos)%6 == 0) || (Math.abs(poss_moves.get(0)-x_pos)%6 == 0 && Math.abs(poss_coors[0]-x_coors[0])%3==0 && Math.abs(poss_moves.get(0)-x_pos)%24 == 0)))
    {
      int start = 0;
      if(poss_coors[0] > x_coors[0])
      {
        start = x_coors[0]+1;
        for (int i = start; i < 8; i++) {

          if(board[i][(x_coors[1]+j)] == 0)
          {
            i=9;
          }
          else{
            captured.add(convert_out(i, x_coors[1]+j));
          }
          j++;
        }
      }
      
      else if (poss_coors[0] < x_coors[0])
      {
       start = x_coors[0]-1;
       for (int i = start; i > 0; i--) {
         if(board[i][(x_coors[1]-j)] == 0)
         {
           i=0;
         }
         else{
           captured.add(convert_out(i, x_coors[1]-j));
         }
         j++;
       }
      }
      
    }
   
    else if(Math.abs(poss_coors[0]-x_coors[0]) == Math.abs(poss_coors[1]-x_coors[1]))
    {
      if(poss_coors[1] < x_coors[1])
      {
        for (int i = x_coors[0] + 1; i < 8; i++) {
          if(board[i][(x_coors[1]-k)] == 0)
          {
            i=9;
          }
          else{
            captured.add(convert_out(i, x_coors[1]-k));
          }
          k++;
        }
      } 
        
        if(poss_coors[1] > x_coors[1])
        {
          for (int i = x_coors[1] + 1; i < 8; i++) {
            
            if(board[x_coors[0]-k][i] == 0)
            {
              i=9;
            }
            else{
              captured.add(convert_out(x_coors[0]-k, i));
            }
            k++;
          }
      }
    }
  }
  
  boolean check = false;
  for (int i = 0; i < captured.size(); i++) {
    for (int l = 0; l < poss_moves.size(); l++) {
      if(captured.get(i) == poss_moves.get(l))
      {
        poss_moves.remove(l);
      }
    } 
    
  }
  
  String output = "";
  for (int i = 0; i < captured.size(); i++) {
    output+= captured.get(i).toString();
    
    if(!(i==captured.size()-1))
    {
      output+= ", ";
    }
  }
  
  if(captured.size() == 0 || !(poss_moves.size()==0))
  {
    output = "NONE";
  }
  
  System.out.println(output);
  }
    
}
  
  public static int[] convert_in (int pos){
    int n =7;
    if(pos % 7 == 0)
    {
      n=8;
    }
    
    int row = (pos - (pos % n))/n + 1;
    int col = (pos % n);
    
    if(pos % 7 == 0)
    {
      col = 7;
    }
    int [] position = new int [] {row, col};
    return position;
  }
  
  public static int convert_out (int row, int col){
    int position = 7*(row-1) + (col);
    return position;
  }
  
}
