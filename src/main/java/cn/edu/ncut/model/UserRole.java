package cn.edu.ncut.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Ocean Lin on 2017/5/10.
 */

@Entity
@Table(name="TB_USERROLE")
public class UserRole implements Serializable{
    @Id
    private Integer userroleid;

    //bi-directional many-to-one association to UserObj
    @ManyToOne
    @JoinColumn(name="userid")
    @XStreamOmitField
    private User tbUser;

    public UserRole() {
    }

    public UserRole(Integer userroleid, User tbUser) {
        this.userroleid = userroleid;
        this.tbUser = tbUser;
    }

    public Integer getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(Integer userroleid) {
        this.userroleid = userroleid;
    }

    public User getTbUser() {
        return tbUser;
    }

    public void setTbUser(User tbUser) {
        this.tbUser = tbUser;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("userroleid=").append(userroleid);
        sb.append(", tbUser=").append(tbUser);
        sb.append('}');
        return sb.toString();
    }
}
