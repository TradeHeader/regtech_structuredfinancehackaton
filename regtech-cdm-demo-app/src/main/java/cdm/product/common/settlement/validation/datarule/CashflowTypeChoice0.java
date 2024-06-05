package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.CashflowType;
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
@RosettaDataRule("CashflowTypeChoice0")
@ImplementedBy(CashflowTypeChoice0.Default.class)
public interface CashflowTypeChoice0 extends Validator<CashflowType> {
	
	String NAME = "CashflowTypeChoice0";
	String DEFINITION = "required choice cashflowType, cashPrice";
	
	ValidationResult<CashflowType> validate(RosettaPath path, CashflowType cashflowType);
	
	class Default implements CashflowTypeChoice0 {
	
		@Override
		public ValidationResult<CashflowType> validate(RosettaPath path, CashflowType cashflowType) {
			ComparisonResult result = executeDataRule(cashflowType);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashflowType", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CashflowType", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CashflowType cashflowType) {
			try {
				return choice(MapperS.of(cashflowType), Arrays.asList("cashflowType", "cashPrice"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CashflowTypeChoice0 {
	
		@Override
		public ValidationResult<CashflowType> validate(RosettaPath path, CashflowType cashflowType) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashflowType", path, DEFINITION);
		}
	}
}
