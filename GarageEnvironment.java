// Environment code for project Garage.mas2j



import jason.asSyntax.*;

import jason.asSyntax.parser.ParseException;



import jason.environment.*;

import java.util.logging.*;



public class GarageEnvironment extends Environment {



    private Logger logger = Logger.getLogger("Garage.mas2j."+GarageEnvironment.class.getName());
	//book page 106
    /** Called before the MAS execution with the args informed in .mas2j */

    @Override

    public void init(String[] args) {

        super.init(args);

		
				
			addPercept(ASSyntax.parseLiteral("percept(demo)"));
			addPercept("ag1", Literal.parseLiteral("p(a)"));
			addPercept("navigator", ASSyntax.parseLiteral("n(a)"));
			addPercept("surveillance", ASSyntax.parseLiteral("s(a)"));
			addPercept("valet", ASSyntax.parseLiteral("v(a)"));
    }



    @Override

    public boolean executeAction(String agName, Structure action) {

        logger.info("executing: "+action+", but not implemented!");

        if (true) { // you may improve this condition

             informAgsEnvironmentChanged();

        }

        return true; // the action was executed with success 

    }



    /** Called before the end of MAS execution */

    @Override

    public void stop() {
		//Clear all percepts
		clearPercepts();
		clearPercepts("navigator");
		clearPercepts("surveillance");
		clearPercepts("valet");
		
        super.stop();

    }

}


