package monpackage.beans;

import java.util.ArrayList;
import java.util.List;

//RAJAA
public class Module implements Composant{
	// Les attributs
    private String code;
    private String nom;
    private String semester;
    private Filiere filiere; 
    private boolean validated=false;
    private List<Composant> elements; // Stocke les éléments (feuilles)
    
    
    
    //Constructeur
    public Module(String code, String nom,String semester, Filiere filiere) {
        this.code = code;
        this.nom = nom;
        this.semester = semester;
        this.filiere = filiere;
        this.elements = new ArrayList<>();
    }


    // Getters ans Setters
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
	    if (!semester.matches("S[1-5]")) {// érifiez que le semestre est valide (entre S1 et S5)
	        throw new IllegalArgumentException("Le semestre doit être compris entre S1 et S5.");
	    }
	    this.semester = semester;
	}


	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public void addElement(Element element) {
		if (element != null && elements.stream().noneMatch(e -> ((Element) e).getCode().equals(element.getCode()))) {
	        elements.add(element);
	        
	    } else {  // ajouter une validation pour éviter d'ajouter deux éléments avec le même code au module.
	        System.out.println("L'élément est nul ou un élément avec ce code existe déjà.");
	    }
	}
    
  
	
	// LES METHODES DE LA CLASSE COMPOSITE QUI IMPLEMENTE COMPOSANT ADD REMOVE OPERATION
	// Ajouter un composant (élément)
    public void add(Composant composant) {
        if (composant instanceof Element && elements.stream().noneMatch(e -> ((Element) e).getCode().equals(((Element) composant).getCode()))) {
            elements.add(composant);
        } else {
            System.out.println("Élément invalide ou déjà existant.");
        }
    }

    // Supprimer un composant (élément)
    public void remove(Composant composant) {
        elements.remove(composant);
    }

    // Afficher les informations du module et de ses éléments
    @Override
    public void operation() {
        System.out.println("Module: " + code + " - " + nom + " (Semestre: " + semester + ")");
        for (Composant composant : elements) {
            composant.operation();
        }
    }


    public boolean isValidated() {
        return validated;
    }

    public void validate() {
        this.validated = true;
    }

   



}
