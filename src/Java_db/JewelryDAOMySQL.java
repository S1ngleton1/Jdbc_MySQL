package Java_lb_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import com.mysql.jdbc.Statement;

public class JewelryDAOMySQL {
	//������ �� ���������� ������
	private static final String SQL_UPDATE_JEWELRY = "UPDATE jewelry_type SET " + 
				"jewelry_title = ?, jewelry_price = ?, id_material = ? WHERE id_type = ?";
	//������ �� ���������� ������
	private static final String SQL_INSERT_INTO_JEWELRY = "INSERT INTO " + 
				"jewelry_type(jewelry_title,jewelry_price,id_material) VALUES(?,?,?)";
	//������ �� ������� ������
	private static final String SQL_SELECT_BY_MATERIAL = "SELECT " + 
			"id_type, jewelry_title, jewelry_price, jewelry_type.id_material " + 
			"FROM jewelry_type INNER JOIN material on (jewelry_type.id_material = material.id_material) " + 
			"WHERE material_title = ?";
	
	//����������� ���������� � ������ �� ���������� ������
	private void setInsertStatementParameters(Jewelry jewelry, PreparedStatement
			prStatement) throws SQLException {
			prStatement.setString(1, jewelry.getJewelryTitle());
			prStatement.setFloat(2, jewelry.getPrice());
			prStatement.setInt(3, jewelry.getIDMaterial());
		}
	
	//����������� ���������� � ������ �� ���������� ������
	private void setUpdateStatementParameters(Jewelry jewelry, PreparedStatement
			prStatement, int index) throws SQLException {
			prStatement.setString(1, jewelry.getJewelryTitle());
			prStatement.setFloat(2, jewelry.getPrice());
			prStatement.setInt(3, jewelry.getIDMaterial());
			prStatement.setInt(4, index);
		}
	
	//��������� ���������� �� ��������������� ������ � ��������� �� � ������ jewelry
	private Jewelry getJewelry(ResultSet rs) throws SQLException {
			Jewelry jewelry = new Jewelry();
			jewelry.setId(rs.getInt("id_type"));
			jewelry.setJewelryTitle(rs.getString("jewelry_title"));
			jewelry.setPrice(rs.getFloat("jewelry_price"));
			jewelry.setIDMaterial(rs.getInt("id_material"));
			return jewelry;
		}
	
	private ArrayList<Jewelry> getJewelries(ResultSet rs) throws SQLException {
		ArrayList<Jewelry> jewelry = new ArrayList<Jewelry>(); //�������� ������ �������� Jewelry
		while (rs.next())
			jewelry.add(getJewelry(rs)); //���������� � ������ ���������� ���������� �� ��������������� ������
		return jewelry;
		}
	
	//����� ���������� ������ � ��
	public void create(Connection connection, Jewelry jewelry) throws SQLException	{
		//�������� ��������������� ������� �� ���������� ������
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_INSERT_INTO_JEWELRY, Statement.RETURN_GENERATED_KEYS);
		//����������� ���������� � ������
		setInsertStatementParameters(jewelry, prStatement);
		//���������� �������
		prStatement.executeUpdate();
		//�������� ������� Statement
		prStatement.close();
	}
	
	//����� ���������� ������ � ��
	public void update(Connection connection, Jewelry jewelry,int index) throws SQLException 	{
		//�������� ��������������� ������� �� ���������� ������ 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_UPDATE_JEWELRY);	
		//����������� ���������� � ������
		setUpdateStatementParameters(jewelry, prStatement,index);
		//���������� �������
		prStatement.executeUpdate();
		//�������� ������� Statement
		prStatement.close();
	}
	
	//����� ��� ������� ������ �� ��
	public ArrayList<Jewelry> getByMaterial(Connection connection, String material) throws SQLException {
		//�������� ��������������� ������� �� ������� ������ 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_SELECT_BY_MATERIAL);
		//����������� ��������� � ������
		prStatement.setString(1, material);
		//���������� �������
		ResultSet rs = prStatement.executeQuery();
		//��������� ���������� ������ �� ��������������� ������ � ������
		ArrayList<Jewelry> jewelry = getJewelries(rs); 
		//�������� ������� Statement
		prStatement.close();
		return jewelry;
	}
}
