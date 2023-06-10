package com.example.SimpleCalculator;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CalculatorServices {
  public String calculate(String expression) {
	  if(Valid(expression).equals("E") || expression.charAt(0)==')'){
          return "E";
      }
	  if(!areBalanced(expression)) {
		  return "E";
	  }
      char [] ch=expression.toCharArray();
      Stack<Double> numbers = new Stack<Double>();
      Stack<Character> operators= new Stack<Character>();
      if(isOperator(ch[0]))
      return "E";
      for(int i=0;i<ch.length;i++){
          if(ch[i]>='0'&&ch[i]<='9'){
              String digit ="";
              while(i<ch.length && (ch[i]>='0' && ch[i]<='9' || ch[i]=='.')){
                  digit=digit+ch[i++];
              }
              numbers.push(Double.parseDouble(digit));
              i--;
          }
          else if(ch[i]=='('){
              operators.push(ch[i]);
              
          }
          else if(ch[i]==')'){
              while(operators.peek()!='('){
                  numbers.push(calc(numbers.pop(),operators.pop(),numbers.pop()));
              }
              operators.pop();
          }
          else if(ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/' || ch[i]=='^'){
              while(!operators.empty() && (precedence(operators.peek())>=precedence(ch[i]))){
                  numbers.push(calc(numbers.pop(),operators.pop(),numbers.pop()));
              }
              operators.push(ch[i]);
          }
          
      }
      while(!operators.empty()){
          numbers.push(calc(numbers.pop(),operators.pop(),numbers.pop()));
      }
      double res=numbers.pop();
      if(ch[0]=='√' && res>=0) {
    	  res=Math.sqrt(res);
      }
      else if(ch[ch.length-1]=='%')
      {
    	  res=res/100.0;
      }
      String x=String.valueOf(res);
      return x;
  }
  public double calc(double y, char op , double x){
      switch(op){
          case '+':
              return x+y;
          case '-':
              return x-y;
          case '*':
              return x*y;
          case '/':
              return x/y; 
          case '^':
              return Math.pow(x, y);
      }
      return 0;
  }
  public int precedence(char op){
      switch(op){
          case '+':
          case '-':
            return 1;
          case '*':
          case '/':
            return 2;
          case '^':
        	return 3;
      }
      return 0;
  }
  public Boolean isOperator(char op){
      switch(op){
          case '+':
              return true;
          case '-':
              return true;
          case '*':
              return true;
          case '/':
              return true;
          case '^':
              return true;
          default:
          return false;
      }
  }
  public String Valid(String exp){
      char[] ch = exp.toCharArray();
      for(int i=1;i<ch.length;i++){
          if(ch[i]=='0'&& ch[i-1]=='/')
          return "E";
      }
      return exp;
  }
  public Boolean areBalanced(String str)
  {   Stack<Character> tmp = new Stack<Character>();
      char ch[]=str.toCharArray();
      for(int i=0;i<ch.length;i++)
      {
          if(ch[i]=='(')
          tmp.push(ch[i]);
          else if(!tmp.isEmpty()&& ( ch[i]==')' && tmp.peek()=='('))
          tmp.pop();
          else if ( tmp.isEmpty() && ch[i]==')')
          {
           tmp.push(ch[i]);
          }
          
      }
      if(tmp.isEmpty())
      return true;
      else 
      return false;
      
  }

}
