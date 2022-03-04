package at.kaindorf.pattern.immutable.immutableClub;

public class ImmutableTester {

    public static void main(String[] args) {
        Club normalClub = new Club("Peteristen",3112);
        Member m1 = new Member("Peter","Gottlieb");
        Member m2 = new Member("Peter","Gotthass");
        Member m3 = new Member("Peter","Gottfried");
        normalClub.addMember(m1);
        normalClub.addMember(m2);
        normalClub.addMember(m3);
        System.out.println(normalClub);


    }

}
