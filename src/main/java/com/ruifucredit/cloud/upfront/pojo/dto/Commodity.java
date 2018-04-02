package com.ruifucredit.cloud.upfront.pojo.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Commodity {
	
	private Long goodsId;
	private String goodsName;
	private String goodsType;
	private Date createTime;
	private Date updateTime;
	private String goodsStatus;
	private List<SubCommodity> subCommodities;
	
}
