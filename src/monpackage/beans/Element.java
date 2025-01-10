package monpackage.beans;

import java.util.List;

public class Element implements Composant{
	// Les attributs
	private String code; 
    private String nom;
    private Double coefficient;
    private Module module; //added
    private String professeurCode;
    private List<ModaliteEvaluation> evaluationModes; // Liste des modalités d'évaluation CC TP PROJET 

    //Constructeur
	public Element(String code, String nom, Double coefficient, List<ModaliteEvaluation> evaluationModes) {
		super();
		this.code = code;
		this.nom = nom;
		this.coefficient = coefficient;
		//this.module = module;
		this.evaluationModes = evaluationModes;
	}
	
	// Getters and setters
    public String getCode() { // ADDED
		return code;
	}

	public void setCode(String code) {  // ADDED
		this.code = code;
	}

	public String getNom() {
        return nom;
    }
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {  // SETTER DE COEFICIENT MAIS AVEC UNE VERIFICATION QUE LE COEFICIENT DOIT ETRE ENTRE 0 ET 1
	    if (coefficient < 0 || coefficient > 1) {
	        throw new IllegalArgumentException("Le coefficient doit être compris entre 0 et 1.");
	    }
	    this.coefficient = coefficient;
	}

	
	public Module getModule() {  // ADDED
		return module;
	}

	public void setModule(Module module) {  // ADDED
		this.module = module;
	}
    
	public List<ModaliteEvaluation> getEvaluationModes() {
        return evaluationModes;
    }
	public void setEvaluationModes(List<ModaliteEvaluation> evaluationModes) {
		this.evaluationModes = evaluationModes;
	}
	
	public void addModalite(ModaliteEvaluation modalite) {
	    double totalCoefficient = this.evaluationModes.stream()
	                                 .mapToDouble(ModaliteEvaluation::getCoefficient)
	                                 .sum();
	    if (totalCoefficient + modalite.getCoefficient() > 1) {
	        throw new IllegalArgumentException("Le total des coefficients des modalités ne peut pas dépasser 100%.");
	    }
	    this.evaluationModes.add(modalite);
	}
	
	  // Afficher les informations de l’élément et de ses modalités (composite)
    @Override
    public void operation() {
        System.out.println("  Élément: " + code + " - " + nom + " (Coefficient: " + coefficient + ")");
        for (ModaliteEvaluation modalite : evaluationModes) {
            System.out.println("    Modalité: " + modalite.getType() + " (Coefficient: " + modalite.getCoefficient() + ")");
        }
    }
    public Element(String code, String nom, Double coefficient, String professeurCode) {
        this.code = code;
        this.nom = nom;
        this.coefficient = coefficient;
        this.professeurCode = professeurCode;  // On garde juste le code
    }

// Vérifiez que le coefficient total des modalités d'évaluation ne dépasse pas 100% pour chaque élément.



    
    
	// CONSTRUCTEUR IS MISSIGN FILEDS!!!!
	/*
    public Element(String nom, List<ModaliteEvaluation> evaluationModes) {
        this.nom = nom;
        this.evaluationModes = evaluationModes;
    }*/

	


	
}