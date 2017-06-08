package cn.edu.ncut.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pub_map database table.
 * 
 */
@Entity
@Table(name="pub_map")
public class PubMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id

	private int pubid;

	private String location;

	private String pubname;

	private String pubtype;

	public PubMap() {
	}

	public int getPubid() {
		return this.pubid;
	}

	public void setPubid(int pubid) {
		this.pubid = pubid;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPubname() {
		return this.pubname;
	}

	public void setPubname(String pubname) {
		this.pubname = pubname;
	}

	public String getPubtype() {
		return this.pubtype;
	}

	public void setPubtype(String pubtype) {
		this.pubtype = pubtype;
	}

}