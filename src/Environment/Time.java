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
	
	
	// POUR LA GENERATION ALEATOIRES DE PERSONNES ALLER-RETOUR
	
	static Random rand = new Random();
	// Génère une heure alétoire de départ du point d'entrée 
	public static Time randomBegin(){
		// L'heure de début est distribuée autour de 10h du matin
		int randhour = (int)Math.floor(rand.nextGaussian()*4+10);
		// Elle est comprise entre 2h et 20h
		byte hours = (byte)Math.max(Math.min(randhour, 20), 2);
		// Les minutes tombent sur des quarts d'heure
		byte minutes = (byte)(15*rand.nextInt(5));
		return new Time(hours, minutes);
	}
	// Génère une heure aléatoire de départ du travail (en fonction de l'heure de commencement)
	public static Time randomEnd(Time begin){
		// L'heure de départ est distribuée autour de 18h
		int randhour = (int)Math.floor(rand.nextGaussian()*4+18);
		// Elle est comprise entre l'heure d'arrivée + 2h (sans compter les minutes) et 24h (minuit)
		byte hours = (byte)Math.max(Math.min(randhour, 24), 2+begin.hours);
		// Les minutes tombent sur des quarts d'heure
		byte minutes = (byte)(15*rand.nextInt(5));
		return new Time(hours, minutes);
	}
	
}
