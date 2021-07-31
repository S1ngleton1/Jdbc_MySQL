package Java_lb_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import com.mysql.jdbc.Statement;

public class MaterialDAOMySQL {
	//������ �� ���������� ������
	private static final String SQL_UPDATE_MATERIAL = "UPDATE material SET " + 
			"material_description = ?, material_title = ? WHERE id_material = ?";
	//������ �� ���������� ������
	private static final String SQL_INSERT_INTO_MATERIAL = "INSERT INTO " + 
			"material(material_description,material_title) VALUES(?,?)";
	//������ �� ������� ������
	private static final String SQL_SELECT_ALL_MATERIAL = "SELECT * FROM material"; 
	//����������� ���������� � ������ �� ���������� ������
	private void setInsertStatementParameters(Material material, PreparedStatement prStatement) 
			throws SQLException {
				prStatement.setString(1, material.getMaterialDescription());
				prStatement.setString(2, material.getMaterialTitle());
			}
	//����������� ���������� � ������ �� ���������� ������
	private void setUpdateStatementParameters(Material material, PreparedStatement prStatement, int index)
			throws SQLException {		
					prStatement.setString(1, material.getMaterialDescription());
					prStatement.setString(2, material.getMaterialTitle());
					prStatement.setInt(3, index);
			}
	//��������� ���������� �� ��������������� ������ � ��������� �� � ������ material
	private Material getMaterial(ResultSet rs) throws SQLException {
		Material material = new Material();	
		material.setIdMaterial(rs.getInt("id_material"));
		material.setMaterialDescription(rs.getString("material_description"));
		material.setMaterialTitle(rs.getString("material_title"));
		return material;
	}
	
	private ArrayList<Material> getMaterials(ResultSet rs) throws SQLException {
		 //�������� ������ �������� Material
		ArrayList<Material> material = new ArrayList<Material>();
		while (rs.next())
			//���������� � ������ ���������� ���������� �� ��������������� ������
			material.add(getMaterial(rs));
		return material;
	}
	//����� ���������� ������ � ��
	public void create(Connection connection, Material material) throws SQLException	{
		//�������� ��������������� ������� �� ���������� ������
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_INSERT_INTO_MATERIAL, Statement.RETURN_GENERATED_KEYS);
		//����������� ���������� � ������
		setInsertStatementParameters(material, prStatement);
		//���������� �������
		prStatement.executeUpdate();
		//�������� ������� Statement
		prStatement.close();
	}
	//����� ���������� ������ � ��
	public void update(Connection connection, Material material,int index) throws SQLException 	{
		//�������� ��������������� ������� �� ���������� ������ 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_UPDATE_MATERIAL);	
		//����������� ���������� � ������
		setUpdateStatementParameters(material, prStatement,index);
		//���������� �������
		prStatement.executeUpdate();
		//�������� ������� Statement
		prStatement.close();
	}
	//����� ��� ������� ������ �� ��
	public ArrayList<Material> getAllMaterial(Connection connection) throws SQLException {
		//�������� ��������������� ������� �� ������� ������ 
		PreparedStatement prStatement =
		connection.prepareStatement(SQL_SELECT_ALL_MATERIAL);
		//���������� �������
		ResultSet rs = prStatement.executeQuery();
		//��������� ���������� ������ �� ��������������� ������ � ������
		ArrayList<Material> materialaArrayList = getMaterials(rs);
		//�������� ������� Statement
		prStatement.close();
		return materialaArrayList;
	}	
}
