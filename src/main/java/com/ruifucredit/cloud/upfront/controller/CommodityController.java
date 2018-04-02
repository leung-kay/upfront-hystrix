package com.ruifucredit.cloud.upfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ruifucredit.cloud.kit.dto.Outcoming;
import com.ruifucredit.cloud.upfront.pojo.dto.Commodity;
import com.ruifucredit.cloud.upfront.service.ICommodityService;

@RestController
public class CommodityController {

	@Autowired
	private ICommodityService commodityService;

	@GetMapping("commodity/{id}")
	public Outcoming<Commodity> query(@PathVariable(name = "id") Long goodsId) {
		Commodity result = commodityService.query(goodsId);
		return new Outcoming<Commodity>(result);
	}

}
