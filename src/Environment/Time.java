package Environment;

import java.util.Random;

public class Time {

	// La simulation se déroule sur une journée
	byte hours;
	byte minutes;
	
	public Time(byte h, byte m){
		hours = h;
		minutes = m;
	}
	
	public boolean equals(Time toCompare){
		return ( (hours==toCompare.getHours())&&(minutes==toCompare.getMinutes()) );
	}
	
	// POUR LA GENERATION ALEATOIRES DE PERSONNES ALLER-RETOUR
	
	static Random rand = new Random();
	// Génère une heure alétoire de départ du point d'entrée 
	public static Time randomBegin(){
		// L'heure de début est distribuée autour de 10h du matin
		int randhour = (int)Math.floor(rand.nextGaussian()*4+10);
		// Elle est comprise entre 2h et 20h
		byte hours = (byte)Math.max(Math.min(randhour, 20), 2);
		// Les minutes sont déterminées aléatoirement
		byte minutes = (byte)(rand.nextInt(60));
		return new Time(hours, minutes);
	}
	// Génère une heure aléatoire de départ du travail (en fonction de l'heure de commencement)
	public static Time randomEnd(Time begin){
		// L'heure de départ est distribuée autour de 18h
		int randhour = (int)Math.floor(rand.nextGaussian()*4+18);
		// Elle est comprise entre l'heure d'arrivée + 2h (sans compter les minutes) et 24h (minuit)
		byte hours = (byte)Math.max(Math.min(randhour, 24), 2+begin.hours);
		// Les minutes sont déterminées aléatoirement
		byte minutes = (byte)(rand.nextInt(60));
		return new Time(hours, minutes);
	}
	
	public String toString(){
		if (minutes == 0){
		return hours + "h00";
		} 
		else {
		return hours + "h" + minutes;
		}
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
	
	public void stringToTime(String s){
		String[] splited = s.split("h");
		hours = Byte.parseByte(splited[0]);
		minutes = Byte.parseByte(splited[1]);
	}
	
	
}
