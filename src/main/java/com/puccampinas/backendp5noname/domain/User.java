package com.puccampinas.backendp5noname.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.puccampinas.backendp5noname.dtos.SignupDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Document(collection="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    private String id;
    @Indexed(unique=true)
    @NonNull
    private String login;
    @JsonIgnore
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    @NonNull
    private String document;
    @NonNull
    private double weight;
    @NonNull
    private StatusUser status;
    @JsonFormat(pattern="dd/MM/yyyy 'as' HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="dd/MM/yyyy 'as' HH:mm:ss")
    private LocalDateTime updatedAt;

    public User(SignupDTO userDTO, String password) {
        this.login = userDTO.getEmail();
        this.password = password;
        this.document = userDTO.getDocument();
        this.weight = Double.parseDouble(userDTO.getWeight());
        this.fullName = userDTO.getFullName();
        this.status = StatusUser.ATIVO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return null;
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