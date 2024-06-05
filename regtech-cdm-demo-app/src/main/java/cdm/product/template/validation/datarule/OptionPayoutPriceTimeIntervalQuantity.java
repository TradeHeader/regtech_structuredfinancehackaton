package cdm.product.template.validation.datarule;

import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.OptionPayout;
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
@RosettaDataRule("OptionPayoutPriceTimeIntervalQuantity")
@ImplementedBy(OptionPayoutPriceTimeIntervalQuantity.Default.class)
public interface OptionPayoutPriceTimeIntervalQuantity extends Validator<OptionPayout> {
	
	String NAME = "OptionPayoutPriceTimeIntervalQuantity";
	String DEFINITION = "if schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity exists then delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if delivery -> periods -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent";
	
	ValidationResult<OptionPayout> validate(RosettaPath path, OptionPayout optionPayout);
	
	class Default implements OptionPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<OptionPayout> validate(RosettaPath path, OptionPayout optionPayout) {
			ComparisonResult result = executeDataRule(optionPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionPayout optionPayout) {
			try {
				if (exists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(optionPayout).<AssetDeliveryInformation>map("getDelivery", _optionPayout -> _optionPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(optionPayout).<AssetDeliveryInformation>map("getDelivery", _optionPayout -> _optionPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(optionPayout).<CalculationSchedule>map("getSchedule", _optionPayout -> _optionPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(optionPayout).<AssetDeliveryInformation>map("getDelivery", _optionPayout -> _optionPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<OptionPayout> validate(RosettaPath path, OptionPayout optionPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionPayout", path, DEFINITION);
		}
	}
}
