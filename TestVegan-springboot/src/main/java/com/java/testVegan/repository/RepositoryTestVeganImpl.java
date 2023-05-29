package com.java.testVegan.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.testVegan.entity.OggettiNelPacco;


@Repository
public class RepositoryTestVeganImpl implements RepositoryTestVegan {

	
	/*
	 * Mokko le chiamate a db ho creato una lista di oggetti
	 * id - peso - costo
	 * 
	 */
	
	
	/**
	 * Nel metodo  getListMockPacchiRepo ritorno una lista che comprende gli oggetti
	 * su andrò a fare i controlli nel service come indicato nell'esempio del test il service ritornerà gli id 6,2 e 5 
	 */
	@Override
	public List<OggettiNelPacco> getListMockPacchiRepo() {
		List<OggettiNelPacco> result = new ArrayList<OggettiNelPacco>();
		result.add(new OggettiNelPacco(1, 85.31, 29));
		result.add(new OggettiNelPacco(2, 14.55, 74));
		result.add(new OggettiNelPacco(3, 3.98, 16));
		result.add(new OggettiNelPacco(4, 112.24, 55));
		result.add(new OggettiNelPacco(5, 63.69, 52));
		result.add(new OggettiNelPacco(6, 14.69, 75));
	
		return result;
	}

	
	
	
}
