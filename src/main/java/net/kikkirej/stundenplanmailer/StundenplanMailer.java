package net.kikkirej.stundenplanmailer;

import org.apache.log4j.Logger;

import net.kikkirej.stundenplanmailer.model.StundenPlanMail;

import net.kikkirej.stundenplanmailer.model.Stundenplan;
import net.kikkirej.stundenplanmailer.model.Tag;
import net.kikkirej.stundenplanmailer.model.WochenArt;

public class StundenplanMailer {
	
	private Logger logger = Logger.getLogger(StundenplanMailer.class);
	
	public static void main(String[] args) {
		new StundenplanMailer().run(args);
	}

	private void run(String[] args) {
		logger.info("Stundenplan-Mailer gestartet.");
		PropertiesSingleton propertiesSingleton = PropertiesSingleton.getInstance();
		String pfadZurGeradenCSV = propertiesSingleton.getProperty("geradeWoche");
		String pfadZurUngeradenCSV = propertiesSingleton.getProperty("ungeradeWoche");
		Stundenplan geradeWoche = new StundenplanGenerator(pfadZurGeradenCSV, WochenArt.GERADE).getStundenplan();
		Stundenplan ungeradeWoche = new StundenplanGenerator(pfadZurUngeradenCSV, WochenArt.UNGERADE).getStundenplan();
		Stundenplan[] stundenPlaene = {geradeWoche, ungeradeWoche};
		TagesErmittlung tagesErmittlung = new TagesErmittlung(stundenPlaene);
		Tag tag = tagesErmittlung.getTag();
		
		if (tag != null) {
			System.out.println(tag.toString());
			new StundenPlanMail(tag);
		}
	}
}
