package pro.tmedia.model;

import javax.persistence.*;

/**
 * User: Ivaykin Timofey
 * Date: 2/14/14
 */
@MappedSuperclass
public class DictionaryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="appearance_id_seq")
    @SequenceGenerator(name="appearance_id_seq", sequenceName="appearance_id_seq", allocationSize=1)
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    /*@ManyToOne
    Request requests;

    @Override
    public String toString() {
        return getName();
    }    */
}
