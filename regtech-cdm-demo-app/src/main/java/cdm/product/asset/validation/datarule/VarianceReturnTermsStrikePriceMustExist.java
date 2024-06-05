package cdm.product.asset.validation.datarule;

import cdm.product.asset.VarianceReturnTerms;
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
@RosettaDataRule("VarianceReturnTermsStrikePriceMustExist")
@ImplementedBy(VarianceReturnTermsStrikePriceMustExist.Default.class)
public interface VarianceReturnTermsStrikePriceMustExist extends Validator<VarianceReturnTerms> {
	
	String NAME = "VarianceReturnTermsStrikePriceMustExist";
	String DEFINITION = "required choice volatilityStrikePrice, varianceStrikePrice";
	
	ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms);
	
	class Default implements VarianceReturnTermsStrikePriceMustExist {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			ComparisonResult result = executeDataRule(varianceReturnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VarianceReturnTerms varianceReturnTerms) {
			try {
				return choice(MapperS.of(varianceReturnTerms), Arrays.asList("volatilityStrikePrice", "varianceStrikePrice"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VarianceReturnTermsStrikePriceMustExist {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
		}
	}
}
