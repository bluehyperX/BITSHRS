package com.app.hotelmanagementsystem.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Entity
@Table(
        name = "user"
       
)
public class User {

    public Long getUserId() {
		return userId;
	}

	public User() {
		super();
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false
    )
    private String emailAddress;
    @Column(
            name = "password",
            nullable = false
    )
    
    private String password;
    @Column(
            name = "role",
            nullable = false
    )
    private String role;
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "roleId"
            )
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Role> roles = new HashSet<>();

//    public User(String firstName, String lastName, String emailAddress, String password, Collection<Role> roles) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.password = password;
//        this.roles = roles;
//    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public User(Long userId, String firstName, String lastName, String emailAddress, String password, String role,
		Collection<Role> roles) {
	super();
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailAddress = emailAddress;
	this.password = password;
	this.role = role;
	this.roles = roles;
}

	public boolean hasRole(String roleName) {
        Iterator<Role> iterator = roles.iterator();

        while (iterator.hasNext()) {
            Role role = iterator.next();
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}
