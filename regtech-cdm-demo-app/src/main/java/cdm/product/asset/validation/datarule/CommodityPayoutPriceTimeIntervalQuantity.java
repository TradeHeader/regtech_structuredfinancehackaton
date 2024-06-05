package cdm.product.asset.validation.datarule;

import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.asset.CommodityPayout;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.SchedulePeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("CommodityPayoutPriceTimeIntervalQuantity")
@ImplementedBy(CommodityPayoutPriceTimeIntervalQuantity.Default.class)
public interface CommodityPayoutPriceTimeIntervalQuantity extends Validator<CommodityPayout> {
	
	String NAME = "CommodityPayoutPriceTimeIntervalQuantity";
	String DEFINITION = "if schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity exists then delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if delivery -> periods -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent";
	
	ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout commodityPayout);
	
	class Default implements CommodityPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout commodityPayout) {
			ComparisonResult result = executeDataRule(commodityPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CommodityPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CommodityPayout commodityPayout) {
			try {
				if (exists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(commodityPayout).<AssetDeliveryInformation>map("getDelivery", _commodityPayout -> _commodityPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(commodityPayout).<AssetDeliveryInformation>map("getDelivery", _commodityPayout -> _commodityPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(commodityPayout).<CalculationSchedule>map("getSchedule", _commodityPayout -> _commodityPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(commodityPayout).<AssetDeliveryInformation>map("getDelivery", _commodityPayout -> _commodityPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout commodityPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityPayout", path, DEFINITION);
		}
	}
}
