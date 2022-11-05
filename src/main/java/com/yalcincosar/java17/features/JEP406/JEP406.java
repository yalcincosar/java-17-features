package com.yalcincosar.java17.features.JEP406;

public class JEP406 {
    // JEP406.1 improved switch to support pattern matching for types (like instanceof)
    // JEP406.2 support for null case
    // JEP406.3 Refining patterns or guarded patterns in switch

    public static void main(String[] args) {
        Animal animalW = new Wolf(15);
        Animal animalE = new Eagle(1);
        System.out.println(animalDoSomething(animalW));
        System.out.println(animalDoSomething(animalE));
        //Now we can test the null in switch directly.
        System.out.println(animalDoSomethingWithNullSupport(null));
        //Using both a refined pattern and a non-refined pattern
        System.out.println(animalDoSomethingForShowRefiningPatterns(animalW));
        System.out.println(animalDoSomethingForShowRefiningPatterns(animalE));
        animalW = new Wolf(5,false);
        System.out.println(animalDoSomethingForShowRefiningPatterns(animalW));

    }

     public static String animalDoSomething(Animal animal) {
        return switch (animal) {
            case Eagle e -> e.fly();
            case Wolf w -> w.joinTheHerd();
            default  -> "It is not clear what this animal will do.";
        };
    }
    /**
     * in old times
     *     public static String animalDoSomethingButWithoutNullSupport(Animal animal) {
     *         if (animal == null) {
     *             return "Unknown";
     *         }
     *         return switch (animal) {
     *             case Eagle e -> e.fly();
     *             case Wolf w -> w.joinThePack();
     *             default  -> "It is not clear what this animal will do.";
     *         };
     *     }
     */

    //support for null case
    public static String animalDoSomethingWithNullSupport(Animal animal) {
        return switch (animal) {
            case Eagle e -> e.fly();
            case Wolf w -> w.joinTheHerd();
            case null  -> "Unknown";
            default  -> "It is not clear what this animal will do.";
        };
    }

    //Refining patterns in switch
    public static String animalDoSomethingForShowRefiningPatterns (Animal animal) {
        return switch (animal) {
            case Eagle e && e.getAge() < 2 -> "This is a baby eagle, unable to do something";
            case Eagle e -> e.fly();
            case Wolf w && w.getAge() > 14  -> "The wolf is too old, he can't do anything";
            //By using parentheses, we can avoid having additional if-else statements.
            case Wolf w && !w.doesItBelongToTheHerd() && (w.getAge() > 1 && w.getAge() < 14) -> w.joinTheHerd();
            case null  -> "Unknown";
            default  -> "It is not clear what this animal will do.";
        };
    }
}

