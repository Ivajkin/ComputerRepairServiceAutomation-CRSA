package pro.tmedia.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * User: Ivaykin Timofey
 * Date: 2/14/14
 */
@MappedSuperclass
public class DictionaryItem {
    @Id
    @GeneratedValue
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

    @OneToMany
    Set<Request> requests;

    /*@Override
    public String toString() {
        return getName();
    }    */
}
