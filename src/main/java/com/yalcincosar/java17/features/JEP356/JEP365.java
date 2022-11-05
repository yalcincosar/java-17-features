package com.yalcincosar.java17.features.JEP356;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class JEP365 {
    public static void main(String[] args) {
        long seed = 36L;
        RandomGenerator randomGenerator = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create(seed);

        for (int i=0; i<5; i++) {
            int result = randomGenerator.nextInt(11);
            System.out.println(result);
        }

        RandomGeneratorFactory.all()
                .map(fac -> fac.group()+ " : " +fac.name())
                .sorted()
                .forEach(System.out::println);
    }
}
