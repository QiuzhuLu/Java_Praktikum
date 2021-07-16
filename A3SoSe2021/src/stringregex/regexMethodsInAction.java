package stringregex;

public class regexMethodsInAction {
    public static void main(String[] args) {
        StringExperimentals se = new StringExperimentals();

        System.out.println("----------- alleVorkommen -----------");
        String regex1 = "\\(.*?\\)";
        String somestring = "(hello) (world) (!";
        System.out.println(se.alleVorkommen(somestring, regex1));

        System.out.println("----------- gleichDemo -----------");
        se.gleichDemo();
    }
}
