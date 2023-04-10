import java.util.regex.Matcher;
// documentation -> https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html
import java.util.regex.Pattern;
// documentation -> https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html

public class regex {

    public static void main(String[] args) {
        String string = "I am a string. Yes, I am.";
        System.out.println(string);
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".", "Y"));

        // ^ -> starts with
        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));

        String secondString = "abcDeeeF12GhhabcDeeeiiiijkl99z";
        System.out.println(secondString.replaceAll("^abcDeee", "YYY"));

        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("^abcDeee"));
        System.out.println(alphanumeric.matches("abcDeeeF12Ghhiiiijkl99z"));

        // $ -> ends with
        System.out.println(alphanumeric.replaceAll("ijkl99z$", "THE END"));

        // matches with the chrachters that are in square brackets
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        System.out.println(alphanumeric.replaceAll("[aei]", "I replaced a letter here"));

        // matches with the sequence that one of the character in first square brackets
        // as soon as followed by a character that in second square bracket
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";

        // using ^ in the square root means that not
        // mathes with the character that is not 'e' or 'j'
        System.out.println(newAlphanumeric.replaceAll("[^ej]", "X"));

        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X"));

        // shorting the above regex with ranges like 'a-f' 'A-F' '3-8'
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X"));

        // (?i) means ignore case sensitive
        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X"));

        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X"));

        // matches with the digits
        System.out.println(newAlphanumeric.replaceAll("\\d", "X"));

        // matches with non-digits
        System.out.println(newAlphanumeric.replaceAll("\\D", "X"));

        String hasWhitespace = "I have blanks and\ta tab, and also a newline\n";
        System.out.println(hasWhitespace);

        // matches with whitespaces
        System.out.println(hasWhitespace.replaceAll("\\s", ""));

        // matches with tabs
        System.out.println(hasWhitespace.replaceAll("\t", "X"));

        // matches with non-whitespaces
        // NOTE only remains tabs, spaces and new line character (\n)
        System.out.println(hasWhitespace.replaceAll("\\S", ""));

        // matches with all things
        System.out.println(newAlphanumeric.replaceAll("\\w", "X"));

        // matches with all things other than alphanumerics ( ,-\n\t......)
        System.out.println(hasWhitespace.replaceAll("\\W", "X"));

        // matches with the boundry like "test message, teeest" -> "XesX XessagX,
        // XeeesX"
        System.out.println(hasWhitespace.replaceAll("\\b", "X"));

        String thirdAlphanumericString = "abcDeeeF12Ghhiiiijkl99z";

        // quantifiers always come after a
        // "^abcDe{3}" matches same as "^abcDeee"
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{3}", "YYY"));

        // "^abcDe+" matches same as "^abcDe" or "^abcDee" or "^abcDeee" or "^abcDeeee"
        // or "^abcDeeeee" or ......
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe+", "YYY"));

        // "^abcDe*" matches with started with abcD and followed by wheter 'e' or
        // something else
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe*", "YYY"));

        // matches with started abcD and followed by at least 2 'e', at most 5 'e'
        System.out.println(thirdAlphanumericString.replaceAll("^abcDe{2,5}", "YYY"));

        // matches with "hhhhhhhhj", "hhiagrkoaej", "hiiiwqqwpofkj" ......
        System.out.println(thirdAlphanumericString.replaceAll("h+i*j", "Y"));

        //////////////////////////////////////////////////////////////////////////////////////////////////

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        // some api's works with pattern class not with string so good to know that

        // simple usage
        // String h2Pattern = "<h2>"; matches method not works because it is a string
        String h2Pattern = ".*<h2>.*"; // it works beacuse it is a regular expression
        Pattern pattern = Pattern.compile(h2Pattern);

        // we can use matcher only at once
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        // to use it again need to reset
        matcher.reset();

        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());

        }

        // ? comes after .*, it means when it will stop until encountering the </h2> tag
        // ? not comes after .*, it means when it read until encountering the last </h2>
        // tag
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            System.out.println("Occurrence: " + groupMatcher.group(1)); // indexing from 1
        }

        String h2TextGroups = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPatten = Pattern.compile(h2TextGroups);
        Matcher h2TextMatcher = h2TextPatten.matcher(htmlText);

        while (h2TextMatcher.find()) {
            System.out.println("Occurrence: " + h2TextMatcher.group(2)); // 2 means string which matches with (.+?) part
        }

        // "abc" "a" and "b" and "c"
        // [[Hh]arry
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("harry".replaceAll("[Hh]arry", "Larry"));
        System.out.println("Harry".replaceAll("[H|h]arry", "Larry"));
        System.out.println("Harry".replaceAll("[Hh]arry", "Larry"));

        // [^abc]
        String tvTest = "tstvtkt";
        // means the 't' character that is followed by a character that is not 'v'
        // String tNotVRegExp = "t[^v]";
        // means the 't' character that is followed by a v character
        // String tNotVRegExp = "t(?=v)";
        // means the 't' character that is not followed by a v character
        String tNotVRegExp = "t(?!v)";
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        // Occurrence 2 : 4 to 5 means only 4th index
        // Occurrence 2 : 1 to 5 means 1-2-3-4th indexes
        count = 0;
        while (tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }

        // ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$
        String phone1 = "1234567890"; // Shouldn't match
        String phone2 = "(123) 456-7890"; // match
        String phone3 = "123 456-7890"; // Shouldn't match
        String phone4 = "(123)456-7890"; // Shouldn't match

        System.out.println("phone1 = " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone2 = " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone3 = " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("phone4 = " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        // ^4[0-9]{12}([0-9]{3})?$
        String visa1 = "4444444444444"; // should match
        String visa2 = "5444444444444"; // shouldn't match
        String visa3 = "4444444444444444"; // should match
        String visa4 = "4444"; // shouldn't match

        System.out.println("visa1 " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa2 " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa3 " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("visa4 " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));

    }
}