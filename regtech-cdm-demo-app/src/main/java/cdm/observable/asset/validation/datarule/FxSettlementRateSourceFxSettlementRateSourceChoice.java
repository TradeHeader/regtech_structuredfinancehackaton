package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.FxSettlementRateSource;
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
@RosettaDataRule("FxSettlementRateSourceFxSettlementRateSourceChoice")
@ImplementedBy(FxSettlementRateSourceFxSettlementRateSourceChoice.Default.class)
public interface FxSettlementRateSourceFxSettlementRateSourceChoice extends Validator<FxSettlementRateSource> {
	
	String NAME = "FxSettlementRateSourceFxSettlementRateSourceChoice";
	String DEFINITION = "required choice settlementRateOption, nonstandardSettlementRate";
	
	ValidationResult<FxSettlementRateSource> validate(RosettaPath path, FxSettlementRateSource fxSettlementRateSource);
	
	class Default implements FxSettlementRateSourceFxSettlementRateSourceChoice {
	
		@Override
		public ValidationResult<FxSettlementRateSource> validate(RosettaPath path, FxSettlementRateSource fxSettlementRateSource) {
			ComparisonResult result = executeDataRule(fxSettlementRateSource);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxSettlementRateSource", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FxSettlementRateSource", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FxSettlementRateSource fxSettlementRateSource) {
			try {
				return choice(MapperS.of(fxSettlementRateSource), Arrays.asList("settlementRateOption", "nonstandardSettlementRate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FxSettlementRateSourceFxSettlementRateSourceChoice {
	
		@Override
		public ValidationResult<FxSettlementRateSource> validate(RosettaPath path, FxSettlementRateSource fxSettlementRateSource) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxSettlementRateSource", path, DEFINITION);
		}
	}
}
