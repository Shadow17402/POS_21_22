package at.kaindorf.pattern.immutable.immutableClub;

import java.util.ArrayList;
import java.util.List;

public class Club {

    private String clubname;
    private int secretnumber;
    private List<Member> members;

    public Club(String clubname, int secretnumber) {
        this.clubname = clubname;
        this.secretnumber = secretnumber;
        this.members = new ArrayList<>();
    }

    public String getClubname() {
        return clubname;
    }
    public void setClubname(String clubname) {
        this.clubname = clubname;
    }
    public int getSecretnumber() {
        return secretnumber;
    }
    public void setSecretnumber(int secretnumber) {
        this.secretnumber = secretnumber;
    }
    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    public void addMember(Member m){
        this.members.add(m);
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubname='" + clubname + '\'' +
                ", secretnumber=" + secretnumber +
                ", members=" + members +
                '}';
    }
}
