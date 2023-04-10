import javax.sound.sampled.SourceDataLine;

public class testEnums {
    public static void main(String[] args) {
        DayOfTheWeek day = getRandomDay();
        System.out.println(day);
        System.out.printf("Name is %s and ordinal is %d%n", day.name(), day.ordinal());

        if (day == DayOfTheWeek.SUNDAY)
            System.out.println("Found Sunday!");

        if (day.equals(DayOfTheWeek.SUNDAY))
            System.out.println("Found Sunday!");

        // this will not work
        // if(day == SUNDAY)
        // System.out.println("Found Sunday!");


        switchDayOfTheWeek(day);


        System.out.println();
        System.out.println();
        for(Topping x : Topping.values())
            System.out.println(x + " costs " + x.getPrice() + " dollars.");
    }

    public static void switchDayOfTheWeek(DayOfTheWeek weekDay) {

        int weekDayInteger = weekDay.ordinal() + 1;
        switch (weekDay) {
            case WEDNESDAY -> System.out.println("Wednesday is Day " + weekDayInteger + ".");
            case FRIDAY -> System.out.println("Friday is Day " + weekDayInteger + ".");
            default -> System.out.println(weekDay.name().charAt(0) + weekDay.name().substring(1).toLowerCase()
                    + " is Day " + weekDayInteger + ".");
        }
    }

    public static DayOfTheWeek getRandomDay() {
        // int randomInteger = new Random().nextInt(DayOfTheWeek.values().length);
        int randomInteger = (int) (Math.random() * DayOfTheWeek.values().length); // returns 0 to 6

        var allDays = DayOfTheWeek.values();
        return allDays[randomInteger];
    }
}
