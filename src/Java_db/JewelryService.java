package Java_lb_4;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class JewelryService {
	private JewelryDAOMySQL jewelryDAO; //Инизиализация объекта типа JewelryDAOMySQL
	//Конструктор
	JewelryService(JewelryDAOMySQL jewelryDAO){
		this.jewelryDAO = jewelryDAO;
	}
	//Добавление изделия в БД
	public void createJewelry(Jewelry jewelry) throws SQLException {
		Connection connection = DBConnection.getConnection(); //Вызов статического метода из класса DBConnection
		try {
			jewelryDAO.create(connection, jewelry);//Вызов метода для добавления
			connection.commit(); //Подтверждение выполнения операции
		} catch (SQLException e) {
			connection.rollback(); //Откат операции
		throw e;
		}
	}
	//Обновления информации об изделии
	public void updateJewelry(Jewelry jewelry,int index) throws SQLException {
		Connection connection = DBConnection.getConnection(); //Вызов статического метода из класса DBConnection
		try {
			jewelryDAO.update(connection, jewelry,index); //Вызов метода для обновления
			connection.commit(); //Подтверждение выполнения операции
		} catch (SQLException e) {
			connection.rollback(); //Откат операции
		throw e;
		}
	}
	//Получения списка изделий
	public ArrayList<Jewelry> getJewelryByMaterial(String material) throws SQLException {
		Connection connection = DBConnection.getConnection(); //Соединение с БД
		try {
			ArrayList<Jewelry> jewelry = jewelryDAO.getByMaterial(connection, material); //Занесение в список
			connection.commit(); //Подтверждение выполнения операции
		return jewelry;
		} catch (SQLException e) {
			connection.rollback(); //Откат операции
		throw e;
		}
	}
}
