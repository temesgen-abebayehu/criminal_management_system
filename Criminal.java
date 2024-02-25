public class Criminal extends Person {

    String dob;
    String crime;
    double sentence;

    public Criminal(int id, String name, String sex, String dob, String crime, double sentence) {
        super(id, name, sex);
        this.dob = dob;
        this.crime = crime;
        this.sentence = sentence;
    }

    public String getDob() {
        return dob;
    }

    public String getCrime() {
        return crime;
    }

    public double getSentence() {
        return sentence;
    }
}
