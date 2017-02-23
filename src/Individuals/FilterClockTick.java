package Individuals;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate.MatchExpression;

public class FilterClockTick implements MatchExpression {
// ce filtre permet de ne considérer que les messages d'avertissement d'arrivée d'un bus
	
	public boolean match(ACLMessage message) {
		if (message.getPerformative() == 1){
			return true;
		}
		return false;
	}

}
