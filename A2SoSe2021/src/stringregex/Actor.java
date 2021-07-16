package stringregex;

public class Actor {
    private String firstname;
    private String name;

    public Actor(String firstName, String name){
        this.firstname =firstName;
        this.name = name;
    }

    @Override
    public String toString() {
        return  (firstname.isEmpty() ? "" : firstname + " ") + name;
    }
}
