import java.util.function.Supplier;

public class challenge67 {

    public static Supplier<String> iLoveJava = () -> "I love Java!";
    // with return statement you need brackets
    // public static Supplier<String> iLoveJava = () -> {return "I love Java!";};

    public static void main(String[] args) {
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

    }
}
