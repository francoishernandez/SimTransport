package Individuals;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate.MatchExpression;

public class FilterNewDay implements MatchExpression {
// ce filtre permet de ne considérer que les messages de nouvelle journée
	
	public boolean match(ACLMessage message) {
		// on choisit 2 pour les messages de nouvelle journée
		if (message.getPerformative() == 2){
			return true;
		}
		return false;
	}

}