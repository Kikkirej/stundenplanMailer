package net.kikkirej.stundenplanmailer.model;

import java.util.ArrayList;

public class Tag {
	private ArrayList<Stunde> stunden = new ArrayList<Stunde>();
	
	public ArrayList<Stunde> getStunden(){
		return stunden;
	}
	
	public void addStunde(Stunde stunde){
		stunden.add(stunde);
	}
	
	@Override
	public String toString() {
		String string = "";
		for (Stunde stunde:stunden) {
			string += stunde.getStartzeit() + " - " + stunde.getEndzeit() + "\t";
			string += stunde.getFach() + "\n";
		}
		return string;
	}

}
