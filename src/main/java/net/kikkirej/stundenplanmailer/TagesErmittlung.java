package net.kikkirej.stundenplanmailer;

import java.util.Calendar;
import java.util.Date;

import net.kikkirej.stundenplanmailer.model.Stundenplan;
import net.kikkirej.stundenplanmailer.model.Tag;
import net.kikkirej.stundenplanmailer.model.WochenArt;

public class TagesErmittlung {

	private Stundenplan[] stundenPlaene;
	private Tag tag;

	public Tag getTag() {
		return tag;
	}

	public TagesErmittlung(Stundenplan[] stundenPlaene) {
		this.stundenPlaene = stundenPlaene;
		WochenArt wochenArt = getWochenArt();
		Stundenplan spezifischenStundenPlanErmitteln = spezifischenStundenPlanErmitteln(wochenArt);
		int aktuellenWochenTagErmitteln = aktuellenWochenTagErmitteln();
		tag = stundenPlanTagErmitteln(aktuellenWochenTagErmitteln, spezifischenStundenPlanErmitteln);
	}

	private Tag stundenPlanTagErmitteln(int aktuellenWochenTagErmitteln, Stundenplan stundenPlan) {
		switch (aktuellenWochenTagErmitteln) {
		case Calendar.MONDAY:
			return stundenPlan.getMontag();
		case Calendar.TUESDAY:
			return stundenPlan.getDienstag();
		case Calendar.WEDNESDAY:
			return stundenPlan.getMittwoch();
		case Calendar.THURSDAY:
			return stundenPlan.getDonnerstag();
		case Calendar.FRIDAY:
			return stundenPlan.getFreitag();
		case Calendar.SATURDAY:
			return stundenPlan.getSamstag();
		default:
			return null;
		}
	}

	private WochenArt getWochenArt() {
		Calendar cal = getCalendar();
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		if(week%2 == 0){
			return WochenArt.GERADE;
		}else{
			return WochenArt.UNGERADE;
		}
	}

	private Calendar getCalendar() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal;
	}

	private Stundenplan spezifischenStundenPlanErmitteln(WochenArt wochenArt) {
		for(Stundenplan stundenPlan:stundenPlaene){
			if(stundenPlan.getWochenArt().equals(wochenArt)){
				return stundenPlan;
			}
		}
		return null;
	}

	private int aktuellenWochenTagErmitteln() {
		Calendar calendar = getCalendar();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
