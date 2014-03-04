package pro.tmedia.model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * User: Ivaykin Timofey
 * Date: 2/14/14
 */
@Entity
@Table(name="report_entry")
public class ReportEntry implements Serializable {


    @GeneratedValue
    @Id
    private Integer id;

    /*@OneToMany(mappedBy = "project")
    @JoinTable(
            name = "Project_Tasks",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;*/


    /*
    @JoinTable(
            name = "report_entry",
            joinColumns = @JoinColumn(name = "id", table = "report"),
            inverseJoinColumns = @JoinColumn(name = "report_id", table = "report_entry")
    )         */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="report_id")
    //@ForeignKey(name = "report_id")
    private Report report;

    @ManyToOne
    private Flow flow;
    @ManyToOne
    private Discipline disc;

    private Integer stud_count_plan;
    private Integer lection_count_plan;
    private Integer stud_count_fact;
    private Integer lection_count_fact;


    public Report getReport() {
        return report;
    }
    public void setReport(Report report) {
        this.report = report;
        if(report.getReportEntries() == null) {
            report.setReportEntries(new ArrayList<ReportEntry>());
        }
        if(!report.getReportEntries().contains(this)) {
            report.getReportEntries().add(this);
        }
    }

    public Flow getFlow() {
        return flow;
    }
    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Discipline getDisc() {
        return disc;
    }
    public void setDisc(Discipline disc) {
        this.disc = disc;
    }

    public Integer getStud_count_plan() {
        return stud_count_plan;
    }
    public void setStud_count_plan(Integer stud_count_plan) {
        this.stud_count_plan = stud_count_plan;
    }

    public Integer getLection_count_plan() {
        return lection_count_plan;
    }
    public void setLection_count_plan(Integer lection_count_plan) {
        this.lection_count_plan = lection_count_plan;
    }


    public Integer getStud_count_fact() {
        return stud_count_fact;
    }
    public void setStud_count_fact(Integer stud_count_fact) {
        this.stud_count_fact = stud_count_fact;
    }

    public Integer getLection_count_fact() {
        return lection_count_fact;
    }
    public void setLection_count_fact(Integer lection_count_fact) {
        this.lection_count_fact = lection_count_fact;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
