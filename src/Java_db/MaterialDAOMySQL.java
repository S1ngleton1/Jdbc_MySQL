package Java_lb_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import com.mysql.jdbc.Statement;

public class MaterialDAOMySQL {
	//Запрос на обновление данных
	private static final String SQL_UPDATE_MATERIAL = "UPDATE material SET " + 
			"material_description = ?, material_title = ? WHERE id_material = ?";
	//Запрос на добавление данных
	private static final String SQL_INSERT_INTO_MATERIAL = "INSERT INTO " + 
			"material(material_description,material_title) VALUES(?,?)";
	//Запрос на выборку данных
	private static final String SQL_SELECT_ALL_MATERIAL = "SELECT * FROM material"; 
	//Подстановка параметров в запрос на добавление данных
	private void setInsertStatementParameters(Material material, PreparedStatement prStatement) 
			throws SQLException {
				prStatement.setString(1, material.getMaterialDescription());
				prStatement.setString(2, material.getMaterialTitle());
			}
	//Подстановка параметров в запрос на обновление данных
	private void setUpdateStatementParameters(Material material, PreparedStatement prStatement, int index)
			throws SQLException {		
					prStatement.setString(1, material.getMaterialDescription());
					prStatement.setString(2, material.getMaterialTitle());
					prStatement.setInt(3, index);
			}
	//Получение параметров из результирующего набора и занесение их в объект material
	private Material getMaterial(ResultSet rs) throws SQLException {
		Material material = new Material();	
		material.setIdMaterial(rs.getInt("id_material"));
		material.setMaterialDescription(rs.getString("material_description"));
		material.setMaterialTitle(rs.getString("material_title"));
		return material;
	}
	
	private ArrayList<Material> getMaterials(ResultSet rs) throws SQLException {
		 //Создание списка объектов Material
		ArrayList<Material> material = new ArrayList<Material>();
		while (rs.next())
			//Добавление в список полученных параметров из результирующего набора
			material.add(getMaterial(rs));
		return material;
	}
	//Метод добавление данных в БД
	public void create(Connection connection, Material material) throws SQLException	{
		//Создание подготовленного запроса на добавление данных
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_INSERT_INTO_MATERIAL, Statement.RETURN_GENERATED_KEYS);
		//Подстановка параметров в запрос
		setInsertStatementParameters(material, prStatement);
		//Выполнение запроса
		prStatement.executeUpdate();
		//Закрытие объекта Statement
		prStatement.close();
	}
	//Метод обновления данных В БД
	public void update(Connection connection, Material material,int index) throws SQLException 	{
		//Создание подготовленного запроса на обновление данных 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_UPDATE_MATERIAL);	
		//Подстановка параметров в запрос
		setUpdateStatementParameters(material, prStatement,index);
		//Выполнение запроса
		prStatement.executeUpdate();
		//Закрытие объекта Statement
		prStatement.close();
	}
	//Метод для выборки данных из БД
	public ArrayList<Material> getAllMaterial(Connection connection) throws SQLException {
		//Создание подготовленного запроса на выборку данных 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_SELECT_ALL_MATERIAL);
		//Выполнение запроса
		ResultSet rs = prStatement.executeQuery();
		//Занесение полученных данных из результирующего набора в список
		ArrayList<Material> materialaArrayList = getMaterials(rs);
		//Закрытие объекта Statement
		prStatement.close();
		return materialaArrayList;
	}	
}
