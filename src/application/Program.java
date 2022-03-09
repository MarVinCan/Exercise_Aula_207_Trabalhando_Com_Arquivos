package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		
		System.out.print("Enter file path: ");
		String csv = sc.nextLine();
			
		
		try(BufferedReader br = new BufferedReader(new FileReader(csv))){
			String line = br.readLine();
			while(line != null) {
				
				double price = Double.valueOf(line.substring(line.indexOf(",") + 1,line.lastIndexOf(","))).doubleValue();
				int quant = Integer.valueOf(line.substring(line.lastIndexOf(",") + 1)).intValue();
				double fp = price * quant;
				String edtLine = line.substring(0,line.indexOf(",")+ 1); 
				list.add(edtLine + String.format("%.2f", fp));
				
				line = br.readLine();	
			}
			 boolean success = new File(csv.substring(0, csv.lastIndexOf("\\")) + "\\out").mkdir();
			System.out.println("Directory created successfully: " + success);
			String outCsv = "C:\\temp\\out\\summary.csv";
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(outCsv))){
				
				for(String l : list) {
					bw.write(l);
					bw.newLine();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			
			sc.close();
		}

	}

}
