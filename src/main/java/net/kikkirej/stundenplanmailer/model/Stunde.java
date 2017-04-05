package net.kikkirej.stundenplanmailer.model;

public class Stunde {
	String fach, raum, dozent, startzeit, endzeit;

	public String getFach() {
		return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}

	public String getDozent() {
		return dozent;
	}

	public void setDozent(String dozent) {
		this.dozent = dozent;
	}

	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
	}

	public String getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(String endzeit) {
		this.endzeit = endzeit;
	}
}
