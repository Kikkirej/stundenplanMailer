package net.kikkirej.stundenplanmailer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import net.kikkirej.stundenplanmailer.model.Stunde;
import net.kikkirej.stundenplanmailer.model.Stundenplan;
import net.kikkirej.stundenplanmailer.model.Tag;
import net.kikkirej.stundenplanmailer.model.WochenArt;

public class StundenplanGenerator {
	
	Logger logger = Logger.getLogger(StundenplanGenerator.class);

	private Stundenplan plan = new Stundenplan();
	private File csvFile;
	private ArrayList<String> csvZeilen = new ArrayList<String>();
	private String[] zeiten;
	
	public StundenplanGenerator(String pfadZurCSV, WochenArt wochenArt) {
		plan.setWochenArt(wochenArt);
		csvFile = new File(pfadZurCSV);
		csvFileAuslesen();
		zeitenErmitteln();
		Tag montag = generateDay(csvZeilen.get(1));
		plan.setMontag(montag);
		Tag dienstag = generateDay(csvZeilen.get(2));
		plan.setDienstag(dienstag);
		Tag mittwoch = generateDay(csvZeilen.get(3));
		plan.setMittwoch(mittwoch);
		Tag donnerstag = generateDay(csvZeilen.get(4));
		plan.setDonnerstag(donnerstag);	
		Tag freitag = generateDay(csvZeilen.get(5));
		plan.setFreitag(freitag);
		Tag samstag = generateDay(csvZeilen.get(6));
		plan.setSamstag(samstag);
	}

	private void csvFileAuslesen() {
		try {
			FileReader fileReader = new FileReader(csvFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String dummy;
			dummy = bufferedReader.readLine();
			while(dummy != null){
				csvZeilen.add(dummy);
				dummy = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		} 
	}

	private void zeitenErmitteln() {
		String ersteZeile = csvZeilen.get(0);
		zeiten = ersteZeile.split(";");
	}

	private Tag generateDay(String datensatz) {
		if (datensatz.equals("")) {
			logger.debug("Keine Fächer gefunden.");
			
			return null;
		}
		
		String[] faecher = datensatz.split(";");
		Tag tag = new Tag();
		for (int i = 0; i < faecher.length; i++) {
			Stunde stunde = new Stunde();
			stunde.setFach(faecher[i]);
			String[] startUndEndzeit = zeiten[i].split("-");
			stunde.setStartzeit(startUndEndzeit[0]);
			stunde.setEndzeit(startUndEndzeit[1]);
			tag.addStunde(stunde);
		}
		return tag;
	}

	public Stundenplan getStundenplan() {
		return plan;
	}

}
