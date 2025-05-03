package de.ait;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Jack", 10, 30, 105);
        int w= p.getWeight();
        System.out.println(p);
        Person mike = Person.builder()
                .name("Mike")
                .height(178)
                .age(45)
                .build();
        System.out.println(mike);

    }
}
