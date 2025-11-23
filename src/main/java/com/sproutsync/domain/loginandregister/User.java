package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.group.Group;
import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.util.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.sproutsync.infrastructure.apivalidation.ValidationConstants.PASSWORD_MIN_SIZE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @Size(min = 3, max = 25)
    private String surname;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String password;

    private String confirmationToken;

    private boolean enabled = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> group;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities = new HashSet<>();

    public boolean confirm() {
        this.setEnabled(true);
        this.setConfirmationToken(null);
        return true;
    }

    public User(final String email, final String password, String confirmationToken, final Set<Role> authorities) {
        this.email = email;
        this.password = password;
        this.confirmationToken = confirmationToken;
        this.authorities = authorities;
    }

    public User(final Long userId, final String username, String surname, final String email) {
        this.id = userId;
        this.username =username;
        this.surname = surname;
        this.email = email;
    }
}
