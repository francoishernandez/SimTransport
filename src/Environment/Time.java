package Environment;

import java.util.Random;

import mainPackage.Starter;

public class Time {

	// La simulation se déroule sur une journée
	byte hours;
	byte minutes;
	byte seconds;
	
	public Time(byte h, byte m){
		hours = h;
		minutes = m;
		seconds = (byte) 0;
	}
	
	public boolean equals(Time toCompare){
		return ( (hours==toCompare.getHours())&&(minutes==toCompare.getMinutes())&&(seconds==toCompare.getSeconds()) );
	}
	
	// POUR LA GENERATION ALEATOIRES DE PERSONNES ALLER-RETOUR
	
	static Random rand = new Random();
	// Génère une heure alétoire de départ du point d'entrée 
	public static Time randomBegin(){
		// L'heure de début est distribuée autour de 10h du matin
		int randhour = (int)Math.floor(rand.nextGaussian()*Starter.sigmaBeginTime+Starter.centerBeginTime);
		// Elle est comprise entre 2h et 20h
		byte hours = (byte)Math.max(Math.min(randhour, 20), 2);
		// Les minutes sont déterminées aléatoirement
		byte minutes = (byte)(rand.nextInt(60));
		return new Time(hours, minutes);
	}
	// Génère une heure aléatoire de départ du travail (en fonction de l'heure de commencement)
	public static Time randomEnd(Time begin){
		// L'heure de départ est distribuée autour de 18h
		int randhour = (int)Math.floor(rand.nextGaussian()*Starter.sigmaEndTime+Starter.centerEndTime);
		// Elle est comprise entre l'heure d'arrivée + 2h (sans compter les minutes) et 24h (minuit)
		byte hours = (byte)Math.max(Math.min(randhour, 24), 2+begin.hours);
		// Les minutes sont déterminées aléatoirement
		byte minutes = (byte)(rand.nextInt(60));
		return new Time(hours, minutes);
	}
	
	public String toString(){
		String mString;
		String sString;
		
		if (seconds == 0){
		sString = "00";
		} else { sString = ""+seconds; }
		
		if (minutes < 10){
		mString = "0"+minutes;
		} else { mString = ""+minutes; }

		return hours + "h" + mString + "m" + sString + "s";
	
	}

	public Time clone(){
		return new Time (hours, minutes);
	}
	
	public byte getSeconds() {
		return seconds;
	}

	public byte getHours() {
		return hours;
	}

	public byte getMinutes() {
		return minutes;
	}
	
	public void incMinute(byte m){
		byte r = (byte) ((minutes + m) / 60);
		minutes = (byte) ((minutes + m) % 60);
		hours += r;
	}
	
	public void incQuarter(byte s){
		byte r = (byte) ((seconds + s) / 60);
		seconds = (byte) ((seconds + s) % 60);
		if (r != (byte) 0){
			this.incMinute(r);
		}
	}
	
	public void stringToTime(String s){
		String[] splited = s.split("[a-z]");
		hours = Byte.parseByte(splited[0]);
		minutes = Byte.parseByte(splited[1]);
		seconds = Byte.parseByte(splited[2]);
	}
	
	
}
