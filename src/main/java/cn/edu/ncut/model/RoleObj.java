package cn.edu.ncut.model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the TB_ROLE database table.
 * 
 */
@Entity
@Table(name="LAB_ROLE")
public class RoleObj  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleid;

	private String roledesc;

	private String rolename;

	private Integer rolestatus;

	private Integer sortnum;

	//bi-directional many-to-one association to RolemodelObj
	@OneToMany(mappedBy="tbRole",fetch=FetchType.EAGER)
	@XStreamOmitField
	private List<RolemodelObj> tbRolemodels;


	public RoleObj() {
	}

	/**
	 *
	 * @param roledesc 角色描述
	 * @param rolename 角色名称
	 * @param rolestatus 角色状态
     * @param sortnum 排序号
     */
	public RoleObj(String roledesc, String rolename, Integer rolestatus, Integer sortnum) {
		this.roledesc = roledesc;
		this.rolename = rolename;
		this.rolestatus = rolestatus;
		this.sortnum = sortnum;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRoledesc() {
		return this.roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getRolestatus() {
		return this.rolestatus;
	}

	public void setRolestatus(Integer rolestatus) {
		this.rolestatus = rolestatus;
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


}