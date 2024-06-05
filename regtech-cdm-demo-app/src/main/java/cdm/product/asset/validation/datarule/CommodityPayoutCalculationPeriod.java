package cdm.product.asset.validation.datarule;

import cdm.product.asset.CommodityPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("CommodityPayoutCalculationPeriod")
@ImplementedBy(CommodityPayoutCalculationPeriod.Default.class)
public interface CommodityPayoutCalculationPeriod extends Validator<CommodityPayout> {
	
	String NAME = "CommodityPayoutCalculationPeriod";
	String DEFINITION = "required choice schedule, calculationPeriodDates";
	
	ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout commodityPayout);
	
	class Default implements CommodityPayoutCalculationPeriod {
	
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
				return choice(MapperS.of(commodityPayout), Arrays.asList("schedule", "calculationPeriodDates"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityPayoutCalculationPeriod {
	
		@Override
		public ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout commodityPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityPayout", path, DEFINITION);
		}
	}
}
