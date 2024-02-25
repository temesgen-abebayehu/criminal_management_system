public class Judge extends Person {
    String password;

    Judge(int id, String name, String sex, String password) {
        super(id, name, sex);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
