package sunofkyuss.zwitter.view.utility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import sunofkyuss.zwitter.Visibility;

@Named
@ApplicationScoped
public class EnumLister {

	public Visibility[] visibilityValues(){
		return Visibility.values();
	}
	
}
