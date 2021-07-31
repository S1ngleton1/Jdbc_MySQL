package Java_lb_4;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class JewelryService {
	private JewelryDAOMySQL jewelryDAO; //������������� ������� ���� JewelryDAOMySQL
	//�����������
	JewelryService(JewelryDAOMySQL jewelryDAO){
		this.jewelryDAO = jewelryDAO;
	}
	//���������� ������� � ��
	public void createJewelry(Jewelry jewelry) throws SQLException {
		Connection connection = DBConnection.getConnection(); //����� ������������ ������ �� ������ DBConnection
		try {
			jewelryDAO.create(connection, jewelry);//����� ������ ��� ����������
			connection.commit(); //������������� ���������� ��������
		} catch (SQLException e) {
			connection.rollback(); //����� ��������
		throw e;
		}
	}
	//���������� ���������� �� �������
	public void updateJewelry(Jewelry jewelry,int index) throws SQLException {
		Connection connection = DBConnection.getConnection(); //����� ������������ ������ �� ������ DBConnection
		try {
			jewelryDAO.update(connection, jewelry,index); //����� ������ ��� ����������
			connection.commit(); //������������� ���������� ��������
		} catch (SQLException e) {
			connection.rollback(); //����� ��������
		throw e;
		}
	}
	//��������� ������ �������
	public ArrayList<Jewelry> getJewelryByMaterial(String material) throws SQLException {
		Connection connection = DBConnection.getConnection(); //���������� � ��
		try {
			ArrayList<Jewelry> jewelry = jewelryDAO.getByMaterial(connection, material); //��������� � ������
			connection.commit(); //������������� ���������� ��������
		return jewelry;
		} catch (SQLException e) {
			connection.rollback(); //����� ��������
		throw e;
		}
	}
}
