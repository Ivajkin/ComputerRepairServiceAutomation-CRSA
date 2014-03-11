package pro.tmedia.model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: Ivaykin Timofey
 * Date: 2/14/14
 */
@Entity
@Table(name="report")
public class Report implements Serializable {



    @GeneratedValue
    @Id
    private Integer id;

    public List<ReportEntry> getReportEntries() {
        return reportEntries;
    }

    public void setReportEntries(List<ReportEntry> reportEntries) {
        this.reportEntries = reportEntries;
    }

    /*@OneToMany(mappedBy = "report")
    @JoinTable(
            name = "report",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    ) */

    //@ForeignKey(name = "id")
    @OneToMany(mappedBy = "report")
    /*@JoinTable(
            name = "report",
            joinColumns = @JoinColumn(name = "report_id", table = "report_entry"),
            inverseJoinColumns = @JoinColumn(name = "id", table = "report")
    ) */
    private List<ReportEntry> reportEntries;


    public void addReportEntry(ReportEntry reportEntry) {
        this.reportEntries.add(reportEntry);
        if(reportEntry.getReport() != this) {
            reportEntry.setReport(this);
        }
    }


    @ManyToOne
    private Department dept;

    @ManyToOne
    private Teacher teacher;




    public Department getDept() {
        return dept;
    }
    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
