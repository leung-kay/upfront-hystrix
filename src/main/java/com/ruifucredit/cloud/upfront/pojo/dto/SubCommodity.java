package com.ruifucredit.cloud.upfront.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubCommodity {
	
	private Long subGoodsId;
	private Long goodsId;
	private Long stockId;
	private String subName;
	private BigDecimal goodsPrice;
	private Date createTime;
	private Date updateTime;
	private String subStatus;
	private BigDecimal stockNumber;
	private String stockStatus;
	
}
