package monpackage.beans;

import java.util.List;

public class ModaliteEvaluation {
	private static final List<String> VALID_TYPES = List.of("CC", "TP", "Projet", "Presentation");
	private int id; // GETTERSS SETTERSSSS
    private String type; // Type of evaluation (e.g., CC, TP, Project)
    private double coefficient;    // Coefficient of the evaluation (e.g., 0.3, 0.2, 0.5)
    private String ElementCode;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getElementCode() {
		return ElementCode;
	}

	public void setElementCode(String elementCode) {
		ElementCode = elementCode;
	}

	// Constructor
    public ModaliteEvaluation(String evaluationType, double coefficient) {
    	 setType(type);  // Utiliser le setter pour valider le type
         setCoefficient(coefficient);
    }

  //getters and setters
	public String getType() {
		return type;
	}

	public void setType(String type) {
	    if (!VALID_TYPES.contains(type)) {
	        throw new IllegalArgumentException("Le type doit être CC, TP, Projet ou Présentation.");
	    }
	    this.type = type;
	}

	public double getCoefficient() {
		return coefficient;
	}
	
	// Constructeur
    public ModaliteEvaluation(String type, double coefficient, String elementCode) {
        this.type = type;
        this.coefficient = coefficient;
        this.ElementCode = elementCode;
    }

	public void setCoefficient(double coefficient) {
	    if (coefficient < 0 || coefficient > 1) {
	        throw new IllegalArgumentException("Le coefficient doit être compris entre 0 et 1.");
	    }
	    this.coefficient = coefficient;
	}

    
   


   
}


