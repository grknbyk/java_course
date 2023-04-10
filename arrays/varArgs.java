public class varArgs {

    // we can pass parameter for vArgs
    // as a array of the type or
    // as many of instance of the type
    private static void printText(String... textList) {
        for (String t : textList)
            System.out.println(t);
    }

    public static void main(String... args) {

        String[] splitStrings = "Hello World again".split(" ");
        printText(splitStrings);

        System.out.println("_".repeat(20));
        printText("Hello");

        System.out.println("_".repeat(20));
        printText("Hello", "World", "again");

        String[] sArray = { "first", "second", "third", "fourth", "fifth" };
        System.out.println(String.join(",", sArray));
    }

}
