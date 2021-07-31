package Java_lb_4;

public class Material {
	private int id_material;
	private String material_description;
	private String material_title;
	
	//Установка и получения значений
	public void setIdMaterial(int id) {
		this.id_material = id;
	}
	public int getIdMaterial() {
		return id_material;
	}
	
	public void setMaterialDescription(String description) {
		this.material_description = description;
	}
	public String getMaterialDescription() {
		return material_description;
	}
	
	public void setMaterialTitle(String title) {
		this.material_title = title;
	}
	public String getMaterialTitle() {
		return material_title;
	}
	
	//Переопределение метода toString
	@Override
	public String toString() {
		return "Material [ID-материала = " + id_material + ", материал: " + material_title + 
				", описание: " + material_description + " ]";
	}
}
