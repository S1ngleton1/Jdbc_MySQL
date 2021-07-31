package Java_lb_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String dbHost = "localhost:3306"; //IP компьтера, на котором установлен сервер СУБД
	private static final String dbName = "jewelry?characterEncoding=utf8"; //Название БД с указанием кодировки
	private static final String dbUser = "root"; //Пользователь
	private static final String dbPassword = ""; //Пароль
	
	private static String dbUrl = "jdbc:mysql://" + dbHost + "/" + dbName; //Строка подключения
	public static Connection getConnection() throws SQLException {
		try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();//Загрузка драйвера
		} catch (Exception e) {//Обработка исключений
			e.printStackTrace();
		throw new SQLException("Can't get mysql driver");
		}
		Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);//Соединение с БД
		connection.setAutoCommit(false);//Доступ к управлению транзакциями
		return connection;
	}	
}
