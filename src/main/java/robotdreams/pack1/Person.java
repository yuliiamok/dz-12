package robotdreams.pack1;

public class Person {
    public Person partner;
    protected String firstName;
    protected String lastName;
    protected int age;
    protected boolean isRetired() {
        return false;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    protected Person getPartner() {
        return partner;
    }

    protected void setPartner(Person partner) {
        this.partner = partner;
    }

    public Person(String firstName, String lastName, int age, Person partner) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.partner = partner;
    }
    public String getInfo() {
        return String.format("%s %s has %d years old, is Retired: %s", firstName, lastName, age, isRetired());
    }
}
