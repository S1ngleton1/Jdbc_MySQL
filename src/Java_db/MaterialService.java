package Java_lb_4;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialService {
	//������������� ������� ���� MaterialDAOMySQL
	private MaterialDAOMySQL materialDAO;
	//�����������
	MaterialService(MaterialDAOMySQL materialDAO){
		this.materialDAO = materialDAO;
	}
	//���������� ��������� � ��
	public void createMaterial(Material material) throws SQLException {
		Connection connection = DBConnection.getConnection();//����� ������������ ������ �� ������ DBConnection
		try {
			materialDAO.create(connection, material); //����� ������ ��� ����������
			connection.commit(); //������������� ���������� ��������
		} catch (SQLException e) {
			connection.rollback(); //����� ��������
		throw e;
		}
	}
	//���������� ���������� �� ���������
	public void updateMaterial(Material material,int index) throws SQLException {
		Connection connection = DBConnection.getConnection(); //���������� � ��
		try {
			materialDAO.update(connection, material,index); //����� ������ ��� ����������
			connection.commit(); //������������� ���������� ��������
		} catch (SQLException e) { 
			connection.rollback(); //����� ��������
		throw e;
		}
	}
	//��������� ������ ����������
	public ArrayList<Material> getAllMaterial() throws SQLException {
		Connection connection = DBConnection.getConnection(); //���������� � ��
		try {
			ArrayList<Material> materialaArrayList = materialDAO.getAllMaterial(connection); //��������� � ������
			connection.commit(); //������������� ���������� ��������
		return materialaArrayList;
		} catch (SQLException e) {
			connection.rollback(); //����� ��������
		throw e;
		}
	}
}
