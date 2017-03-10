package Environment;


public class Clock extends jade.core.Agent {

	int simulationTime; // en secondes
	int stepLength; // en secondes
	Time currentTime;
	
	public Clock(int simulationTime, int stepLength, int startHour) {
		this.simulationTime = simulationTime;
		this.stepLength = stepLength;
		currentTime = new Time((byte)startHour,(byte)0);
	}
	
	public Clock(Clock toCopy) {
		this.simulationTime = toCopy.simulationTime;
		this.stepLength = toCopy.stepLength;
		currentTime = new Time((byte)0,(byte)0);
	}
	
	public void setup(){
		// On prévient du lancement de l'horloge
		System.out.println("Lancement de l'horloge.");
		// On lui ajoute ses comportements
		int period = (int) (1000*simulationTime*stepLength/(24*60*60)); // période réelle en ms
		this.addBehaviour(new ClockTick(this,period));
	}
	
	public boolean isMidgnight(){
		return ( currentTime.hours==(byte)24 );
	}
	
	public void incTime(){
		currentTime.incQuarter((byte) this.stepLength);
	}

	public Time getCurrentTime() {
		return currentTime;
	}
	
	
}

