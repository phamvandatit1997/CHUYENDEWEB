package com.subject.sell_cake.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "bills")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Bills {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.subject.sell_cake.util.IDGeneratorUtil")
    @Column(name = "bill_id", nullable = false, length = 255)
    private String billId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "date_order", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrder;
    @Column(name = "date_shiper", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateShiper;
    @Column(name = "total")
    private int total;
    @Column(name = "payment")
    private String payment;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private int status;

    @CreatedDate
    @Column(name = "create_at",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Column(name = "update_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivity;

    public String getBillId() {
        return billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public int getTotal() {
        return total;
    }

    public String getPayment() {
        return payment;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Date getDateShiper() {
        return dateShiper;
    }

    public void setDateShiper(Date dateShiper) {
        this.dateShiper = dateShiper;
    }
}
