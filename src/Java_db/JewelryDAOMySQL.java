package Java_lb_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import com.mysql.jdbc.Statement;

public class JewelryDAOMySQL {
	//Запрос на обновление данных
	private static final String SQL_UPDATE_JEWELRY = "UPDATE jewelry_type SET " + 
				"jewelry_title = ?, jewelry_price = ?, id_material = ? WHERE id_type = ?";
	//Запрос на добавление данных
	private static final String SQL_INSERT_INTO_JEWELRY = "INSERT INTO " + 
				"jewelry_type(jewelry_title,jewelry_price,id_material) VALUES(?,?,?)";
	//Запрос на выборку данных
	private static final String SQL_SELECT_BY_MATERIAL = "SELECT " + 
			"id_type, jewelry_title, jewelry_price, jewelry_type.id_material " + 
			"FROM jewelry_type INNER JOIN material on (jewelry_type.id_material = material.id_material) " + 
			"WHERE material_title = ?";
	
	//Подстановка параметров в запрос на добавление данных
	private void setInsertStatementParameters(Jewelry jewelry, PreparedStatement
			prStatement) throws SQLException {
			prStatement.setString(1, jewelry.getJewelryTitle());
			prStatement.setFloat(2, jewelry.getPrice());
			prStatement.setInt(3, jewelry.getIDMaterial());
		}
	
	//Подстановка параметров в запрос на обновление данных
	private void setUpdateStatementParameters(Jewelry jewelry, PreparedStatement
			prStatement, int index) throws SQLException {
			prStatement.setString(1, jewelry.getJewelryTitle());
			prStatement.setFloat(2, jewelry.getPrice());
			prStatement.setInt(3, jewelry.getIDMaterial());
			prStatement.setInt(4, index);
		}
	
	//Получение параметров из результирующего набора и занесение их в объект jewelry
	private Jewelry getJewelry(ResultSet rs) throws SQLException {
			Jewelry jewelry = new Jewelry();
			jewelry.setId(rs.getInt("id_type"));
			jewelry.setJewelryTitle(rs.getString("jewelry_title"));
			jewelry.setPrice(rs.getFloat("jewelry_price"));
			jewelry.setIDMaterial(rs.getInt("id_material"));
			return jewelry;
		}
	
	private ArrayList<Jewelry> getJewelries(ResultSet rs) throws SQLException {
		ArrayList<Jewelry> jewelry = new ArrayList<Jewelry>(); //Создание списка объектов Jewelry
		while (rs.next())
			jewelry.add(getJewelry(rs)); //Добавление в список полученных параметров из результирующего набора
		return jewelry;
		}
	
	//Метод добавление данных в БД
	public void create(Connection connection, Jewelry jewelry) throws SQLException	{
		//Создание подготовленного запроса на добавление данных
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_INSERT_INTO_JEWELRY, Statement.RETURN_GENERATED_KEYS);
		//Подстановка параметров в запрос
		setInsertStatementParameters(jewelry, prStatement);
		//Выполнение запроса
		prStatement.executeUpdate();
		//Закрытие объекта Statement
		prStatement.close();
	}
	
	//Метод обновления данных В БД
	public void update(Connection connection, Jewelry jewelry,int index) throws SQLException 	{
		//Создание подготовленного запроса на обновление данных 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_UPDATE_JEWELRY);	
		//Подстановка параметров в запрос
		setUpdateStatementParameters(jewelry, prStatement,index);
		//Выполнение запроса
		prStatement.executeUpdate();
		//Закрытие объекта Statement
		prStatement.close();
	}
	
	//Метод для выборки данных из БД
	public ArrayList<Jewelry> getByMaterial(Connection connection, String material) throws SQLException {
		//Создание подготовленного запроса на выборку данных 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_SELECT_BY_MATERIAL);
		//Подстановка параметра в запрос
		prStatement.setString(1, material);
		//Выполнение запроса
		ResultSet rs = prStatement.executeQuery();
		//Занесение полученных данных из результирующего набора в список
		ArrayList<Jewelry> jewelry = getJewelries(rs); 
		//Закрытие объекта Statement
		prStatement.close();
		return jewelry;
	}
}
