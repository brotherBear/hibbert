package hibbert.domain;

import javax.persistence.Column;


public class C extends B {

	private static final long serialVersionUID = 1L;

	@Column
	private int count;

	
	public int getCount() {
		return count;
	}

	
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
