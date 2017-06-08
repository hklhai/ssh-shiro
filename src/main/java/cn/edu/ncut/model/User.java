package cn.edu.ncut.model;

import cn.edu.ncut.model.base.BaseModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User extends BaseModel implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String password;
    private String sex;


    //bi-directional many-to-one association to UserRole
    @OneToMany(mappedBy="tbUser",fetch=FetchType.EAGER)
    @XStreamOmitField
    private List<UserRole> tbUserroles;

    public User() {
    }

    public User(String name, String password, String sex) {
        super();
        this.name = name;
        this.password = password;
        this.sex = sex;
    }

    public List<UserRole> getTbUserroles() {
        return tbUserroles;
    }

    public void setTbUserroles(List<UserRole> tbUserroles) {
        this.tbUserroles = tbUserroles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }




    @Override
    protected void setConvertRules(XStream xstream) {
        xstream.alias("User", User.class);
        xstream.setMode(XStream.NO_REFERENCES);
    }
}
