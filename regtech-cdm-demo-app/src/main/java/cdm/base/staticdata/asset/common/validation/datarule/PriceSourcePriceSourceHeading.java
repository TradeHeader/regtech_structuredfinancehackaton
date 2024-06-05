package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.PriceSource;
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
@RosettaDataRule("PriceSourcePriceSourceHeading")
@ImplementedBy(PriceSourcePriceSourceHeading.Default.class)
public interface PriceSourcePriceSourceHeading extends Validator<PriceSource> {
	
	String NAME = "PriceSourcePriceSourceHeading";
	String DEFINITION = "if priceSourceHeading exists then priceSourceLocation exists";
	
	ValidationResult<PriceSource> validate(RosettaPath path, PriceSource priceSource);
	
	class Default implements PriceSourcePriceSourceHeading {
	
		@Override
		public ValidationResult<PriceSource> validate(RosettaPath path, PriceSource priceSource) {
			ComparisonResult result = executeDataRule(priceSource);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSource", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceSource", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceSource priceSource) {
			try {
				if (exists(MapperS.of(priceSource).<String>map("getPriceSourceHeading", _priceSource -> _priceSource.getPriceSourceHeading())).getOrDefault(false)) {
					return exists(MapperS.of(priceSource).<String>map("getPriceSourceLocation", _priceSource -> _priceSource.getPriceSourceLocation()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceSourcePriceSourceHeading {
	
		@Override
		public ValidationResult<PriceSource> validate(RosettaPath path, PriceSource priceSource) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSource", path, DEFINITION);
		}
	}
}
