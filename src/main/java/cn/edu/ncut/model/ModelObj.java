package cn.edu.ncut.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the TB_MODEL database table.
 */
@Entity
@Table(name = "LAB_MODEL")
public class ModelObj implements Serializable,
        Comparable<ModelObj> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modelid;

    private String modeldesc;

    private String modelname;

    private Integer modelstatus;


    private Integer sortnum;

    private Integer parentid;

    // bi-directional many-to-one association to RolemodelObj
    @OneToMany(mappedBy = "tbModel")
    @XStreamOmitField
    private List<RolemodelObj> tbRolemodels;

    public ModelObj() {
    }

    /**
     * @param modeldesc   模块描述
     * @param modelname   模块名称
     * @param modelstatus 模块状态
     * @param sortnum     排序号
     * @param parentid    父节点
     */
    public ModelObj(Integer modelid, String modeldesc, String modelname, Integer modelstatus, Integer sortnum, Integer parentid) {
        this.modelid = modelid;
        this.modeldesc = modeldesc;
        this.modelname = modelname;
        this.modelstatus = modelstatus;
        this.sortnum = sortnum;
        this.parentid = parentid;
    }

    public Integer getModelid() {
        return this.modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public String getModeldesc() {
        return this.modeldesc;
    }

    public void setModeldesc(String modeldesc) {
        this.modeldesc = modeldesc;
    }

    public String getModelname() {
        return this.modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public Integer getModelstatus() {
        return this.modelstatus;
    }

    public void setModelstatus(Integer modelstatus) {
        this.modelstatus = modelstatus;
    }

    public Integer getSortnum() {
        return this.sortnum;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public List<RolemodelObj> getTbRolemodels() {
        return this.tbRolemodels;
    }

    public void setTbRolemodels(List<RolemodelObj> tbRolemodels) {
        this.tbRolemodels = tbRolemodels;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Override
    public int compareTo(ModelObj obj) {
        try {
            if (this.sortnum != null && obj.sortnum != null) {
                return this.getSortnum().compareTo(obj.getSortnum());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ModelObj) {
            ModelObj o = (ModelObj) obj;
            return this.modelid.equals(o.modelid);
        } else {
            return super.equals(obj);
        }
    }
}