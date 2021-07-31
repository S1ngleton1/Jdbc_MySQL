package Java_lb_4;

import java.util.Scanner; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		//Создание объектов
		Scanner inScanner = new Scanner(System.in);
		JewelryDAOMySQL jewelryDAO = new JewelryDAOMySQL();
		JewelryService jewelryService = new JewelryService(jewelryDAO);
		MaterialDAOMySQL materialDAOMySQL = new MaterialDAOMySQL();
		MaterialService materialService = new MaterialService(materialDAOMySQL);
		Material material = new Material();
		Jewelry jewelry = new Jewelry();
		try {
			do {
				System.out.println("INSERT в таблицу Material - 1");
				System.out.println("INSERT в таблицу Jewelry - 2");
				System.out.println("SELECT списка материалов - 3");
				System.out.println("UPDATE таблицы Material - 4");
				System.out.println("UPDATE таблицы Jewelry - 5");			
				System.out.println("SELECT по указанному материалу - 6");

				int choice = inScanner.nextInt();
				switch(choice) {
		 		case 1:

		 			inScanner = new Scanner(System.in);
		 			System.out.println("Введите название для материала: ");
		 			String title = inScanner.nextLine();
		 			material.setMaterialTitle(title); //Установка названия материала
		 			
		 			inScanner = new Scanner(System.in);
		 			System.out.println("Введите описание для материала: ");
		 			String description = inScanner.nextLine();
		 			material.setMaterialDescription(description);//Установка описания материала
		 			materialService.createMaterial(material); //Добавление материала в БД
		 			break;	 			
		 		case 2:
		 			System.out.println("Введите название изделия: ");
					inScanner = new Scanner(System.in);
		 			String jewelry_title = inScanner.nextLine();		 			
		 			jewelry.setJewelryTitle(jewelry_title); //Установка названия изделия
		 			
		 			System.out.println("Введите цену изделия: ");
		 			inScanner = new Scanner(System.in);
		 			float price = inScanner.nextFloat();
		 			jewelry.setPrice(price); //Установка цены изделия
		 			
		 			System.out.println("Введите id_material: ");
		 			inScanner = new Scanner(System.in);
		 			int id_material = inScanner.nextInt();
		 			jewelry.setIDMaterial(id_material); //Установка id-материала изделия
		 			jewelryService.createJewelry(jewelry); //Добавление изделия в БД 			
		 			break;
		 		case 3:
		 			//Получение списка материалов и вывод их на экран
		 			ArrayList<Material> materialArrayList  = materialService.getAllMaterial();
		 			for (Material materialList : materialArrayList) {
		 				System.out.println(materialList);
		 			}
		 			break;
		 		case 4:
		 			System.out.println("Введите ID материала для изменения: ");
		 			inScanner = new Scanner(System.in);
		 			int id = inScanner.nextInt();
		 			
					System.out.println("Введите новое «Название» материала: ");
					inScanner = new Scanner(System.in);
			 		String newTitle = inScanner.nextLine();
			 		material.setMaterialTitle(newTitle);
			 		
					System.out.println("Введите новое «Описание» материала: ");
					inScanner = new Scanner(System.in);
			 		String newDescription = inScanner.nextLine();
			 		material.setMaterialDescription(newDescription);
			 		materialService.updateMaterial(material, id); //Обновление данных В БД
			 		break;
		 		case 5:
		 			System.out.println("Введите ID изделия для изменения: ");
		 			inScanner = new Scanner(System.in);
		 			int id_jewelry = inScanner.nextInt();
		 			
		 			System.out.println("Введите новое «Название» изделия: ");
					inScanner = new Scanner(System.in);
			 		String newJewelry = inScanner.nextLine();
			 		jewelry.setJewelryTitle(newJewelry);
			 		
			 		System.out.println("Введите новую «Цену» изделия: ");
					inScanner = new Scanner(System.in);
			 		float newPrice = inScanner.nextFloat();
			 		jewelry.setPrice(newPrice);
			 		
			 		System.out.println("Введите новое «ID-атериала» изделия: ");
					inScanner = new Scanner(System.in);
			 		int newID = inScanner.nextInt();
			 		jewelry.setIDMaterial(newID);
			 		
			 		jewelryService.updateJewelry(jewelry, id_jewelry); //Обновление данных В БД
		 			break;
		 		case 6:
		 			//Вывод списка изделий по заданному материалу
		 			System.out.println("Введите материал для поиска: ");
		 			inScanner = new Scanner(System.in);
		 			String find_material = inScanner.nextLine();	 			
		 			ArrayList<Jewelry> jewelryArrayList  = jewelryService.getJewelryByMaterial(find_material);
		 			for (Jewelry jw : jewelryArrayList) {
		 				System.out.println(jw);
		 			}
		 			break;
		 		default: System.out.println("Такого действий нет!");
		 			break;
			}
				System.out.println();
				inScanner = new Scanner(System.in);
		 		System.out.print("Продолжить?(1/0) ");
		 		int exit = inScanner.nextInt();
		 		System.out.println();
		 		if(exit == 0) {
		 			System.out.println("Программа завершена!");
		 			Runtime.getRuntime().exit(0); //Выход из программы
		 		}
		 	} while(true);		 		 				 	
		 	}catch (Exception ex) { //Обработка исключений
		 		System.out.printf("Ошибка!! - %d", ex.getMessage());
		 	}
		}
	}

