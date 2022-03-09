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

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();

		System.out.print("Enter file path: ");
		String csv = sc.nextLine();
		
		String formatCsv = csv.substring(0, csv.lastIndexOf("\\"));
		boolean success = new File(formatCsv + "\\out").mkdir();
		System.out.println("Directory created successfully: " + success);
		String outCsv = "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
			String line = br.readLine();
			while (line != null) {
				
				String[] parts = line.split(",");
				String edtLine = parts[0];
				double price = Double.parseDouble(parts[1]);
				int quant = Integer.parseInt(parts[2]);
				Product p = new Product(edtLine, price, quant);
				list.add(p);
				line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(formatCsv + outCsv))) {

				for (Product l : list) {
					bw.write(l.toString());
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("SUCCESS IN CREATING THE FILE");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			sc.close();
		}

	}

}
