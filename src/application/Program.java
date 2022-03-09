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

		try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
			String line = br.readLine();
			while (line != null) {

				double price = Double.valueOf(line.substring(line.indexOf(",") + 1, line.lastIndexOf(",")))
						.doubleValue();
				int quant = Integer.valueOf(line.substring(line.lastIndexOf(",") + 1)).intValue();
				String edtLine = line.substring(0, line.indexOf(",") + 1);
				Product p = new Product(edtLine, price, quant);
				list.add(p);
				line = br.readLine();
			}
			String formatCsv = csv.substring(0, csv.lastIndexOf("\\"));
			boolean success = new File(formatCsv + "\\out").mkdir();
			System.out.println("Directory created successfully: " + success);
			String outCsv = "\\out\\summary.csv";

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
