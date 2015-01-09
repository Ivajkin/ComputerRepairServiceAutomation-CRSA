package pro.tmedia.model.cash;

import pro.tmedia.model.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Ivaykina Galina
 * Date: 28.06.14
 */
@Entity
@Table(name="cash_operation")
public class CashOperation {
    public CashOperation(Integer amount, String description, CashType cashType, Employee employee) {
        this.amount = amount;
        this.description = description;
        this.operation_date = new java.sql.Date(new Date().getTime());
        this.cashType = cashType;
        this.saldo = cashType.getSaldo();
        this.employee = employee;
        if(amount > 0) {
            cash_operation_status_id = 1; /* Приход */
        } else {
            cash_operation_status_id = 2; /* Расход */
        }
    }

    public CashOperation() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cash_operation_id_seq")
    @SequenceGenerator(name="cash_operation_id_seq", sequenceName="cash_operation_id_seq", allocationSize=1)
    private Integer id;

    private Integer product_id = 1;
    private Integer cash_operation_status_id;

    @ManyToOne
    @JoinColumn(name="cash_type_id")
    private CashType cashType;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    private Integer amount;
    private String description;
    private Integer saldo;
    private java.sql.Date operation_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public CashType getCashType() {
        return cashType;
    }

    public void setCashType(CashType cashType) {
        this.cashType = cashType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public java.sql.Date getOperation_date() {
        return operation_date;
    }

    public void setOperation_date(java.sql.Date operation_date) {
        this.operation_date = operation_date;
    }
}
