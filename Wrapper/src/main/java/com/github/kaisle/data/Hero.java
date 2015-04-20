package com.github.kaisle.data;

import com.github.kaisle.util.Defines;


public class Hero {

	private String id;
	private String name; 
	private String displayName;
	private String smallPicURL;
	private String mediumPicURL;
	private String largeHorizontalPicURL; 
	private String largeVerticalPicURL; // JPG
	
	public Hero (String id) {
	this.id = id;
		
	}
	
	
	public void addURLS() {
		String sub = displayName.substring(displayName.indexOf("_") + 1);
		sub = sub.substring(sub.indexOf("_") + 1);
		sub = sub.substring(sub.indexOf("_") + 1);
		smallPicURL = Defines.imageURL + sub + "_" + Defines.smallPic;
		mediumPicURL = Defines.imageURL + sub + "_" + Defines.mediumPic;
		largeHorizontalPicURL = Defines.imageURL + sub + "_" + Defines.horiPic;
		largeVerticalPicURL = Defines.imageURL + sub + "_" + Defines.vertPic;
	}
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSmallPicURL() {
		return smallPicURL;
	}

	public void setSmallPicURL(String smallPicURL) {
		this.smallPicURL = smallPicURL;
	}

	public String getMediumPicURL() {
		return mediumPicURL;
	}

	public void setMediumPicURL(String mediumPicURL) {
		this.mediumPicURL = mediumPicURL;
	}

	public String getLargeHorizontalPicURL() {
		return largeHorizontalPicURL;
	}

	public void setLargeHorizontalPicURL(String largeHorizontalPicURL) {
		this.largeHorizontalPicURL = largeHorizontalPicURL;
	}

	public String getLargeVerticalPicURL() {
		return largeVerticalPicURL;
	}

	public void setLargeVerticalPicURL(String largeVerticalPicURL) {
		this.largeVerticalPicURL = largeVerticalPicURL;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", displayName="
				+ displayName + ", smallPicURL=" + smallPicURL
				+ ", mediumPicURL=" + mediumPicURL + ", largeHorizontalPicURL="
				+ largeHorizontalPicURL + ", largeVerticalPicURL="
				+ largeVerticalPicURL + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((largeHorizontalPicURL == null) ? 0 : largeHorizontalPicURL
						.hashCode());
		result = prime
				* result
				+ ((largeVerticalPicURL == null) ? 0 : largeVerticalPicURL
						.hashCode());
		result = prime * result
				+ ((mediumPicURL == null) ? 0 : mediumPicURL.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((smallPicURL == null) ? 0 : smallPicURL.hashCode());
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
		Hero other = (Hero) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (largeHorizontalPicURL == null) {
			if (other.largeHorizontalPicURL != null)
				return false;
		} else if (!largeHorizontalPicURL.equals(other.largeHorizontalPicURL))
			return false;
		if (largeVerticalPicURL == null) {
			if (other.largeVerticalPicURL != null)
				return false;
		} else if (!largeVerticalPicURL.equals(other.largeVerticalPicURL))
			return false;
		if (mediumPicURL == null) {
			if (other.mediumPicURL != null)
				return false;
		} else if (!mediumPicURL.equals(other.mediumPicURL))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (smallPicURL == null) {
			if (other.smallPicURL != null)
				return false;
		} else if (!smallPicURL.equals(other.smallPicURL))
			return false;
		return true;
	}
	
	
	
	
	
}
