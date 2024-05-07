package entity;

import javax.persistence.*;

@Entity
@Table(name = "BOARD")
@SecondaryTable(name = "BOARD_DETAIL",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "BOARD_DETAIL_ID")
)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "BOARD_SEQ_GENERATOR")
    private Long id;

    private String title;
   @Column(table = "BOARD_DETAIL")
    private String content;
}
