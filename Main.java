import java.io.*;
public class Main {

	
	public static void main(String[] args) {
		String text = getTextFile("PlainText.txt");
		System.out.println(text);
		String textValOne = Encryption(text);
		System.out.println(textValOne);
		writeToFile(textValOne);
		String text2 = decrypt(textValOne);
		System.out.println(text2);

	}
	
	public static String getTextFile(String file){ 
		String plainText = "";
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line!= null){
				plainText += line;
				line = br.readLine();
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println("Error occured while reading the file");
		}
		return plainText;
	}
	
	public static String Encryption(String plainText) { 	 
		int blockLength = 6;							
		int[] blockOrder = {1, 3, 4, 5, 0, 2};    	 	
		String additive = " ";							
		
		int fillingVal = (blockLength - plainText.length()%blockLength);
		int i = 0;
		while(i!=fillingVal) {
			plainText += additive;
			i ++;
		}
		
		String tempVal = "";
		String firstVal = "";
		String finalVal = "";
		int plainTextLength = plainText.length();
		
		for (int j=0; j <(plainTextLength-blockLength+1); j += blockLength){   
			tempVal = plainText.substring(j, j+blockLength);
			for (int x: blockOrder){
				firstVal += tempVal.charAt(x);
			}
		}
		
		
		for(int y: blockOrder){										
			int k = y;
			while(k < firstVal.length()){
				finalVal += firstVal.charAt(k);
				k += blockLength;
			}
		}
		return finalVal;
	}
	
	
	public static String decrypt(String cipherText){
		cipherText = cipherText.trim();					 
		int blockLength = 6;
		String additive = " ";
		int[] reverseBlockOrder = {4, 0, 5, 1, 2, 3}; 
		String finalDVal = "";
		int fillingVal = blockLength -(cipherText.length()%blockLength);
		int count = 0;
		while(count < fillingVal) {					
			cipherText += additive;
			count ++;
		}
		
		int numOfBlocks = cipherText.length()/blockLength;		
		String firstDVal = "";
		for (int i = 0; i < blockLength; i++){					
			int startIndex = reverseBlockOrder[i]*numOfBlocks;  
			
			firstDVal += cipherText.substring(startIndex, startIndex + numOfBlocks);
		}
		
		for (int countVal = 0; countVal < numOfBlocks; countVal ++) {		
			int indexVal = countVal;
			while(indexVal < firstDVal.length()){
				finalDVal += firstDVal.charAt(indexVal);
				indexVal += numOfBlocks;
			}
		}
		
		String plainTextReturn = "";	
										
		for (int indexValTwo = 0; indexValTwo < (finalDVal.length()-blockLength+1); indexValTwo += blockLength){  
			String tempVal = finalDVal.substring(indexValTwo, indexValTwo + blockLength);
			
			for (int element: reverseBlockOrder){
				plainTextReturn += tempVal.charAt(element);
			}
		}
		return plainTextReturn;
	}
	
	public static void writeToFile(String cipherText){
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( "output.txt"));
		    writer.write( cipherText);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		
	}
}

