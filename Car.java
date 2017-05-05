import java.util.Arrays;
import java.util.List;
import java.util.logging.*;

public class Car {
    private Logger logger = Logger.getLogger("Garage.mas2j."+Car.class.getName());

	private String owner;
	private String number_plate;
	private int index;
	
	static List<String> names = Arrays.asList(
			"Hal�sz Alexandra", "Kocsis Mikl�s", "Szek�r D�vid", "Kov�cs M�ty�s", "Vajda Endre",
			"Tak�cs Linda", "Havas �va", "Solt�sz P�ter", "P�l Blanka", "G�sp�r Anna",
			"T�th Zsuzsanna", "Kov�cs �rp�d", "Kiss Norbert", "Vad�sz T�mea", "Szab� Barnab�s",
			"Mayer M�nika", "Kecsk�s Zolt�n", "B�r� Hedvig", "Nagy Gergely", "F�ti T�nde",
			"Vad�sz P�ter", "Szatm�ri Borb�la", "Veres Bence", "Harangoz� Erzs�bet", "Tiszafalvi S�ndor",
			"Herman Nikolett", "Sass J�nos", "N�meth Edina", "J�vorka B�la", "Sz�n�si �gnes" );
	static List<String> number_plates = Arrays.asList(
			"NSH-374", "AWU-634", "EWE-233", "IDC-143", "COW-914",
			"BOL-941", "FEI-927", "HAJ-741", "IAH-963", "HIX-861",
			"NOX-198", "HNL-011", "KFR-745", "HPS-469", "MOS-800",
			"SAP-013", "IGE-321", "NEM-020", "NOP-656", "LSD-999",
			"KAC-276", "CAT-455", "CIB-195", "LAC-731", "ALM-519",
			"BPO-612", "DNS-276", "EXE-383", "FTC-571", "KFC-771");
	
	public Car(){
		index = 0;
	}
	
	public static void main(String[] args) {
		Car oneCar = new Car();
		oneCar.getOneCar();
	}
	
	public void getOneCar(){
		if(index == 30){
			index = 0;
		}
		owner = names.get(index);
		number_plate = number_plates.get(index);
		index++;
	}
}