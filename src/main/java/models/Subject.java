package models;

public class Subject {
    private String subjectID;
    private String subjectName;
    private String previousSubject;
    private int year,term;


    public Subject(String subjectID, String getSubjectName, String previousSubject, int year, int term) {
        this.subjectID = subjectID;
        this.subjectName = getSubjectName;
        this.previousSubject = previousSubject;
        this.year = year;
        this.term = term;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
