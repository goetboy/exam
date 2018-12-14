package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.security.JWTUtil;
import pers.goetboy.services.LoginRepository;

import java.util.List;

/**
 * @author:goetboy;
 * @date 2018 /12 /06
 **/
@Component("jwtUserDetail")
public class LoginRepository implements UserDetailsService {
	@Autowired
	UserMapper userMapper;

	@Autowired
	JWTUtil jwtUtil;
	@Autowired
	RoleMapper roleMapper;

	public User findByUsername(String username) {
		User user = userMapper.findByUserName(username);
		return user;
	}

	public User getcurrentUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.findByUsername(userDetails.getUsername());
	}

	/**
	 * 登陆
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return token
	 */
	public String login(String username, String password) {

	//	UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
	//	Authentication authentication = authenticationManager.authenticate(upToken);
	//	SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = findByUsername(username);
		String token = jwtUtil.generateToken(userDetails);
		return token;
	}

	/**
	 * 注册用户
	 *
	 * @param user
	 * @return
	 */
	public void register(User user) throws ServiceTipsException {
		String username = user.getUsername();
		if (findByUsername(username) != null) {
			throw new ServiceTipsException("用户已存在");
		}

		userMapper.dynamicInsert(encodePassword(user));
	}

	/**
	 * 登出
	 */
	public void loginOut() {
		SecurityContextHolder.clearContext();
	}

	/**
	 * 刷新token
	 *
	 * @param oldToken
	 * @return
	 */
	public String refreshToken(String oldToken) {
		String token = oldToken.substring(jwtUtil.getJwtConfig().getHeader().length());
		return jwtUtil.refreshToken(token);
	}

	/**
	 * 加密用户密码
	 *
	 * @param password
	 */
	private String encodePassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		return bCryptPasswordEncoder.encode(password);
	}

	/**
	 * 加密用户密码
	 *
	 * @param user
	 */
	private User encodePassword(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			user.setRoles(loadUserRoles(user.getId()));
			return user;
		}
	}

	public List<Role> loadUserRoles(Long userId) {
		List<Role> roles = roleMapper.findByUserId(userId);
		if (roles == null || roles.isEmpty()) {
			return null;
		} else {
			return roles;
		}

	}
}
