package Java_lb_4;

import java.util.Scanner; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		//�������� ��������
		Scanner inScanner = new Scanner(System.in);
		JewelryDAOMySQL jewelryDAO = new JewelryDAOMySQL();
		JewelryService jewelryService = new JewelryService(jewelryDAO);
		MaterialDAOMySQL materialDAOMySQL = new MaterialDAOMySQL();
		MaterialService materialService = new MaterialService(materialDAOMySQL);
		Material material = new Material();
		Jewelry jewelry = new Jewelry();
		try {
			do {
				System.out.println("INSERT � ������� Material - 1");
				System.out.println("INSERT � ������� Jewelry - 2");
				System.out.println("SELECT ������ ���������� - 3");
				System.out.println("UPDATE ������� Material - 4");
				System.out.println("UPDATE ������� Jewelry - 5");			
				System.out.println("SELECT �� ���������� ��������� - 6");

				int choice = inScanner.nextInt();
				switch(choice) {
		 		case 1:

		 			inScanner = new Scanner(System.in);
		 			System.out.println("������� �������� ��� ���������: ");
		 			String title = inScanner.nextLine();
		 			material.setMaterialTitle(title); //��������� �������� ���������
		 			
		 			inScanner = new Scanner(System.in);
		 			System.out.println("������� �������� ��� ���������: ");
		 			String description = inScanner.nextLine();
		 			material.setMaterialDescription(description);//��������� �������� ���������
		 			materialService.createMaterial(material); //���������� ��������� � ��
		 			break;	 			
		 		case 2:
		 			System.out.println("������� �������� �������: ");
					inScanner = new Scanner(System.in);
		 			String jewelry_title = inScanner.nextLine();		 			
		 			jewelry.setJewelryTitle(jewelry_title); //��������� �������� �������
		 			
		 			System.out.println("������� ���� �������: ");
		 			inScanner = new Scanner(System.in);
		 			float price = inScanner.nextFloat();
		 			jewelry.setPrice(price); //��������� ���� �������
		 			
		 			System.out.println("������� id_material: ");
		 			inScanner = new Scanner(System.in);
		 			int id_material = inScanner.nextInt();
		 			jewelry.setIDMaterial(id_material); //��������� id-��������� �������
		 			jewelryService.createJewelry(jewelry); //���������� ������� � �� 			
		 			break;
		 		case 3:
		 			//��������� ������ ���������� � ����� �� �� �����
		 			ArrayList<Material> materialArrayList  = materialService.getAllMaterial();
		 			for (Material materialList : materialArrayList) {
		 				System.out.println(materialList);
		 			}
		 			break;
		 		case 4:
		 			System.out.println("������� ID ��������� ��� ���������: ");
		 			inScanner = new Scanner(System.in);
		 			int id = inScanner.nextInt();
		 			
					System.out.println("������� ����� ��������� ���������: ");
					inScanner = new Scanner(System.in);
			 		String newTitle = inScanner.nextLine();
			 		material.setMaterialTitle(newTitle);
			 		
					System.out.println("������� ����� ��������� ���������: ");
					inScanner = new Scanner(System.in);
			 		String newDescription = inScanner.nextLine();
			 		material.setMaterialDescription(newDescription);
			 		materialService.updateMaterial(material, id); //���������� ������ � ��
			 		break;
		 		case 5:
		 			System.out.println("������� ID ������� ��� ���������: ");
		 			inScanner = new Scanner(System.in);
		 			int id_jewelry = inScanner.nextInt();
		 			
		 			System.out.println("������� ����� ��������� �������: ");
					inScanner = new Scanner(System.in);
			 		String newJewelry = inScanner.nextLine();
			 		jewelry.setJewelryTitle(newJewelry);
			 		
			 		System.out.println("������� ����� ����� �������: ");
					inScanner = new Scanner(System.in);
			 		float newPrice = inScanner.nextFloat();
			 		jewelry.setPrice(newPrice);
			 		
			 		System.out.println("������� ����� �ID-�������� �������: ");
					inScanner = new Scanner(System.in);
			 		int newID = inScanner.nextInt();
			 		jewelry.setIDMaterial(newID);
			 		
			 		jewelryService.updateJewelry(jewelry, id_jewelry); //���������� ������ � ��
		 			break;
		 		case 6:
		 			//����� ������ ������� �� ��������� ���������
		 			System.out.println("������� �������� ��� ������: ");
		 			inScanner = new Scanner(System.in);
		 			String find_material = inScanner.nextLine();	 			
		 			ArrayList<Jewelry> jewelryArrayList  = jewelryService.getJewelryByMaterial(find_material);
		 			for (Jewelry jw : jewelryArrayList) {
		 				System.out.println(jw);
		 			}
		 			break;
		 		default: System.out.println("������ �������� ���!");
		 			break;
			}
				System.out.println();
				inScanner = new Scanner(System.in);
		 		System.out.print("����������?(1/0) ");
		 		int exit = inScanner.nextInt();
		 		System.out.println();
		 		if(exit == 0) {
		 			System.out.println("��������� ���������!");
		 			Runtime.getRuntime().exit(0); //����� �� ���������
		 		}
		 	} while(true);		 		 				 	
		 	}catch (Exception ex) { //��������� ����������
		 		System.out.printf("������!! - %d", ex.getMessage());
		 	}
		}
	}

