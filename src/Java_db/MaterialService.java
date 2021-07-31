package Java_lb_4;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialService {
	//Инизиализация объекта типа MaterialDAOMySQL
	private MaterialDAOMySQL materialDAO;
	//Конструктор
	MaterialService(MaterialDAOMySQL materialDAO){
		this.materialDAO = materialDAO;
	}
	//Добавление материала в БД
	public void createMaterial(Material material) throws SQLException {
		Connection connection = DBConnection.getConnection();//Вызов статического метода из класса DBConnection
		try {
			materialDAO.create(connection, material); //Вызов метода для добавления
			connection.commit(); //Подтверждение выполнения операции
		} catch (SQLException e) {
			connection.rollback(); //Откат операции
		throw e;
		}
	}
	//Обновления информации об материале
	public void updateMaterial(Material material,int index) throws SQLException {
		Connection connection = DBConnection.getConnection(); //Соединение с БД
		try {
			materialDAO.update(connection, material,index); //Вызов метода для обновления
			connection.commit(); //Подтверждение выполнения операции
		} catch (SQLException e) { 
			connection.rollback(); //Откат операции
		throw e;
		}
	}
	//Получения списка материалов
	public ArrayList<Material> getAllMaterial() throws SQLException {
		Connection connection = DBConnection.getConnection(); //Соединение с БД
		try {
			ArrayList<Material> materialaArrayList = materialDAO.getAllMaterial(connection); //Занесение в список
			connection.commit(); //Подтверждение выполнения операции
		return materialaArrayList;
		} catch (SQLException e) {
			connection.rollback(); //Откат операции
		throw e;
		}
	}
}
