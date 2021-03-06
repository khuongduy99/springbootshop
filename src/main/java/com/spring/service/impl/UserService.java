package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dto.RoleDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.RoleEntity;
import com.spring.entity.UserEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.UserRepository;
import com.spring.service.IUserService;
import com.spring.utils.EncrytedPasswordUtils;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private ModelMapper modelMapper;
	



	/*
	 * ----------------GET--------------------
	 * 
	 */
	@Override
	public List<UserDTO> findAllByRoleAlias(String roleAlias) {
		List<UserDTO> result = new ArrayList<UserDTO>();
		RoleDTO roleDTO = roleService.findOneByAlias(roleAlias);
		if(roleDTO != null) {
			List<UserEntity> entities = userRepository.findAllByRoleId(roleDTO.getId());
			for (UserEntity item : entities) {
				UserDTO dto = modelMapper.map(item, UserDTO.class);
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public List<UserDTO> findAllByRoleId(Long roleId) {
		List<UserDTO> result = new ArrayList<UserDTO>();
		
			List<UserEntity> entities = userRepository.findAllByRoleId(roleId);
			for (UserEntity item : entities) {
				UserDTO dto = modelMapper.map(item, UserDTO.class);
				result.add(dto);
				
			}
		return result;
	}
	
	@Override
	public UserDTO findOneById(Long id) {
		UserEntity entity = userRepository.findOneById(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, UserDTO.class);
	}

	
	@Override
	public MessageAlertModel register(UserDTO dto) {
		UserEntity isExist = userRepository.findOneByEmail(dto.getEmail());
		if(isExist != null) {
			return new MessageAlertModel("danger", "Email ????ng k?? ???? t???n t???i. Vui l??ng s??? d???ng Email kh??c!", new Date());
		}
		isExist = userRepository.findOneByUserName(dto.getUserName());
		if(isExist != null) {
			return new MessageAlertModel("danger", "T??n ????ng nh???p n??y ???? t???n t???i!", new Date());
		}
		String cssButton = "background: #222;height: 50px;min-width: 150px;border: none;border-radius: 10px;color: #eee;font-size: 20px;font-family: 'Cookie', cursive;";
		cssButton+= "position: relative;display: flex;cursor: pointer !important; padding-top: 5px;";
				
		String urlRegister = "http://localhost:8080/register";
		String textForm = "<form action=\""+urlRegister+"\" method=\"POST\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"userName\" value=\""+dto.getUserName()+"\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"fullName\" value=\""+dto.getFullName()+"\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"email\" value=\""+dto.getEmail()+"\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"password\" value=\""+dto.getPassword()+"\">";
		textForm+="<button class=\"btn btn-block bg-pink waves-effect\" style=\""+cssButton+"\" type=\"submit\">Vui l??ng Click v??o ????y ????? X??C TH???C ????NG K??</button></form>";
		WebUtils.sendEmail(dto.getEmail(), "Y??U C???U X??C TH???C", textForm);
		return new MessageAlertModel("success", "Vui l??ng truy c???p v??o Email ????ng k?? ????? x??c th???c!", new Date());
	}

	@Override
	public MessageAlertModel login(String userName, String password) {
		
		UserEntity isExist = userRepository.findOneByEmail(userName);
		if(isExist == null) isExist = userRepository.findOneByUserName(userName);
		if (!checkMatchesPassword(password, isExist)) {
            return new MessageAlertModel("danger", "T??n ????ng nh???p ho???c m???t kh???u kh??ng ch??nh x??c!", new Date());
        }
		return new MessageAlertModel("success", "????ng nh???p!", new Date());
	}
	
	public boolean checkMatchesPassword(String password, UserEntity user) {
		if(user == null) return false;
		return new BCryptPasswordEncoder().matches(password, user.getPassword());
	}

	@Override
	@Transactional
	public MessageAlertModel save(UserDTO dto) {
		String alert = "", message = "";
		UserEntity isExist = userRepository.findOneByEmail(dto.getEmail());
		if(isExist != null) {
			return new MessageAlertModel("danger", "Email ????ng k?? ???? t???n t???i. Vui l??ng s??? d???ng Email kh??c!", new Date());
		}
		isExist = userRepository.findOneByUserName(dto.getUserName());
		if(isExist != null) {
			return new MessageAlertModel("danger", "T??n ????ng nh???p n??y ???? t???n t???i!", new Date());
		}
		dto.setStatus(StatusCustom.ACTIVE);
		dto.setPassword(EncrytedPasswordUtils.encrytePassword(dto.getPassword()));
		UserEntity entity = modelMapper.map(dto, UserEntity.class);
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		RoleEntity role = modelMapper.map(roleService.findOneById(1L), RoleEntity.class);
		roles.add(role);
		entity.setListRole(roles);
		try {
			userRepository.save(entity);
			alert = "success";
			message = "????ng k?? th??nh c??ng!";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel updateRoleUser(UserDTO dto) {
		UserEntity user = userRepository.findOneById(dto.getId());
		if(user == null) return new MessageAlertModel("danger", "T??i kho???n kh??ng t???n t???i", new Date());
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		for(Long id : dto.getRolesId()) {
			RoleDTO role = roleService.findOneById(id);
			if(role == null) return new MessageAlertModel("danger", "Vai tr?? kh??ng t???n t???i", new Date());
			listRole.add(modelMapper.map(role, RoleEntity.class));
		}
		user.setListRole(listRole);
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
		return new MessageAlertModel("success", "C???p nh???t th??nh c??ng", new Date());
	}

	@Override
	public MessageAlertModel updateStatusUser(UserDTO dto) {
		UserEntity user = userRepository.findOneById(dto.getId());
		if(user == null) return new MessageAlertModel("danger", "T??i kho???n kh??ng t???n t???i", new Date());
		user.setStatus(dto.getStatus() );
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
		return new MessageAlertModel("success", "C???p nh???t th??nh c??ng", new Date());
	}

	@Override
	public MessageAlertModel update(UserDTO dto) {
		String alert = "", message = "";
		UserEntity oldEntity = userRepository.findOneById(dto.getId());
		oldEntity.setAddress(dto.getAddress());
		oldEntity.setFullName(dto.getFullName());
		oldEntity.setPhone(dto.getPhone());
		oldEntity.setGender(dto.getGender());
		try {
			userRepository.saveAndFlush(oldEntity);
			alert = "success";
			message = "C???p nh???t th??nh c??ng!";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel updatePasswordUser(Long id, String oldPassword, String newPassword) {
		String alert = "", message = "";
		UserEntity oldEntity = userRepository.findOneById(id);
			if(!checkMatchesPassword(oldPassword, oldEntity)) {
				return new MessageAlertModel("danger", "M???t kh???u hi???n t???i kh??ng ????ng", new Date());
			}
			oldEntity.setPassword(EncrytedPasswordUtils.encrytePassword(newPassword));
			try {
				userRepository.save(oldEntity);
				alert = "success";
				message = "?????i m???t kh???u th??nh c??ng!";
			} catch (Exception e) {
				alert = "danger";
				message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
			}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public int countByYearAndMonth(int year, int month) {
		return userRepository.countByYearAndMonth(year, month);
	}

	@Override
	public int countByYearAndMonthAndDay(int year, int month, int day) {
		return userRepository.countByYearAndMonthAndDay(year, month, day);
	}

	@Override
	public MessageAlertModel forgotPassword(String email) {
		UserEntity isExist = userRepository.findOneByEmail(email);
		if(isExist == null) {
			return new MessageAlertModel("danger", "Email n??y kh??ng t???n t???i trong h??? th???ng ch??ng t??i.", new Date());
		}
		String cssButton = "background: #222;height: 50px;min-width: 150px;border: none;border-radius: 10px;color: #eee;font-size: 20px;font-family: 'Cookie', cursive;";
		cssButton+= "position: relative;display: flex;cursor: pointer !important; padding-top: 5px;";
				
		String urlRegister = "http://localhost:8080/reset-password";
		String textForm = "<form action=\""+urlRegister+"\" method=\"POST\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"id\" value=\""+isExist.getId()+"\">";
		textForm+="<input type=\"hidden\" class=\"form-control\" name=\"email\" value=\""+email+"\">";
		textForm+="<button class=\"btn btn-block bg-pink waves-effect\" style=\""+cssButton+"\" type=\"submit\">Vui l??ng Click v??o ????y ????? nh???n m???t li??n k???t ????? ?????t l???i m???t kh???u c???a b???n</button></form>";
		WebUtils.sendEmail(email, "?????T L???I M???T KH???U C???A B???N", textForm);
		return new MessageAlertModel("success", "Vui l??ng truy c???p v??o Email ????ng k?? ????? nh???n li??n k???t ?????i m???t kh???u!", new Date());
	}

	@Override
	public MessageAlertModel updatePasswordUser(String email, String password) {
		String alert = "", message = "";
		UserEntity oldEntity = userRepository.findOneByEmail(email);
			
			oldEntity.setPassword(EncrytedPasswordUtils.encrytePassword(password));
			try {
				userRepository.save(oldEntity);
				alert = "success";
				message = "?????i m???t kh???u th??nh c??ng!";
			} catch (Exception e) {
				alert = "danger";
				message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
			}
			return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public int countByRoleId(Long id) {
		return userRepository.countByRoleId(id);
	}
}