package cdm.product.asset.validation.datarule;

import cdm.product.asset.CreditIndexReferenceInformation;
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
@RosettaDataRule("CreditIndexReferenceInformationIndexSeries")
@ImplementedBy(CreditIndexReferenceInformationIndexSeries.Default.class)
public interface CreditIndexReferenceInformationIndexSeries extends Validator<CreditIndexReferenceInformation> {
	
	String NAME = "CreditIndexReferenceInformationIndexSeries";
	String DEFINITION = "if indexSeries exists then indexSeries >= 0";
	
	ValidationResult<CreditIndexReferenceInformation> validate(RosettaPath path, CreditIndexReferenceInformation creditIndexReferenceInformation);
	
	class Default implements CreditIndexReferenceInformationIndexSeries {
	
		@Override
		public ValidationResult<CreditIndexReferenceInformation> validate(RosettaPath path, CreditIndexReferenceInformation creditIndexReferenceInformation) {
			ComparisonResult result = executeDataRule(creditIndexReferenceInformation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditIndexReferenceInformation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CreditIndexReferenceInformation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CreditIndexReferenceInformation creditIndexReferenceInformation) {
			try {
				if (exists(MapperS.of(creditIndexReferenceInformation).<Integer>map("getIndexSeries", _creditIndexReferenceInformation -> _creditIndexReferenceInformation.getIndexSeries())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(creditIndexReferenceInformation).<Integer>map("getIndexSeries", _creditIndexReferenceInformation -> _creditIndexReferenceInformation.getIndexSeries()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CreditIndexReferenceInformationIndexSeries {
	
		@Override
		public ValidationResult<CreditIndexReferenceInformation> validate(RosettaPath path, CreditIndexReferenceInformation creditIndexReferenceInformation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditIndexReferenceInformation", path, DEFINITION);
		}
	}
}
