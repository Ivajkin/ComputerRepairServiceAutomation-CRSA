package pro.tmedia.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;


    @OneToMany
    Set<Hardware> hardware;

    public Set<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(Set<Hardware> hardware) {
        this.hardware = hardware;
    }

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
}
