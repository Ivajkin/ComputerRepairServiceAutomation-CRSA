package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="hardware")
public class Hardware extends DictionaryItem {

    private String description;


    private Integer category_id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
