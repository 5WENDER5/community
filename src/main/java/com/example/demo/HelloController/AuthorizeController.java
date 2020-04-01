package com.example.demo.HelloController;


import com.example.demo.dto.AccessTolenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTolenDTO accessTolenDTO = new AccessTolenDTO();
        accessTolenDTO.setCode(code);
        accessTolenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTolenDTO.setClient_id("5ef74cb78c4bf6246efa");
        accessTolenDTO.setClient_secret("fe393a9ec2df69dcefde50eb1c70dc234b6e09b5");
        accessTolenDTO.setState(state);
        String accrssToken = githubProvider.getAccessToken(accessTolenDTO);
        GithubUser user = githubProvider.getUser(accrssToken);
        System.out.println(user.getName());
        return "index";
    }
}
