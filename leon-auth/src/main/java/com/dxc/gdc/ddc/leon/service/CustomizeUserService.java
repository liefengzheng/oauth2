package com.dxc.gdc.ddc.leon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.gdc.ddc.leon.client.UserClient;
import com.dxc.gdc.ddc.leon.data.ExtensionUser;
import com.hpe.foxcloud.common.constant.ResultCode;
import com.hpe.foxcloud.common.data.GeneralContentResult;
import com.hpe.foxcloud.common.data.otd.organization.OrganizationItem;
import com.hpe.foxcloud.common.data.otd.user.UserDetailsItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("customUserService")
public class CustomizeUserService implements UserDetailsService{
	
	@Autowired
	private UserClient userClient;
	
	@Override
	public UserDetails loadUserByUsername(String _username) throws UsernameNotFoundException {
		log.debug("Going to load user info by username {}", _username);
		GeneralContentResult<UserDetailsItem> result = userClient.loadUserByLoginName(_username);
		
		if (!ResultCode.OPERATION_SUCCESS.equals(result.getResultCode())) {
            log.info("User does not exists with username {}.", _username);
            throw new UsernameNotFoundException(_username);
        }
		
		UserDetailsItem userDetailsItem = Optional.ofNullable(result.getResultContent())
                .orElseThrow(() -> new UsernameNotFoundException(_username));
		
		List<GrantedAuthority> authorities = new ArrayList<>();
        userDetailsItem.getRoles().forEach(
                roleItem -> authorities.add(new SimpleGrantedAuthority(roleItem.getRoleName()))
        );

        String organizationId = "";
        if (userDetailsItem.getOrganizations() != null && !userDetailsItem.getOrganizations().isEmpty()) {
            OrganizationItem organizationItem = userDetailsItem.getOrganizations().get(0);
            organizationId = organizationItem.getId();
        }
        
        
        ExtensionUser currentUser = new ExtensionUser(
                _username,
                userDetailsItem.getPassword(),
                true,
                true,
                true,
                true,
                authorities,
                userDetailsItem.getUserId(),
                userDetailsItem.getUserName(),
                organizationId);
        log.debug("Load user info {} ", currentUser);
        return currentUser;
	}

}
