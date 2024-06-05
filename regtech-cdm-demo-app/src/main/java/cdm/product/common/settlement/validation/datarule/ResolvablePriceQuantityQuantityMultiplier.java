package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.metafields.ReferenceWithMetaResolvablePriceQuantity;
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
@RosettaDataRule("ResolvablePriceQuantityQuantityMultiplier")
@ImplementedBy(ResolvablePriceQuantityQuantityMultiplier.Default.class)
public interface ResolvablePriceQuantityQuantityMultiplier extends Validator<ResolvablePriceQuantity> {
	
	String NAME = "ResolvablePriceQuantityQuantityMultiplier";
	String DEFINITION = "if quantityMultiplier exists then quantityReference -> reference exists";
	
	ValidationResult<ResolvablePriceQuantity> validate(RosettaPath path, ResolvablePriceQuantity resolvablePriceQuantity);
	
	class Default implements ResolvablePriceQuantityQuantityMultiplier {
	
		@Override
		public ValidationResult<ResolvablePriceQuantity> validate(RosettaPath path, ResolvablePriceQuantity resolvablePriceQuantity) {
			ComparisonResult result = executeDataRule(resolvablePriceQuantity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResolvablePriceQuantity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ResolvablePriceQuantity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ResolvablePriceQuantity resolvablePriceQuantity) {
			try {
				if (exists(MapperS.of(resolvablePriceQuantity).<QuantityMultiplier>map("getQuantityMultiplier", _resolvablePriceQuantity -> _resolvablePriceQuantity.getQuantityMultiplier())).getOrDefault(false)) {
					return exists(MapperS.of(resolvablePriceQuantity).<ReferenceWithMetaResolvablePriceQuantity>map("getQuantityReference", _resolvablePriceQuantity -> _resolvablePriceQuantity.getQuantityReference()).map("getReference", a->a.getGlobalReference()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResolvablePriceQuantityQuantityMultiplier {
	
		@Override
		public ValidationResult<ResolvablePriceQuantity> validate(RosettaPath path, ResolvablePriceQuantity resolvablePriceQuantity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResolvablePriceQuantity", path, DEFINITION);
		}
	}
}
