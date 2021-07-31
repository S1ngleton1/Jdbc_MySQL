package Java_lb_4;

public class Jewelry {
	private int id;
	private String jewelry_title;
	private float price;
	private int id_material;
	
	public void setId(int id) { //Установка id
		this.id = id;
	}
	public int getId(){//Получение id
		return this.id;
	}	
	public void setJewelryTitle(String jewelry_title){//Установка названия
		this.jewelry_title = jewelry_title;
	}
	public String getJewelryTitle() {//Получение названия
		return this.jewelry_title;
	}
	public void setPrice(float price){//Установка цены
		this.price = price;
	}
	public float getPrice() {//Получение цены
		return this.price;
	}
	public void setIDMaterial(int id_material){//Установка id-материала
		this.id_material = id_material;
	}
	public int getIDMaterial() {//Получение id-материала
		return this.id_material;
	}
	@Override
	public String toString() { //Переопределения метода toString
		return "Jewelry [ID = " + id + ", Изделие: " + jewelry_title +
				", цена = " + price + ", ID-материала = " + id_material + " ]";
	}
}
