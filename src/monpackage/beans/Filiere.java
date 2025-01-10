package monpackage.beans;
import java.util.ArrayList;
import java.util.List;

public class Filiere {
	
	// Les attributs
	private String code;
	private String nom;
    private List<Module> modules;
    private List<Etudiant> etudiants;  
    
    
    // Constructeur

       
    public Filiere(String code, String nom, List<Module> modules, List<Etudiant> etudiants) { // HADA ZADTOU NESSRINE HHHH 
		super();
		this.code = code;
		this.nom = nom;
		this.modules = modules;
		this.etudiants = etudiants;
	}

	public Filiere (String code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
		this.modules = new ArrayList<>();
	}
	// Getters et Setters
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

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}


	// Méthode pour ajouter un module
    public void addModule(Module module) {
    	 modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }
    
    
	public List<Etudiant> getEtudiants() { // ADDED
		return etudiants;
	}


	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;  // ADDEED
	}
    
    
}
