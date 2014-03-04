package pro.tmedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */

@Entity
@Table(name="disc")
public class Discipline extends DictionaryItem {
    /*@Column(name="dept_id")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }  */
}

