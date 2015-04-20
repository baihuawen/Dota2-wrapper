package com.github.kaisle.data;

public class PlayerDetails {
	
	private long steamid; 
	private int communityVisibilityState; 
	private String personaName; 
	private String lastLogOff;
	private String profileURL;
	private String avatar;
	private String avatarMedium;
	private String avatarFull;
	private int personaState;
	public PlayerDetails(long steamid, int communityVisibilityState,
			String personaName, String lastLogOff, String profileURL,
			String avatar, String avatarMedium, String avatarFull,
			int personaState) {
		super();
		this.steamid = steamid;
		this.communityVisibilityState = communityVisibilityState;
		this.personaName = personaName;
		this.lastLogOff = lastLogOff;
		this.profileURL = profileURL;
		this.avatar = avatar;
		this.avatarMedium = avatarMedium;
		this.avatarFull = avatarFull;
		this.personaState = personaState;
	}
	public long getSteamid() {
		return steamid;
	}
	public void setSteamid(long steamid) {
		this.steamid = steamid;
	}
	public int getCommunityVisibilityState() {
		return communityVisibilityState;
	}
	public void setCommunityVisibilityState(int communityVisibilityState) {
		this.communityVisibilityState = communityVisibilityState;
	}
	public String getPersonaName() {
		return personaName;
	}
	public void setPersonaName(String personaName) {
		this.personaName = personaName;
	}
	public String getLastLogOff() {
		return lastLogOff;
	}
	public void setLastLogOff(String lastLogOff) {
		this.lastLogOff = lastLogOff;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAvatarMedium() {
		return avatarMedium;
	}
	public void setAvatarMedium(String avatarMedium) {
		this.avatarMedium = avatarMedium;
	}
	public String getAvatarFull() {
		return avatarFull;
	}
	public void setAvatarFull(String avatarFull) {
		this.avatarFull = avatarFull;
	}
	public int getPersonaState() {
		return personaState;
	}
	public void setPersonaState(int personaState) {
		this.personaState = personaState;
	}
	@Override
	public String toString() {
		return "PlayerDetails [steamid=" + steamid
				+ ", communityVisibilityState=" + communityVisibilityState
				+ ", personaName=" + personaName + ", lastLogOff=" + lastLogOff
				+ ", profileURL=" + profileURL + ", avatar=" + avatar
				+ ", avatarMedium=" + avatarMedium + ", avatarFull="
				+ avatarFull + ", personaState=" + personaState + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result
				+ ((avatarFull == null) ? 0 : avatarFull.hashCode());
		result = prime * result
				+ ((avatarMedium == null) ? 0 : avatarMedium.hashCode());
		result = prime * result + communityVisibilityState;
		result = prime * result
				+ ((lastLogOff == null) ? 0 : lastLogOff.hashCode());
		result = prime * result
				+ ((personaName == null) ? 0 : personaName.hashCode());
		result = prime * result + personaState;
		result = prime * result
				+ ((profileURL == null) ? 0 : profileURL.hashCode());
		result = prime * result + (int) (steamid ^ (steamid >>> 32));
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
		PlayerDetails other = (PlayerDetails) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (avatarFull == null) {
			if (other.avatarFull != null)
				return false;
		} else if (!avatarFull.equals(other.avatarFull))
			return false;
		if (avatarMedium == null) {
			if (other.avatarMedium != null)
				return false;
		} else if (!avatarMedium.equals(other.avatarMedium))
			return false;
		if (communityVisibilityState != other.communityVisibilityState)
			return false;
		if (lastLogOff == null) {
			if (other.lastLogOff != null)
				return false;
		} else if (!lastLogOff.equals(other.lastLogOff))
			return false;
		if (personaName == null) {
			if (other.personaName != null)
				return false;
		} else if (!personaName.equals(other.personaName))
			return false;
		if (personaState != other.personaState)
			return false;
		if (profileURL == null) {
			if (other.profileURL != null)
				return false;
		} else if (!profileURL.equals(other.profileURL))
			return false;
		if (steamid != other.steamid)
			return false;
		return true;
	} 
	
	
	
}
