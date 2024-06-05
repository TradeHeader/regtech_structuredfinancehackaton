package cdm.product.template.validation.datarule;

import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.FixedPricePayout;
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
@RosettaDataRule("FixedPricePayoutQuantity")
@ImplementedBy(FixedPricePayoutQuantity.Default.class)
public interface FixedPricePayoutQuantity extends Validator<FixedPricePayout> {
	
	String NAME = "FixedPricePayoutQuantity";
	String DEFINITION = "priceQuantity exists";
	
	ValidationResult<FixedPricePayout> validate(RosettaPath path, FixedPricePayout fixedPricePayout);
	
	class Default implements FixedPricePayoutQuantity {
	
		@Override
		public ValidationResult<FixedPricePayout> validate(RosettaPath path, FixedPricePayout fixedPricePayout) {
			ComparisonResult result = executeDataRule(fixedPricePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FixedPricePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FixedPricePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FixedPricePayout fixedPricePayout) {
			try {
				return exists(MapperS.of(fixedPricePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FixedPricePayoutQuantity {
	
		@Override
		public ValidationResult<FixedPricePayout> validate(RosettaPath path, FixedPricePayout fixedPricePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FixedPricePayout", path, DEFINITION);
		}
	}
}
