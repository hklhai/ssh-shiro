package cn.edu.ncut.model.view;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the v_map database table.
 * 
 */
@Entity
@Table(name="v_map")
public class VMap implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigInteger counter;
	@Id
	private String location;

	private String pubtype;

	public VMap() {
	}

	public BigInteger getCounter() {
		return this.counter;
	}

	public void setCounter(BigInteger counter) {
		this.counter = counter;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPubtype() {
		return this.pubtype;
	}

	public void setPubtype(String pubtype) {
		this.pubtype = pubtype;
	}

}