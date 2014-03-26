package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="hardware")
/*@SecondaryTable(name="category",
        pkJoinColumns={
                @PrimaryKeyJoinColumn(name="category_id")})       */
public class Hardware extends DictionaryItem {
    /*@Id
    @GeneratedValue
    private Integer id;
    private String name;*/
    private String description;

    @ManyToOne
    //@JoinColumn(/*name = "category_id", */referencedColumnName = "category_id", table = "category", insertable = false, updatable = false)
    private Category category;

    /*public Integer getId() {
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
    } */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
