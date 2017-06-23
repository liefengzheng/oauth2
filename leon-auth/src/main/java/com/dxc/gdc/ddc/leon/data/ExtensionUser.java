package com.dxc.gdc.ddc.leon.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ExtensionUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	private String userId;

    private String userName;

    private String organizationId;

    public ExtensionUser(String username,
                          String password,
                          boolean enabled,
                          boolean accountNonExpired,
                          boolean credentialsNonExpired,
                          boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities,
                          String userId,
                          String userName,
                          String organizationId
    ) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);

        this.userId = userId;
        this.userName = userName;
        this.organizationId = organizationId;
    }

}
