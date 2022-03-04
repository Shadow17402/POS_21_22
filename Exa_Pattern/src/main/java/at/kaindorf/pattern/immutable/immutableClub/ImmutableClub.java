package at.kaindorf.pattern.immutable.immutableClub;

import java.util.ArrayList;
import java.util.List;

public class ImmutableClub {

    private final String clubname;
    private final int secretnumber;
    private final List<Member> members;

    public ImmutableClub(String clubname, int secretnumber, List<Member> members) {
        this.clubname = clubname;
        this.secretnumber = secretnumber;
        this.members = new ArrayList<>();
        this.members.addAll(members);
    }

    public ImmutableClub(String clubname, int secretnumber) {
        this.clubname = clubname;
        this.secretnumber = secretnumber;
        this.members = new ArrayList<>();
    }

    public String getClubname() {
        return clubname;
    }
    public int getSecretnumber() {
        return secretnumber;
    }
    public List<Member> getMembers() {
        List<Member> copy = new ArrayList<>();
        copy.addAll(members);
        return copy;
    }

}
