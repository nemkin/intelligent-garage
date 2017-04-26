import jason.asSyntax.*;
import jason.architecture.*;
import java.util.*;

public class SurveillanceArchitecture extends AgArch {
	@Override
	public Collection<Literal> perceive() {
		// gets the default perception
		Collection<Literal> per = super.perceive();
		return per;
	}
}
