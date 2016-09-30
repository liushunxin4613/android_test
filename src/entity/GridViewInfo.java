package entity;

public class GridViewInfo {
	private int text;
	private int color;
	private int img;
	public int getText() {
		return text;
	}
	public void setText(int text) {
		this.text = text;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public GridViewInfo(int text, int color, int img) {
		super();
		this.text = text;
		this.color = color;
		this.img = img;
	}
	public GridViewInfo() {
		super();
	}
}
