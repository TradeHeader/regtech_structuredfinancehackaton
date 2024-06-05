package cdm.product.asset.validation.datarule;

import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.ReturnTermsBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ReturnTermsBaseInitialLevelOrInitialLevelSource")
@ImplementedBy(ReturnTermsBaseInitialLevelOrInitialLevelSource.Default.class)
public interface ReturnTermsBaseInitialLevelOrInitialLevelSource extends Validator<ReturnTermsBase> {
	
	String NAME = "ReturnTermsBaseInitialLevelOrInitialLevelSource";
	String DEFINITION = "if initialLevel is absent then initialLevelSource exists and if initialLevelSource is absent then initialLevel exists";
	
	ValidationResult<ReturnTermsBase> validate(RosettaPath path, ReturnTermsBase returnTermsBase);
	
	class Default implements ReturnTermsBaseInitialLevelOrInitialLevelSource {
	
		@Override
		public ValidationResult<ReturnTermsBase> validate(RosettaPath path, ReturnTermsBase returnTermsBase) {
			ComparisonResult result = executeDataRule(returnTermsBase);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnTermsBase", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ReturnTermsBase", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ReturnTermsBase returnTermsBase) {
			try {
				if (notExists(MapperS.of(returnTermsBase).<BigDecimal>map("getInitialLevel", _returnTermsBase -> _returnTermsBase.getInitialLevel())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (notExists(MapperS.of(returnTermsBase).<DeterminationMethodEnum>map("getInitialLevelSource", _returnTermsBase -> _returnTermsBase.getInitialLevelSource())).getOrDefault(false)) {
						ifThenElseResult = exists(MapperS.of(returnTermsBase).<BigDecimal>map("getInitialLevel", _returnTermsBase -> _returnTermsBase.getInitialLevel()));
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return exists(MapperS.of(returnTermsBase).<DeterminationMethodEnum>map("getInitialLevelSource", _returnTermsBase -> _returnTermsBase.getInitialLevelSource())).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ReturnTermsBaseInitialLevelOrInitialLevelSource {
	
		@Override
		public ValidationResult<ReturnTermsBase> validate(RosettaPath path, ReturnTermsBase returnTermsBase) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnTermsBase", path, DEFINITION);
		}
	}
}
