package entity;

import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Member> members = new ArrayList<Member>();

    public Team() {}

    public List<Member> getMembers() {
        return members;
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
    }
}
