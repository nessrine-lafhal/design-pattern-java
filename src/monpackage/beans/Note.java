package monpackage.beans;

public class Note {
    private String evaluationType;  // CC, TP, Projet, etc.
    private double grade;           // La note obtenue
    private double coefficient;     // Le coefficient de l'évaluation

    // Constructeur
    public Note(String evaluationType, double grade, double coefficient) {
        this.evaluationType = evaluationType;
        this.grade = grade;
        this.coefficient = coefficient;
    }

    // Getters
    public String getEvaluationType() {
        return evaluationType;
    }

    public double getGrade() {
        return grade;
    }

    public double getCoefficient() {
        return coefficient;
    }

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
    
}
