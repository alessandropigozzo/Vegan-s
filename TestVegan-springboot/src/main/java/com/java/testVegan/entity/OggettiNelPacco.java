package com.java.testVegan.entity;

public class OggettiNelPacco {

	public int id;
	public double peso;
	public int costo;
	
	public OggettiNelPacco() {
		
	}
	
	

	public OggettiNelPacco(int id, double peso, int costo) {
		super();
		this.id = id;
		this.peso = peso;
		this.costo = costo;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}
	

		
}