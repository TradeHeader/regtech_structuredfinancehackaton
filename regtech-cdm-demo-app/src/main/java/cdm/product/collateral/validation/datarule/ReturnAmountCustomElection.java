package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.ReturnAmount;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("ReturnAmountCustomElection")
@ImplementedBy(ReturnAmountCustomElection.Default.class)
public interface ReturnAmountCustomElection extends Validator<ReturnAmount> {
	
	String NAME = "ReturnAmountCustomElection";
	String DEFINITION = "if customElection exists then includesDefaultLanguage = False";
	
	ValidationResult<ReturnAmount> validate(RosettaPath path, ReturnAmount returnAmount);
	
	class Default implements ReturnAmountCustomElection {
	
		@Override
		public ValidationResult<ReturnAmount> validate(RosettaPath path, ReturnAmount returnAmount) {
			ComparisonResult result = executeDataRule(returnAmount);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnAmount", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ReturnAmount", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ReturnAmount returnAmount) {
			try {
				if (exists(MapperS.of(returnAmount).<String>map("getCustomElection", _returnAmount -> _returnAmount.getCustomElection())).getOrDefault(false)) {
					return areEqual(MapperS.of(returnAmount).<Boolean>map("getIncludesDefaultLanguage", _returnAmount -> _returnAmount.getIncludesDefaultLanguage()), MapperS.of(false), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ReturnAmountCustomElection {
	
		@Override
		public ValidationResult<ReturnAmount> validate(RosettaPath path, ReturnAmount returnAmount) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnAmount", path, DEFINITION);
		}
	}
}
