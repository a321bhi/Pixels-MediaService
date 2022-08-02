package com.pixels.mediaservices.dto;

import java.io.Serializable;
import java.util.Objects;

public class PixelsUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public PixelsUserDTO() {
		super();
	}

	public PixelsUserDTO(String username) {
		super();
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PixelsUserDTO other = (PixelsUserDTO) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "PixelSenseUser [username=" + username + "]";
	}

}
