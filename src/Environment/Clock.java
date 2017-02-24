package Environment;


public class Clock extends jade.core.Agent {

	int simulationTime; // en secondes
	Time currentTime;
	
	public Clock(int simulationTime) {
		this.simulationTime = simulationTime;
		currentTime = new Time((byte)0,(byte)0);
	}
	
	public void setup(){
		// On prévient du lancement de l'horloge
		System.out.println("Lancement de l'horloge.");
		// On lui ajoute ses comportements
		int period = (int) (1000*simulationTime/(24*60)); // période réelle en ms
		this.addBehaviour(new ClockTick(this,period));
	}
	
	public boolean isMidgnight(){
		return ( currentTime.hours==(byte)24 );
	}
	
	public void incTime(){
		currentTime.incMinute((byte)1);
	}

	public Time getCurrentTime() {
		return currentTime;
	}
	
	
}

