import java.util.function.Function;

public class challenge2345 {
    public static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(1));
            }
        }

        return returnVal.toString();
    }

    public static Function<String, String> lambdaFunction = s -> {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(s.charAt(1));
            }
        }

        return returnVal.toString();
    };


    public static void main(String[] args) {
        // how to use lambda function
        System.out.println(lambdaFunction.apply("1234567890"));
        System.out.println(parseMethod(lambdaFunction, "1234567890"));
    }

    public static String parseMethod(Function<String, String> func, String parameter ){
        return func.apply(parameter);
    }
}
