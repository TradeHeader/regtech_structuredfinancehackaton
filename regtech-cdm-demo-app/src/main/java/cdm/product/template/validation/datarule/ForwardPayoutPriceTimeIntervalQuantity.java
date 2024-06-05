package cdm.product.template.validation.datarule;

import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.AssetDeliveryPeriods;
import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.ForwardPayout;
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
@RosettaDataRule("ForwardPayoutPriceTimeIntervalQuantity")
@ImplementedBy(ForwardPayoutPriceTimeIntervalQuantity.Default.class)
public interface ForwardPayoutPriceTimeIntervalQuantity extends Validator<ForwardPayout> {
	
	String NAME = "ForwardPayoutPriceTimeIntervalQuantity";
	String DEFINITION = "if schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity exists then delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if delivery -> periods -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity is absent else if schedule -> schedulePeriod -> deliveryPeriod -> profile -> block -> priceTimeIntervalQuantity exists then schedule -> schedulePeriod -> deliveryPeriod -> priceTimeIntervalQuantity is absent and delivery -> periods -> profile -> block -> priceTimeIntervalQuantity is absent";
	
	ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout);
	
	class Default implements ForwardPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			ComparisonResult result = executeDataRule(forwardPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ForwardPayout forwardPayout) {
			try {
				if (exists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(forwardPayout).<AssetDeliveryInformation>map("getDelivery", _forwardPayout -> _forwardPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(forwardPayout).<AssetDeliveryInformation>map("getDelivery", _forwardPayout -> _forwardPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				if (exists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())).getOrDefault(false)) {
					return notExists(MapperS.of(forwardPayout).<CalculationSchedule>map("getSchedule", _forwardPayout -> _forwardPayout.getSchedule()).<SchedulePeriod>mapC("getSchedulePeriod", calculationSchedule -> calculationSchedule.getSchedulePeriod()).<CalculationScheduleDeliveryPeriods>map("getDeliveryPeriod", schedulePeriod -> schedulePeriod.getDeliveryPeriod()).<Price>map("getPriceTimeIntervalQuantity", calculationScheduleDeliveryPeriods -> calculationScheduleDeliveryPeriods.getPriceTimeIntervalQuantity())).and(notExists(MapperS.of(forwardPayout).<AssetDeliveryInformation>map("getDelivery", _forwardPayout -> _forwardPayout.getDelivery()).<AssetDeliveryPeriods>map("getPeriods", assetDeliveryInformation -> assetDeliveryInformation.getPeriods()).<AssetDeliveryProfile>mapC("getProfile", assetDeliveryPeriods -> assetDeliveryPeriods.getProfile()).<AssetDeliveryProfileBlock>mapC("getBlock", assetDeliveryProfile -> assetDeliveryProfile.getBlock()).<Price>map("getPriceTimeIntervalQuantity", assetDeliveryProfileBlock -> assetDeliveryProfileBlock.getPriceTimeIntervalQuantity())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ForwardPayoutPriceTimeIntervalQuantity {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
		}
	}
}
