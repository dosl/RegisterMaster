package models;

public class Subject {
    private String subjectID;
    private String subjectName;
    private String previousSubject;
    private String year, term;
    private boolean status;
    private String color;



    public Subject(String subjectID, String name, String year, String term, String previousSubject, boolean status, String color) {
        this.subjectID = subjectID;
        this.subjectName = name;
        this.previousSubject = previousSubject;
        this.year = year;
        this.term = term;
        this.status = status;
        this.color = color;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPreviousSubject() {
        return previousSubject;
    }

    public void setPreviousSubject(String previousSubject) {
        this.previousSubject = previousSubject;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String isStatus() {
        if (status) return "Pass";
        else return "Not Pass";
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }




    @Override
    public String toString() {
        return getSubjectID()+" "+getPreviousSubject();
    }
}
