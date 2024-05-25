import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CryptSimpleSolver 
{
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Character> uniquechar = new ArrayList<Character>();
	static int nos[] = {0,1,2,3,4,5,6,7,8,9};
	static HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
	static int no1,no2,no3,count=0; // hold integer value of s1,s2, s3 that are returned
	static boolean solutionfound=false;
	static ArrayList<ArrayList<Integer>> permuts = new ArrayList<ArrayList<Integer>>(); //values assigned for each letter for different permutations  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	static String s1,s2,s3;
	
	public static void main(String[] args) throws InterruptedException
	{	
		getInput();
		System.out.println("Calculating. Please wait...");
		calculate();
	}

	public static void getInput()
	{
		System.out.println("Enter string 1:");
		s1 = sc.nextLine();
		System.out.println("Enter string 2:");
		s2 = sc.nextLine();
		System.out.println("Enter string 3:");
		s3 = sc.nextLine();
		
		addToArrayList(s1);
		addToArrayList(s2);
		addToArrayList(s3);
	}
	
	public static void calculate()
	{
		Collections.sort(uniquechar);
		recurPermute(0 , nos);
		
	 // System.out.println(permuts.size());

		for(int i=0;i<permuts.size();i++)
		{
			for(int j=0;j<uniquechar.size();j++)
			{
				hm.put(uniquechar.get(j),permuts.get(i).get(j));			
			}

			//iterateHashMap();

		
			no1 = getNumber(s1);
			no2 = getNumber(s2);
			no3 = getNumber(s3);
			
			if(no3==no1+no2 && getLengthOfInt(no1)==s1.length() && getLengthOfInt(no2)==s2.length() && getLengthOfInt(no3)==s3.length() && count<1)
			{
				solutionfound=true;
				System.out.println(s1+":"+no1+"  "+s2+":"+no2+"  "+s3+":"+no3);
				count++;
			}	
		}


	
		if(!solutionfound)
			System.out.println("No solution found!");

	}

	public static void recurPermute(int k , int[] a )
	{
         if(k==a.length)
		{
			ArrayList<Integer> perm = new ArrayList<Integer>();
			for(int i=0;i<a.length;i++)
			{
				perm.add(a[i]);
			}
			permuts.add(perm);
			return ;
		}	
		else
		{	
			for (int i = k; i < a.length; i++)
			{
				swap(i , k , a);
				recurPermute(k+1,a);
				swap(i , k , a);
			}
		}
	}

	public static void swap(int i , int k , int[] a)
	{
		int t = a[i];
        a[i] = a[k];
		a[k] = t;
	}

	public static boolean found(char c)
	{
		boolean flag=false;
		for(int i=0;i<uniquechar.size();i++)
		{
			if(uniquechar.get(i)==c)
				flag=true;
		}
		if(flag)
			return true;
		else
			return false;	
	}
	
	public static void addToArrayList(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			if(!found(s.charAt(i)))
			{
				uniquechar.add(s.charAt(i));
			}
		}
	}
	
	public static void iterateHashMap()
	{	
		for (Map.Entry<Character, Integer> entry : hm.entrySet()) 
		{
		    char key = entry.getKey();
		    int value = entry.getValue();
		    
		    System.out.println("Key:"+key+" Value:"+value);
		}
	}
	
	public static int getNumber(String s)
	{
		
		String temp="";
		for(int i=0;i<s.length();i++)
		{
			temp=temp+hm.get(s.charAt(i));
		}
		return Integer.parseInt(temp);
	}
	
	public static int getLengthOfInt(int n)
	{
		 return String.valueOf(n).length();
	}
}

/*
 The program begins by importing necessary classes, including ArrayList, Collections, HashMap, and Scanner.

It defines a class named CryptSimpleSolver and declares several static variables, including a Scanner object, an ArrayList to store unique characters, an array of integers from 0 to 9, a HashMap to store character-digit mappings, and some integer variables to hold the converted values of input strings.

The main method is the entry point of the program. It calls two methods: getInput to collect input strings from the user and calculate to perform the calculations.

The getInput method prompts the user to enter three strings (s1, s2, and s3) representing the three parts of a cryptarithmetic equation. It then adds unique characters from these strings to the uniquechar ArrayList.

The calculate method sorts the unique characters and generates all possible permutations of digits from 0 to 9 for these characters. It then iterates through each permutation, assigns digit values to characters using a HashMap (hm), converts the strings to numbers, and checks if the equation is satisfied. If a solution is found, it prints the solution.

The recurPermute method uses backtracking to generate all permutations of the array of digits.

The swap method swaps elements in an array.

The found method checks if a character is already present in the uniquechar ArrayList.

The addToArrayList method adds unique characters from a string to the uniquechar ArrayList.

The iterateHashMap method prints the key-value pairs in the hm HashMap.

The getNumber method converts a string of characters to an integer using the digit mappings in the hm HashMap.

The getLengthOfInt method returns the number of digits in an integer.
 */