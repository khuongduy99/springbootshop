package com.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
	public abstract class BaseEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column
		private String alias;
		
		@Column
		private String status;
		
		@Column
		private String createdByUserId;
		
		@Column
		private Date createdDate;
		
		@Column
		private String updatedByUserId;
		
		@Column
		private Date updatedDate;
		
		public Long getId() {
			return id;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getCreatedByUserId() {
			return createdByUserId;
		}

		public void setCreatedByUserId(String createdByUserId) {
			this.createdByUserId = createdByUserId;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getUpdatedByUserId() {
			return updatedByUserId;
		}

		public void setUpdatedByUserId(String updatedByUserId) {
			this.updatedByUserId = updatedByUserId;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setId(Long id) {
			this.id = id;
		}

		
}
