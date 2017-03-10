package mainPackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Environment.Clock;
import Graphics.Panel;
import Graphics.Window;
import Individuals.FilterClockTick;
import Individuals.FilterNewDay;
import Individuals.Person;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;

public class Days extends Behaviour {
	
	ArrayList<Person> currentPersons = new ArrayList<Person>();

	public void action() {
		// on regarde les messages pour une nouvelle journée
		// (le premier est envoyé dans le setup, les suivants par l'horloge en fin de journée)
		ACLMessage m = myAgent.receive(new MessageTemplate(new FilterNewDay()));
		if (m == null) {
			block();
		} else {

			double currentCarFactor = Starter.carFactor();
			
			if (getCurrentDay()>0){
				
				// PRINT
				if (Starter.verbose>=0){ 
					System.out.println("\n ############################# BILAN JOURNEE " +  getCurrentDay() + " ############################# \n");
					((Starter)this.myAgent).printScores();
					System.out.println("Nouveau carFactor : "+ currentCarFactor);
				}
				
				// Reset des Agents
				((Starter)this.myAgent).reset();
				currentPersons.clear();
			}
			
			((Starter)this.myAgent).incCurrentDay();
			
			if (!((Starter)this.myAgent).noMoreDays()) {
			
				//PRINT :
				if (Starter.verbose>=1){ System.out.println("\n ############################# DEBUT JOURNEE " + 
				getCurrentDay() + " ############################# \n"); }
				
	
				Clock c = new Clock(Starter.simulationTime, Starter.stepLength, Starter.startHour);
				int offset = (getCurrentDay()-1)*getPersonsSauv().size();
				for (int i = 0; i < getPersonsSauv().size(); i++) {
					try {
						getPersonsSauv().get(i).chooseTransportMode(currentCarFactor);
						switch (getPersonsSauv().get(i).getTransportChoice()) {
						case car :
							((Starter)this.myAgent).incNbPeopleCar();
							break;
						case publicTransport :
							((Starter)this.myAgent).incNbPeoplePublicTransport();
							break;
						}
						Person newPersonCopy =  new Person(getPersonsSauv().get(i));
						((Starter)this.myAgent).getContainerController().acceptNewAgent("person" + (i + 1 + offset), newPersonCopy).start();
						currentPersons.add(newPersonCopy);
						((Starter)this.myAgent).setCurrentPersons(currentPersons);
					} catch (StaleProxyException e) {
						e.printStackTrace();
					}			
				}
				try {
					((Starter)this.myAgent).getContainerController().acceptNewAgent("clock"+getCurrentDay(), c).start();
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}
				
				// AFFICHAGE
	
				if (Starter.showSimulation) {
				
					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							public void run() {
								try {
									Starter.f = new Window();
								} catch (IOException e) {
									e.printStackTrace();
								}
			
								Starter.pan = new Panel(Starter.env, currentPersons, c, Starter.windowSize, Starter.simSize);
			
								Starter.f.add(Starter.pan);
							}
						});
					} catch (Exception e) {
						System.err.println("Erreur a la creation de l'interface Swing.");
						System.err.println(e);
					}
					
				}
			}
		}
	}

	public boolean done() {
		return ((Starter)this.myAgent).noMoreDays();
	}

	public ArrayList<Person> getPersonsSauv() {
		return ((Starter)this.myAgent).getPersonsSauv();
	}
	
	public int getCurrentDay() {
		return ((Starter)this.myAgent).getCurrentDay();
	}
	
	
}
