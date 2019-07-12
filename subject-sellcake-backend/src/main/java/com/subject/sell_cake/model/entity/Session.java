package com.subject.sell_cake.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Van Dat
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "session")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Session implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.subject.sell_cake.util.IDGeneratorUtil")
    @Column(name = "token_id", nullable = false, length = 255)
    private String tokenId;
    private String accountLoginId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_date")
    private Date loginDate;
    private String sessionData;

    public String getTokenId() {
        return tokenId;
    }

    public String getAccountLoginId() {
        return accountLoginId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public String getSessionData() {
        return sessionData;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setAccountLoginId(String accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }
}
