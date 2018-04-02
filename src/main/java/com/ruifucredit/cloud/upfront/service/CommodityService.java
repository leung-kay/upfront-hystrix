package com.ruifucredit.cloud.upfront.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.commodity.support.dto.Goods;
import com.ruifucredit.cloud.commodity.support.dto.SubGoods;
import com.ruifucredit.cloud.inventory.support.dto.Stock;
import com.ruifucredit.cloud.kit.dto.Outcoming;
import com.ruifucredit.cloud.upfront.pojo.dto.Commodity;
import com.ruifucredit.cloud.upfront.pojo.dto.SubCommodity;
import com.ruifucredit.cloud.upfront.repository.http.GoodsClient;
import com.ruifucredit.cloud.upfront.repository.http.StockClient;

import lombok.NonNull;
import lombok.SneakyThrows;

@Service
public class CommodityService implements ICommodityService {

	@Autowired
	private GoodsClient goodsClient;
	@Autowired
	private StockClient stockClient;

	@SneakyThrows
	@Override
	public Commodity query(@NonNull Long goodsId) {

		// commodity主要由goods组成
		Commodity result = new Commodity();

		// subcommodities由subGoods和stocks组成
		List<SubCommodity> subCommodities = new ArrayList<>();
		List<SubGoods> subGoodses;
		List<Stock> stocks;

		Outcoming<Goods> goodsOutcoming = goodsClient.queryGoods("http://COMMODITY-SERVICE/goods/" + goodsId);

		if (goodsOutcoming.getCode() == Outcoming.OK_CODE) {

			Outcoming<List<Stock>> stocksOutcoming = stockClient.queryStocks("http://INVENTORY-SERVICE/stock/commodity/" + goodsId);

			if (stocksOutcoming.getCode() == Outcoming.OK_CODE) {

				BeanUtils.copyProperties(goodsOutcoming.getResult(), result, "subGoodses");

				subGoodses = goodsOutcoming.getResult().getSubGoodses();

				stocks = stocksOutcoming.getResult();

				for (SubGoods subGoods : subGoodses) {
					for (Stock stock : stocks) {
						if (subGoods.getSubGoodsId() == stock.getSubGoodsId()) {
							SubCommodity subCommodity = new SubCommodity();
							BeanUtils.copyProperties(subGoods, subCommodity);
							BeanUtils.copyProperties(stock, subCommodity, "createTime", "updateTime");
							subCommodities.add(subCommodity);
							break;
						}
					}
				}
			}
		}

		return result.setSubCommodities(subCommodities);

	}

}
