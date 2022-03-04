package at.kaindorf.pattern.immutable;

public final class ImmutablePerson {    //make class final

    private final String name;          //make instance vars private final
    private final int age;
    private final Address address;

    public ImmutablePerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = new Address(address.getStreetname(),address.getZipCode());   //create clone
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Address getAddress() {
        return new Address(address.getStreetname(),address.getZipCode());           //create clone
    }

}