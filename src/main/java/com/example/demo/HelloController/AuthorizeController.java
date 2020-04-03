package com.example.demo.HelloController;


import com.example.demo.dto.AccessTolenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTolenDTO accessTolenDTO = new AccessTolenDTO();
        accessTolenDTO.setCode(code);
        accessTolenDTO.setRedirect_uri(redirectUri);
        accessTolenDTO.setClient_id(clientId);
        accessTolenDTO.setClient_secret(clientSecret);
        accessTolenDTO.setState(state);
        String accrssToken = githubProvider.getAccessToken(accessTolenDTO);
        GithubUser githubUser = githubProvider.getUser(accrssToken);
        if(githubUser != null)
        {
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccoinId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登录生共cookie和session
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";

        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
