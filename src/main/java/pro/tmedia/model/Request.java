package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue
    private Integer req_num_id;
    private Hardware hardware;
    private Manufacturer manufacturer;
    private String model;
    private String serial_number;
    private Fault fault;
    private Appearance appearance;
    Completeness completeness;
    private String phone;
    private String address;
    private String customer_name;
    private Source source;
    private String date_of_call;
    private String note;
    private Integer approximate_cost;
    private Integer prepayment;
    Employee acceptor;
    Employee responsible;
    private String date_of_receipt;
    private String date_of_issue;
    Integer amount;
    private String method_of_payment;
    RequestStatus request_status;
    Task completed_works;
    Hardware parts_installed;
}
