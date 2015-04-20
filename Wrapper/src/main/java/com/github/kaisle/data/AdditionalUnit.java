package com.github.kaisle.data;

public class AdditionalUnit {

	private String unitName;
	private int item_0;
	private int item_1;
	private int item_2;
	private int item_3;
	private int item_4;
	private int item_5;

	public AdditionalUnit(String unitName, int item_0, int item_1, int item_2,
			int item_3, int item_4, int item_5) {
		super();
		this.unitName = unitName;
		this.item_0 = item_0;
		this.item_1 = item_1;
		this.item_2 = item_2;
		this.item_3 = item_3;
		this.item_4 = item_4;
		this.item_5 = item_5;
	}

	@Override
	public String toString() {
		return "AdditionalUnit [unitName=" + unitName + ", item_0=" + item_0
				+ ", item_1=" + item_1 + ", item_2=" + item_2 + ", item_3="
				+ item_3 + ", item_4=" + item_4 + ", item_5=" + item_5 + "]";
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getItem_0() {
		return item_0;
	}

	public void setItem_0(int item_0) {
		this.item_0 = item_0;
	}

	public int getItem_1() {
		return item_1;
	}

	public void setItem_1(int item_1) {
		this.item_1 = item_1;
	}

	public int getItem_2() {
		return item_2;
	}

	public void setItem_2(int item_2) {
		this.item_2 = item_2;
	}

	public int getItem_3() {
		return item_3;
	}

	public void setItem_3(int item_3) {
		this.item_3 = item_3;
	}

	public int getItem_4() {
		return item_4;
	}

	public void setItem_4(int item_4) {
		this.item_4 = item_4;
	}

	public int getItem_5() {
		return item_5;
	}

	public void setItem_5(int item_5) {
		this.item_5 = item_5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + item_0;
		result = prime * result + item_1;
		result = prime * result + item_2;
		result = prime * result + item_3;
		result = prime * result + item_4;
		result = prime * result + item_5;
		result = prime * result
				+ ((unitName == null) ? 0 : unitName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdditionalUnit other = (AdditionalUnit) obj;
		if (item_0 != other.item_0)
			return false;
		if (item_1 != other.item_1)
			return false;
		if (item_2 != other.item_2)
			return false;
		if (item_3 != other.item_3)
			return false;
		if (item_4 != other.item_4)
			return false;
		if (item_5 != other.item_5)
			return false;
		if (unitName == null) {
			if (other.unitName != null)
				return false;
		} else if (!unitName.equals(other.unitName))
			return false;
		return true;
	}

	
}
