import java.util.Scanner;


public class Cryptography {

	public static void main(String[] args) {
		int loop = 1;
		int choice;
		while(loop==1)
		{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Cryptography");
		System.out.println("Choose the method of encryption/decryption you would like to perform");
		System.out.println("1. Caesar Cipher");
		System.out.println("2. Vigenere Cipher");
		System.out.println("3. Matrix Transposition Cipher");
		System.out.println("0. Exit");
		choice = scanner.nextInt();
		if(choice==1)
		{
			Caesar();
			System.out.println();
		}
		else if(choice==2)
		{
			Vigenere();
			System.out.println();
		}
		else if(choice==3)
		{
			Matrix();
			System.out.println();
		}
		else if(choice==0)
		{
			loop=0;
			System.out.println();
			System.out.println();
		}
	}	
	}
	
	public static void Caesar()
	{
		int i;
		Scanner inputplaintext = new Scanner(System.in);
		System.out.println("Enter the PlainText");
		String plaintext = inputplaintext.nextLine();
		String tempplaintext = plaintext;
		Scanner encryptkey = new Scanner(System.in);
		System.out.println("Enter the integer key value for Encryption");
		int encryptkeyvalue = encryptkey.nextInt();
		int length = plaintext.length();
		System.out.print("The Ciphertext is:");
		for(i=0;i<length;i++)
		{
			char character = tempplaintext.charAt(i);
			int ascii = (int) character;
			if((character >=65 && character <=90 ))
			{
				ascii = ascii+encryptkeyvalue;
				if(ascii >= 91)
				{
					ascii=ascii-26;
				}
				System.out.print(Character.toString ((char) ascii));	
			}
			else if((character >=97 && character <=122))
			{
				ascii = ascii+encryptkeyvalue;
				if(ascii >= 123)
				{
					ascii=ascii-26;
				}
				System.out.print(Character.toString ((char) ascii));	
			}
			else
			{
				System.out.print(tempplaintext.charAt(i));
			}
			
		}
		System.out.println();
		Scanner inputciphertext = new Scanner(System.in);
		System.out.println("Enter the CipherText");
		String ciphertext = inputciphertext.nextLine();
		String tempciphertext = ciphertext;
		Scanner decryptkey = new Scanner(System.in);
		System.out.println("Enter the integer key value for Decryption");
		int decryptkeyvalue = decryptkey.nextInt();
		length = ciphertext.length();
		System.out.print("The Plaintext is:");
		for(i=0;i<length;i++)
		{
			char character = tempciphertext.charAt(i);
			int ascii = (int) character;
			if((character >=65 && character <=90 ))
			{
				ascii = ascii-decryptkeyvalue;
				if(ascii <= 64)
				{
					ascii=ascii+26;
				}
				System.out.print(Character.toString ((char) ascii));	
			}
			else if((character >=97 && character <=122))
			{
				ascii = ascii-decryptkeyvalue;
				if(ascii <= 96)
				{
					ascii=ascii+26;
				}
				System.out.print(Character.toString ((char) ascii));
			}
			else
			{
				System.out.print(tempciphertext.charAt(i));
			}

		}
		System.out.println();
	}
	
	public static void Vigenere()
	{
		Scanner scanning = new Scanner(System.in);
		System.out.println("Press 1 to perform Encryption or 2 to perform Decryption");
		int choice = scanning.nextInt();
		if(choice==1)
		{
			VigenereEncryption();
			System.out.println();
		}
		else
		{
			VigenereDecryption();
			System.out.println();
		}		
	}
	
	
	
