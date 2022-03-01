package org.perscholas.freelance.security;

import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.dao.UserRoleDAO;
import org.perscholas.freelance.database.entity.User;
import org.perscholas.freelance.database.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

//    public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // if the incoming username or email is out found in the database
        // this user will be null
        // TODO if you want to login using username column you can change this to findByUsername or even findByUsernameOrEmail
        User user = userDao.findByEmail(username);

        if (user == null) {
            // this means that the username was not found in the database so we are done
            // and we can get out of here
            throw new UsernameNotFoundException("Username '" + username + "' not found in database");
        }

        // we got here so it means the user was found in the database
        List<UserRole> userRoles = userRoleDao.getUserRoles(user.getId());


        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), springRoles);
    }

//	private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<Permission> permissions) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for (Permission permission : permissions) {
//			authorities.add(new SimpleGrantedAuthority(permission.getName()));
//		}
//
//		return authorities;
//	}

    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getUserRole().toString()));
        }

        // always add the user role
        //authorities.add(new SimpleGrantedAuthority(UserRoleEnum.USER.toString()));

        return authorities;
    }

}