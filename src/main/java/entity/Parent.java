package entity;

import javax.persistence.*;

@Entity
public class Parent {

    @Id
    @Column(name = "PARENT_ID")
    private String id;
    private String name;

    @OneToOne
    @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "PARENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID")
    )
    private Child child;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
