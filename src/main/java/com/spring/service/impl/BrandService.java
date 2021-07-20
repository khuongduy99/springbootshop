package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.BrandDTO;
import com.spring.dto.CategoryDTO;
import com.spring.entity.BrandEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.BrandRepository;
import com.spring.service.IBrandService;
import com.spring.service.ICategoryService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class BrandService implements IBrandService{
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * ----------------GET--------------------
	 * 
	 */
	
	@Override
	public BrandDTO findOneByAlias(String alias) {
		BrandEntity entity = brandRepository.findOneByAlias(alias);
		if (entity == null)
			return null;
		return modelMapper.map(entity, BrandDTO.class);
	}
	
	@Override
	public BrandDTO findOneById(Long id) {
		BrandEntity entity = brandRepository.findOneById(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, BrandDTO.class);
	}
	
	@Override
	public List<BrandDTO> findAllByCategory(long id) {
		List<BrandDTO> result = new ArrayList<BrandDTO>();
		List<BrandEntity> entities = brandRepository.findAllByCategoryId(id);

		for (BrandEntity item :  entities) {
			BrandDTO dto = modelMapper.map(item, BrandDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<BrandDTO> findAllByCategory(String categoryAlias) {
		List<BrandDTO> result = new ArrayList<BrandDTO>();
		CategoryDTO categoryDTO = categoryService.findOneByAlias(categoryAlias);
		if(categoryDTO != null) {
			List<BrandEntity> entities = brandRepository.findAllByCategoryId(categoryDTO.getId());

			for (BrandEntity item :  entities) {
				BrandDTO dto = modelMapper.map(item, BrandDTO.class);
				result.add(dto);
			}
		}
		
		return result;
	}
	

	@Override
	public List<BrandDTO> findAllByCategoryAliasAndStatus(String categoryAlias, String statusCategory,
			String statusBrand) {
		List<BrandDTO> result = new ArrayList<BrandDTO>();
		CategoryDTO categoryDTO = categoryService.findOneByAlias(categoryAlias);
		if(categoryDTO != null) {
			List<BrandEntity> entities = brandRepository.findAllByCategoryAliasAndStatus(categoryAlias, statusCategory, statusBrand);

			for (BrandEntity item :  entities) {
				BrandDTO dto = modelMapper.map(item, BrandDTO.class);
				result.add(dto);
			}
		}
		return result;
	}

	/*
	 * ----------------SAVE, UDATE, DELETE--------------------
	 * 
	 */

	@Override
	public MessageAlertModel save(BrandDTO dto) {
		String alert = "", message = "";
		CategoryDTO categoryDTO = categoryService.findOneById(dto.getCategoryId());
		if(categoryDTO != null) {
			dto.setAlias(categoryDTO.getAlias() +"-"+ WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Thể Loại Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		
		BrandDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setStatus(StatusCustom.ACTIVE);
		BrandEntity entity = modelMapper.map(dto, BrandEntity.class);
		try {
			brandRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(BrandDTO dto) {
		String alert = "", message = "";
		CategoryDTO categoryDTO = categoryService.findOneById(dto.getCategoryId());
		if(categoryDTO != null) {
			dto.setAlias(categoryDTO.getAlias()  +"-"+ WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Thể Loại Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		BrandDTO oldEntity = findOneById(dto.getId());
		BrandDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null && !oldEntity.getAlias().equals(isExistDto.getAlias())) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setCreatedBy(oldEntity.getCreatedBy());
		dto.setCreatedDate(oldEntity.getCreatedDate());
		BrandEntity entity = modelMapper.map(dto, BrandEntity.class);
		try {
			brandRepository.saveAndFlush(entity);
			alert = "success";
			message = "Cập Nhật Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void deleteOne(Long id) throws BrandTransactionException {
		BrandEntity isExist = brandRepository.findOne(id);
		if(isExist == null) {
			throw new BrandTransactionException("Không tồn tại");
		}
		
		try {
			brandRepository.delete(isExist);
		} catch (Exception e) {
			throw new BrandTransactionException("\""+isExist.getName()+"\" Không thể xóa do có chứa các sản phẩm.");
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BrandTransactionException.class)
	public void deleteAll(Long[] ids) throws BrandTransactionException {
		for(Long id : ids) {
			deleteOne(id);
		}
	}

	@Override
	public MessageAlertModel delete(Long[] ids) {
		try {
			deleteAll(ids);
			return new MessageAlertModel("success", "Đã xóa thành công!", new Date());
        } catch (BrandTransactionException e) {
        	return new MessageAlertModel("danger", e.getMessage(), new Date());
        }
		
	}
	
}

class BrandTransactionException extends Exception {
	private static final long serialVersionUID = -3128681006635769411L;
    
    public BrandTransactionException(String message) {
        super(message);
    }
}
