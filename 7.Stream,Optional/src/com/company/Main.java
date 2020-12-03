package com.company;


import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //Reference to static method

        List<String> strings = Arrays.asList("java", "kotlin", "scala");
        List<String> capitalizedStrings = strings.stream()
                .map(StringUtils::capitalize)
//                .map(string -> StringUtils.capitalize(string))
                .collect(Collectors.toList());
        for (String capitalizedString : capitalizedStrings) {
            System.out.println(capitalizedString);
        }

        //Reference to Instance Method of a Particular Object
        List<Dog> dogs = Arrays.asList(
                new Dog("Alibaba", 1000),
                new Dog("Sebek", 2000),
                new Dog("Tuzik", 500)
        );

        DogTailComparator dogTailComparator = new DogTailComparator();
        List<Dog> sortedDogs = dogs.stream()
                .sorted((d1, d2) -> d2.compareTails(d1))
                .collect(Collectors.toList());
        System.out.println(sortedDogs);

        //Reference to an Instance Method of an Arbitrary Object of a Particular Type

        int totalLength = dogs.stream()
                .mapToInt(Dog::getTailLength)
                .sum();
        System.out.println(totalLength);

        List<Dog> sortedDogs2 = dogs.stream()
                .sorted(Dog::compareTails)
                .collect(Collectors.toList());
        System.out.println(sortedDogs2);

        //Reference to a Constructor
        List<String> names = Arrays.asList("Lyolik", "Bolik", "Barsik");
        List<Dog> dogList = names.stream()
                .map(Dog::new)
                .collect(Collectors.toList());

        dogList.forEach(System.out::println);
    }
}

class DogTailComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.getTailLength() - o2.getTailLength();
    }
}

class Dog {
    private String name;
    private int tailLength;

    public Dog(String name) {
        this.name = name;
        this.tailLength = 1000;
    }

    public Dog(String name, int tailLength) {
        this.name = name;
        this.tailLength = tailLength;
    }

    public int getTailLength() {
        return tailLength;
    }

    public int compareTails(Dog anotherDog) {
        return anotherDog.tailLength - this.tailLength;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", tailLength=" + tailLength +
                '}';
    }
}
