package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_product")
public class Product implements Serializable {

	@Id
	private Long pid;

	private String pname;

	private java.math.BigDecimal price;

	@Column(name = "sale_price")
	private java.math.BigDecimal salePrice;

	@Column(name = "type_id")
	private Long typeId;

	private Short status;

	private String pimage;

	private Short flag;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

	@Column(name = "create_user")
	private Long createUser;

	@Column(name = "update_user")
	private Long updateUser;

}
