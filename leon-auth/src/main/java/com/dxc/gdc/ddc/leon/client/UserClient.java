package com.dxc.gdc.ddc.leon.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpe.foxcloud.common.data.GeneralContentResult;
import com.hpe.foxcloud.common.data.dto.accountcenter.AdLoginReq;
import com.hpe.foxcloud.common.data.otd.user.UserDetailsItem;

@FeignClient(url = "http://localhost:9101", name = "usermgmt")
public interface UserClient {
	@RequestMapping(value = "/authsec/users/loadByLoginName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GeneralContentResult<UserDetailsItem> loadUserByLoginName(@RequestParam("login_name") String _loginName);

    @RequestMapping(value = "/authsec/users/loadById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GeneralContentResult<UserDetailsItem> loadUserById(@RequestParam("user_id") String _id);

    @ResponseBody
    @RequestMapping(value = "/noauth/aduser/login", method = RequestMethod.POST)
    public GeneralContentResult<String> loginAdUser(@RequestBody AdLoginReq loginReq);


    @RequestMapping(value = "/noauth/user/loginnameorid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GeneralContentResult<UserDetailsItem> loadUserByUsernameOrId(@RequestParam("_loginNameOrId") String _loginNameOrId);
}