	public static void Matrix()
	{
		Scanner scanning = new Scanner(System.in);
		System.out.println("Press 1 to perform Encryption or 2 to perform Decryption");
		int choice = scanning.nextInt();
		if(choice==1)
		{
			MatrixEncryption();
			System.out.println();
		}
		else
		{
			MatrixDecryption();
			System.out.println();
		}		
	}
	
	
	public static void VigenereEncryption()
	{
		int result;
		String encrypted="";
		int w;
		int t=0;
		int [] espaces = new int [17];
		Scanner inputplaintext = new Scanner(System.in);
		System.out.println("Enter the PlainText");
		String plaintext = inputplaintext.nextLine();
		for(w=0;w<plaintext.length();w++)
		{
			if(plaintext.charAt(w)==' ')
			{
				espaces[t]=w;
				t=t+1;
			}			
		}
		String tempplaintext = plaintext.replaceAll("\\s+","");
		Scanner inputkey = new Scanner(System.in);
		System.out.println("Enter the Key");
		String key = inputkey.nextLine();
		result=check(tempplaintext,key); // check if strings are completely alphabetic or not
		if(result!=1)
		{
			System.out.println("Enter only alphabets in the Text and Key");
			VigenereEncryption();
		}
		String temp1plaintext = tempplaintext;
		String tempkey = key;
		if(temp1plaintext.length()!=tempkey.length())
		{
			if(temp1plaintext.length()<tempkey.length())
			{
				tempkey=tempkey.substring(0,((temp1plaintext.length())));
			}
			else
			{
				int difference = temp1plaintext.length() - tempkey.length();
				for(int j=0;j<difference;j++)
				{
					tempkey=(tempkey)+(tempkey.charAt(j));
				}
			}	
		}
		temp1plaintext=temp1plaintext.toUpperCase();
		tempkey=tempkey.toUpperCase();
		String compare = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println("The CipherText is: ");
		for(int k=0;k<tempkey.length();k++)
		{
			int value = ((compare.indexOf(temp1plaintext.charAt(k)))+(compare.indexOf(tempkey.charAt(k)))) % 26;		
			encrypted=encrypted+compare.charAt(value);
		}
		for(int f=0;f<espaces.length;f++)
		{
			if(espaces[f]!=0)
			{
				encrypted = encrypted.substring(0, espaces[f]) + " " + encrypted.substring(espaces[f], encrypted.length());
				
			}			
		}
		//System.out.println(encrypted);
		for(int r=0;r<plaintext.length();r++)
		{
			if((plaintext.charAt(r)>='a'&&plaintext.charAt(r)<='z'))
			{
				if((encrypted.charAt(r)>='a'&&encrypted.charAt(r)<='z'))
				{
					System.out.print(encrypted.charAt(r));
				}
				else
				{
					char character = encrypted.charAt(r);
					int ascii = (int) character;
					ascii=ascii+32;
					System.out.print(Character.toString ((char) ascii));
				}
				
			}
			else if((plaintext.charAt(r)>='A'&&plaintext.charAt(r)<='Z'))
			{
				System.out.print(encrypted.charAt(r));
			}
			else
			{
				System.out.print(encrypted.charAt(r));
			}			
		}

	}

	
	public static int check(String a,String b)
	{
	int lengthofa = a.length();
	int lengthofb = b.length();
	int flaga = 0;
	int flagb = 0;
	int i;
	for(i=0;i<lengthofa;i++)
	{
		if((a.charAt(i)>='a'&&a.charAt(i)<='z')||(a.charAt(i)>='A'&&a.charAt(i)<='Z'))
		{
			flaga=flaga+1;
		}
	}
		if(flaga!=lengthofa)
		{
			flaga=0;
		}
		else
		{
			flaga=1;
		}
		for(i=0;i<lengthofb;i++)
		{
			if((b.charAt(i)>='a'&&b.charAt(i)<='z')||(b.charAt(i)>='A'&&b.charAt(i)<='Z'))
			{
				flagb=flagb+1;
			}
		}
			if(flagb!=lengthofb)
			{
				flagb=0;
			}
			else
			{
				flagb=1;
			}
		if((flaga+flagb)==2)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static void VigenereDecryption()
	{
		int result;
		int w;
		int u=0;
		String decrypted="";
		int [] dspaces = new int [17];
		Scanner inputciphertext = new Scanner(System.in);
		System.out.println("Enter the CipherText");
		String ciphertext = inputciphertext.nextLine();
		for(w=0;w<ciphertext.length();w++)
		{
			if(ciphertext.charAt(w)==' ')
			{
				dspaces[u]=w;
				u=u+1;
			}			
		}
		String tempciphertext = ciphertext.replaceAll("\\s+","");
		Scanner inputkey = new Scanner(System.in);
		System.out.println("Enter the Key");
		String key = inputkey.nextLine();
		result=check(tempciphertext,key); // check if strings are completely alphabetic or not
		if(result!=1)
		{
			System.out.println("Enter only alphabets in the Text and Key");
			VigenereEncryption();
		}
		String temp1ciphertext = tempciphertext;
		String tempkey = key;
		if(result!=1)
		{
			System.out.println("Enter only alphabets in the Text and Key");
			VigenereDecryption();
		}
		String tempplaintext = ciphertext;
		String tempokey = key;
		if(temp1ciphertext.length()!=tempokey.length())
		{
			if(temp1ciphertext.length()<tempokey.length())
			{
				tempokey=tempokey.substring(0,((temp1ciphertext.length())));
			}
			else
			{
				int difference = temp1ciphertext.length() - tempokey.length();
				for(int j=0;j<difference;j++)
				{
					tempokey=(tempokey)+(tempokey.charAt(j));
				}
			}	
		}
		temp1ciphertext=temp1ciphertext.toUpperCase();
		tempokey=tempokey.toUpperCase();
		String compare = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println("The PlainText is: ");
		for(int k=0;k<tempokey.length();k++)
		{
			int value = ((compare.indexOf(temp1ciphertext.charAt(k)))-(compare.indexOf(tempokey.charAt(k)))) % 26;
			if(value>=0)
			{
				decrypted=decrypted+compare.charAt(value);
			}
			else
			{
				value=26+value;
				decrypted=decrypted+compare.charAt(value);
			}
						
		}		
		for(int x=0;x<dspaces.length;x++)
		{
			if(dspaces[x]!=0)
			{
				decrypted = decrypted.substring(0, dspaces[x]) + " " + decrypted.substring(dspaces[x], decrypted.length());
				
			}			
		}
		//System.out.println(decrypted);
		for(int r=0;r<ciphertext.length();r++)
		{
			if((ciphertext.charAt(r)>='a'&&ciphertext.charAt(r)<='z'))
			{
				if((decrypted.charAt(r)>='a'&&decrypted.charAt(r)<='z'))
				{
					System.out.print(decrypted.charAt(r));
				}
				else
				{
					char character = decrypted.charAt(r);
					int ascii = (int) character;
					ascii=ascii+32;
					System.out.print(Character.toString ((char) ascii));
				}
				
			}
			else if((ciphertext.charAt(r)>='A'&&ciphertext.charAt(r)<='Z'))
			{
				System.out.print(decrypted.charAt(r));
			}
			else
			{
				System.out.print(decrypted.charAt(r));
			}			
		}
	}

	
	public static void MatrixEncryption()
	{
		int highest=0;
		int rows=0;
		int columns=0;
		int i=0;
		int j=0;
		int k=0;
		Scanner inputplaintext = new Scanner(System.in);
		System.out.println("Enter the PlainText");
		String plaintext = inputplaintext.nextLine();
		Scanner scanning = new Scanner(System.in);
		System.out.println("Enter Key");
		int key = scanning.nextInt();
		String tempplaintext = plaintext.replaceAll("\\s+","%"); // replacing spaces in plain text with %
		int tempkey=key;
		if (tempkey > 0) 
		{
		    int max = Integer.MIN_VALUE;
		    while (tempkey > 0) 
		    {
		        int digit = tempkey % 10;
		        max = Math.max(max, digit);		        
		        tempkey /= 10;
		    }

		    highest = max;
		}
		columns=highest;
		int keymatrix[]=new int[highest];
		
		if((tempplaintext.length())%columns==0)
		{
			rows=(tempplaintext.length())/columns;
		}
		else
		{
			rows=(tempplaintext.length())/columns;
			rows=rows+1;
		}
		char[][] plaintextarray = new char[rows][columns]; 
		for(i=0;i<rows;i++)
		{
			for(j=0;j<columns;j++)
			{
				plaintextarray[i][j]='%';   // initializing the array with %
			}			
		}
		for(i=0;i<rows;i++)     // populating array with plain text characters
		{
			for(j=0;j<columns;j++)
			{
				if(k<plaintext.length())
				{
					if(plaintext.charAt(k)==' ')
					{
						plaintextarray[i][j] = '%';
						k=k+1;
					}
					else
					{
						plaintextarray[i][j] = plaintext.charAt(k);
						k=k+1;
					}
				}
			}
			
		}
		tempkey=key;
		int f=columns-1;
		while(tempkey>0) // to create a key matrix
		{
			keymatrix[f]=tempkey%10;
			tempkey=tempkey/10;
			f=f-1;
		}		
		int loop=0;
		k=0;
		System.out.println("The Encrypyted message is");
		while(loop<columns)
		{
			j=(keymatrix[k])-1;
			for(i=0;i<rows;i++)
			{
				System.out.print(plaintextarray[i][j]);
			}
			k=k+1;
			loop=loop+1;
		}		
	}
	
	
	
	
	public static void MatrixDecryption()
	{
		int highest=0;
		int rows=0;
		int columns=0;
		int i=0;
		int j=0;
		int k=0;
		Scanner inputciphertext = new Scanner(System.in);
		System.out.println("Enter the CipherText");
		String ciphertext = inputciphertext.nextLine();
		Scanner scanning = new Scanner(System.in);
		System.out.println("Enter Key");
		int key = scanning.nextInt();
		String tempciphertext = ciphertext.replaceAll("\\s+","%"); // replacing spaces in cipher text with %
		int tempkey=key;
		if (tempkey > 0) 
		{
		    int max = Integer.MIN_VALUE;
		    while (tempkey > 0) 
		    {
		        int digit = tempkey % 10;
		        max = Math.max(max, digit);		        
		        tempkey /= 10;
		    }

		    highest = max;
		}
		columns=highest;
		int keymatrix[]=new int[highest];
		
		if((tempciphertext.length())%columns==0)
		{
			rows=(tempciphertext.length())/columns;
		}
		else
		{
			rows=(tempciphertext.length())/columns;
			rows=rows+1;
		}
		char[][] ciphertextarray = new char[rows][columns];
		char[][] decryptedarray = new char[rows][columns];
		for(i=0;i<rows;i++)
		{
			for(j=0;j<columns;j++)
			{
				ciphertextarray[i][j]='%';   // initializing the array with %
			}			
		}
		k=0;
		for(j=0;j<columns;j++)     // populating array with cipher text characters
		{
			for(i=0;i<rows;i++)
			{
				if(k<ciphertext.length())
				{
					if(ciphertext.charAt(k)==' ')
					{
						ciphertextarray[i][j] = '%';
						k=k+1;
					}
					else
					{
						ciphertextarray[i][j] = ciphertext.charAt(k);
						k=k+1;
					}
				}
			}
			
		}
		tempkey=key;
		int f=columns-1;
		while(tempkey>0) // to create a key matrix
		{
			keymatrix[f]=tempkey%10;
			tempkey=tempkey/10;
			f=f-1;
		}
		int loop=0;
		k=0;
		System.out.println("The Decrypyted message is");
		
		
		while(loop<columns)
		{
			j=(keymatrix[k])-1;
			for(i=0;i<rows;i++)
			{
				decryptedarray[i][j]=ciphertextarray[i][loop];
			}
			k=k+1;
			loop=loop+1;
		}
		
		for(i=0;i<rows;i++)
		{
			for(j=0;j<columns;j++)
			{
				if(decryptedarray[i][j]=='%')
				{
					System.out.print(" ");
				}
				else
				{
					System.out.print(decryptedarray[i][j]);
				}
				
			}
		}
		
		
		
		
		
		
		
	}
	

}
