package com.java.testVegan.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.testVegan.entity.OggettiNelPacco;
import com.java.testVegan.entity.constanti.Constanti;
import com.java.testVegan.repository.RepositoryTestVegan;

@Service
public class ServiceTestVeganImpl implements ServiceTestVegan{

	@Autowired
	RepositoryTestVegan repoTestVegan;

	@Autowired
	static Constanti constante;

	@Override
	public List<String> getListMockPacchiService() {
		List<OggettiNelPacco> pacco = repoTestVegan.getListMockPacchiRepo();
		List<String> risultato = new ArrayList<String>();
		try {
			boolean checkVerificaNumberOfItem = verificoNumberOfItem(pacco);
			risultato = logicaOggetti(pacco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return risultato;
	}
	
	/**
	 * Il metodo logicaOggetti, effettua un sort di una lista di elementi in base al costo e al peso
	 * dalla lista viene effettato un filtro che prende gli elementi della lista che hanno il maggior costo
	 * viene effettuato un check sul peso di ogni elemento e viene restituita una lista di elementi filtrati in base al prezzo
	 * più alto
	 */
	public List<String> logicaOggetti(List<OggettiNelPacco> result) throws Exception{
		int maxPeso = constante.getPesomassimoconstante();
		Comparator<OggettiNelPacco> compareByCosto = Comparator
                .comparing(OggettiNelPacco::getCosto).thenComparing(OggettiNelPacco::getPeso);
		List<OggettiNelPacco> nuova = result.stream()
                .sorted(compareByCosto)
                .collect(Collectors.toList());
		Collections.reverse(nuova);
		List<OggettiNelPacco> listaFiltrata = new ArrayList();
		double risultato = 0;
		int checkIdPrecedente = 0;
		boolean checkElemento = false;
		boolean checkTotale = false;
		boolean esciDalLoop = false;
		double checkRisultato = 0;
		//di questa lista devo chekkare il peso se è minore di constante.getPeso()
		
		while(!esciDalLoop) {
			for (OggettiNelPacco oggettiNelPacco : nuova) {
				checkIdPrecedente = oggettiNelPacco.getId();
				checkElemento = maxPeso <= oggettiNelPacco.getPeso() ? true : false;
				if(!checkElemento) {
				
					risultato = risultato + oggettiNelPacco.getPeso();
					if(risultato<constante.getPesomassimoconstante()) {
						checkRisultato = risultato;
					}
					checkTotale = maxPeso <= risultato ? true: false;
					if(checkTotale) {
						continue;
					}
				
					listaFiltrata.add(oggettiNelPacco);
				}
			}
			esciDalLoop = true;
		}
		
		
		boolean checkVerificoIlMaxPesoDiOgniItem = verificoIlMaxPesoDiOgniItem(listaFiltrata);
		boolean checkVerificoIlMaxPrezzoDiOgniItem = verificoIlMaxPrezzoDiOgniItem(listaFiltrata);
		boolean checkVerificoMaxPesoPaccoTotale = verificoMaxPesoPaccoTotale(checkRisultato);

		
		List<String> risultatoFinale = new ArrayList<String>();
		String testo = "The maximum weight the package can hold : " + constante.getPesomassimoconstante() +"kg";
		risultatoFinale.add(testo);
		String id = "ID: ";
		for (OggettiNelPacco prendiId : listaFiltrata) {
			id= id.concat(String.valueOf(prendiId.getId()));
			risultatoFinale.add(id);
			id="ID: ";
		}
		return risultatoFinale;
	}
	
	
	public boolean verificoNumberOfItem(List<OggettiNelPacco> result) throws Exception {
		boolean check = false;
		try {
			check= result.size() <= 15 ? true : false;
		}catch(Exception e) {
			throw new Exception("La lista contiene più di 15 o più pacchi");
		}finally {
			return check;
		}
	
	}
	/**
	 * 
	 * Verifico nel metodo il peso massimo di ogni elemento della lista
	 */
	public boolean verificoIlMaxPesoDiOgniItem(List<OggettiNelPacco> listaFiltrata) throws Exception {
		boolean check = false;

			for (OggettiNelPacco oggettiNelPacco : listaFiltrata) {
				if(oggettiNelPacco.getPeso()>=constante.getPesomassimoconstante()) {
					check=true;
					throw new Exception("Il peso per l'id : " + oggettiNelPacco.getId() + "è maggiore al peso massimo");
				}
			}
		return check;
	}
	
	/**
	 * Verifico  nel metodo il max Prezzo di ogni elemento della lista se è uguale o maggiore a constant.getCostoMassimoConstante
	 */
	public boolean verificoIlMaxPrezzoDiOgniItem(List<OggettiNelPacco> listaFiltrata) throws Exception {
		boolean check = false;
			for (OggettiNelPacco oggettiNelPacco : listaFiltrata) {
				if(oggettiNelPacco.getCosto()>=constante.getCostomassimoconstante()) {
					check=true;
					throw new Exception("Il costo per l'id : " + oggettiNelPacco.getId() + "è maggiore a " + constante.getCostomassimoconstante());
				}
			}
		return check;
	}

	/**
	 * Verifico nel metodo  il max Peso totale del pacco
	 */
	public boolean verificoMaxPesoPaccoTotale(Double risultato) throws Exception {
		boolean check = false;
				if(risultato>constante.getPesomassimoconstante()) {
					throw new Exception("il Peso finale del pacco è maggiore a " + constante.getPesomassimoconstante());
				}
		return check;
	}



}
