package net.kikkirej.stundenplanmailer.model;

public class Stundenplan {
	private Tag montag, dienstag, mittwoch, donnerstag, freitag, samstag;
	private WochenArt wochenArt;
	
	public Tag getMontag() {
		return montag;
	}
	
	public void setMontag(Tag montag) {
		this.montag = montag;
	}
	
	public Tag getDienstag() {
		return dienstag;
	}
	
	public void setDienstag(Tag dienstag) {
		this.dienstag = dienstag;
	}
	
	public Tag getMittwoch() {
		return mittwoch;
	}
	
	public void setMittwoch(Tag mittwoch) {
		this.mittwoch = mittwoch;
	}
	
	public Tag getDonnerstag() {
		return donnerstag;
	}
	
	public void setDonnerstag(Tag donnerstag) {
		this.donnerstag = donnerstag;
	}
	
	public Tag getFreitag() {
		return freitag;
	}
	
	public void setFreitag(Tag freitag) {
		this.freitag = freitag;
	}
	
	public Tag getSamstag() {
		return samstag;
	}
	
	public void setSamstag(Tag samstag) {
		this.samstag = samstag;
	}
	
	public WochenArt getWochenArt() {
		return wochenArt;
	}
	
	public void setWochenArt(WochenArt wochenArt) {
		this.wochenArt = wochenArt;
	}
	

}
