package model;

public class GreaseSample {
	//variables
	private int sampleId;
	private int barcodeId;
	private double mass;
	private int PPM;
	private String color;
	private String date;
	private String time;
	//constructors
	public GreaseSample(int sampleId, int barcodeId, double mass, int pPM, String color, String date, String time) {
		super();
		this.sampleId = sampleId;
		this.barcodeId = barcodeId;
		this.mass = mass;
		PPM = pPM;
		this.color = color;	
		this.date = date;
		this.time = time;
	}
	
	public GreaseSample(int barcodeId, double mass, int pPM, String color, String date, String time) {
		super();
		this.barcodeId = barcodeId;
		this.mass = mass;
		PPM = pPM;
		this.color = color;
		this.date = date;
		this.time = time;
	}

	

	

	//getters
	public int getSampleId() {
		return sampleId;
	}
	public int getBarcodeId() {
		return barcodeId;
	}
	public double getMass() {
		return mass;
	}
	public int getPPM() {
		return PPM;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}
	
	//setters
	public void setSampleId(int sampleId) {
		this.sampleId = sampleId;
	}
	
	public void setBarcodeId(int barcodeId) {
		this.barcodeId = barcodeId;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public void setPPM(int pPM) {
		PPM = pPM;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
