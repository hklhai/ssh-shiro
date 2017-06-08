package cn.edu.ncut.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the TB_ROLEMODEL database table.
 */
@Entity
@Table(name = "LAB_ROLEMODEL")
public class RolemodelObj implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolemodelid;

    //bi-directional many-to-one association to ModelObj
    @ManyToOne
    @JoinColumn(name = "MODELID")
    private ModelObj tbModel;

    //bi-directional many-to-one association to RoleObj
    @ManyToOne
    @JoinColumn(name = "ROLEID")
    private RoleObj tbRole;

    public RolemodelObj() {
    }

    public RolemodelObj(ModelObj tbModel, RoleObj tbRole) {
        this.tbModel = tbModel;
        this.tbRole = tbRole;
    }

    public Integer getRolemodelid() {
        return this.rolemodelid;
    }

    public void setRolemodelid(Integer rolemodelid) {
        this.rolemodelid = rolemodelid;
    }

    public ModelObj getTbModel() {
        return this.tbModel;
    }

    public void setTbModel(ModelObj tbModel) {
        this.tbModel = tbModel;
    }

    public RoleObj getTbRole() {
        return this.tbRole;
    }

    public void setTbRole(RoleObj tbRole) {
        this.tbRole = tbRole;
    }

}