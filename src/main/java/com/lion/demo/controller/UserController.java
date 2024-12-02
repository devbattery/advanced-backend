package com.lion.demo.controller;

import com.lion.demo.UserService;
import com.lion.demo.entity.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
        if (userService.findByUid(uid) == null && pwd.equals(pwd2) && pwd.length() >= 4) {
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            User user = User.builder()
                    .uid(uid).pwd(hashedPwd).uname(uname).email(email)
                    .regDate(LocalDate.now())
                    .role("ROLE_USER")
                    .build();

            userService.registerUser(user);
        }

        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getUsers();
        users.forEach(System.out::println);
        model.addAttribute("userList", users);
        return "user/list";
    }

    @GetMapping("/delete/{uid}")
    public String delete(@PathVariable String uid) {
        userService.deleteUser(uid);
        return "redirect:/user/list";
    }

    @GetMapping("/update/{uid}")
    public String updateForm(@PathVariable String uid, Model model) {
        User user = userService.findByUid(uid);
        model.addAttribute("user", user);
        return "user/update";
    }

    @GetMapping("/update")
    public String updateProc(String uid, String pwd, String pwd2, String uname, String email, String role) {
        User user = userService.findByUid(uid);
        if (pwd.equals(pwd2) && pwd.length() >= 4) {
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            user.setPwd(hashedPwd);
        }

        user.setUid(uname);
        user.setEmail(email);
        user.setRole(role);

        userService.updateUser(user);

        return "redirect:/user/list";
    }
}
