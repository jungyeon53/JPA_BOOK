package entity;

import javax.persistence.*;

@Entity
public class Child {

    @EmbeddedId
    private ChildId id;

    @OneToOne(mappedBy = "child")
    public Parent parent;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    private String name;
}
