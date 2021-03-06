package ru.otus.spring.homework.springproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.models.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String login;
    private String password;

    public Integer getAge() {
        return age;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    private Integer age;
    private Integer accessLevel;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        var c = new CustomUserDetails();
        c.login = user.getUsername();
        c.password = user.getPassword();
        c.age = user.getAge();
        c.accessLevel = user.getAccessLevel();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role privilege : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege.getRoleName()));
        }
        c.grantedAuthorities = authorities;
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
