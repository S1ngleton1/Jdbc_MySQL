package Java_lb_4;

public class Jewelry {
	private int id;
	private String jewelry_title;
	private float price;
	private int id_material;
	
	public void setId(int id) { //��������� id
		this.id = id;
	}
	public int getId(){//��������� id
		return this.id;
	}	
	public void setJewelryTitle(String jewelry_title){//��������� ��������
		this.jewelry_title = jewelry_title;
	}
	public String getJewelryTitle() {//��������� ��������
		return this.jewelry_title;
	}
	public void setPrice(float price){//��������� ����
		this.price = price;
	}
	public float getPrice() {//��������� ����
		return this.price;
	}
	public void setIDMaterial(int id_material){//��������� id-���������
		this.id_material = id_material;
	}
	public int getIDMaterial() {//��������� id-���������
		return this.id_material;
	}
	@Override
	public String toString() { //��������������� ������ toString
		return "Jewelry [ID = " + id + ", �������: " + jewelry_title +
				", ���� = " + price + ", ID-��������� = " + id_material + " ]";
	}
}
