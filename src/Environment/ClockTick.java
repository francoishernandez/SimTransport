package Environment;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class ClockTick extends jade.core.behaviours.TickerBehaviour {

	// annonce le prochain arrêt à intervalles de temps réguliers
	
	public ClockTick(Clock a, int period) {
		super(a,period); // deuxième argument : période en ms
	}
	
	protected void onTick() {
		if (((Clock)(this.myAgent)).isMidgnight()){
			// si il est minuit on arrête le comportement
			stop();
			// ARRETER LES AUTRES AGENTS ?
		} else {
			// Sinon on incrémente l'horloge et on annonce l'heure
			((Clock)(this.myAgent)).incTime();
			
			// on construit le message
			ACLMessage m = new ACLMessage(1); // 1 pour les messages d'horloge
			m.setContent(currentTime().toString());
			// on recherche les destinataires selon une DFAgentDescription
			DFAgentDescription dfd = new DFAgentDescription();
			ServiceDescription bus = new ServiceDescription();
			// Les destinataires doivent avoir un service de type person
			bus.setType("Person");
			dfd.addServices(bus);
			try {
				// on récupère les destinataires possibles
				DFAgentDescription[] destinataires = DFService.search(myAgent, dfd);
				for (int i = 0; i < destinataires.length; i++){ 
					// on ajoute les destinataires du messages
					AID destName = destinataires[i].getName();
					m.addReceiver(destName);
				}
			} catch (FIPAException e) {
				e.printStackTrace();
			}
			// On envoie le message
			myAgent.send(m);
		}
	}
	
	public Time currentTime(){
		return ((Clock)(this.myAgent)).getCurrentTime();
	}
	
}

