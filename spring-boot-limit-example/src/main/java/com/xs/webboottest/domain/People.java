package com.xs.webboottest.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "people")
public class People {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String namep;
	private String decp;
	private BigDecimal acount;
}
