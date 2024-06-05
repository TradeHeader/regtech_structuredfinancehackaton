package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.observable.asset.MultipleCreditNotations;
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
@RosettaDataRule("MultipleCreditNotationsReferenceAgency")
@ImplementedBy(MultipleCreditNotationsReferenceAgency.Default.class)
public interface MultipleCreditNotationsReferenceAgency extends Validator<MultipleCreditNotations> {
	
	String NAME = "MultipleCreditNotationsReferenceAgency";
	String DEFINITION = "if mismatchResolution = CreditNotationMismatchResolutionEnum -> ReferenceAgency then referenceAgency exists";
	
	ValidationResult<MultipleCreditNotations> validate(RosettaPath path, MultipleCreditNotations multipleCreditNotations);
	
	class Default implements MultipleCreditNotationsReferenceAgency {
	
		@Override
		public ValidationResult<MultipleCreditNotations> validate(RosettaPath path, MultipleCreditNotations multipleCreditNotations) {
			ComparisonResult result = executeDataRule(multipleCreditNotations);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleCreditNotations", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MultipleCreditNotations", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MultipleCreditNotations multipleCreditNotations) {
			try {
				if (areEqual(MapperS.of(multipleCreditNotations).<CreditNotationMismatchResolutionEnum>map("getMismatchResolution", _multipleCreditNotations -> _multipleCreditNotations.getMismatchResolution()), MapperS.of(CreditNotationMismatchResolutionEnum.REFERENCE_AGENCY), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(multipleCreditNotations).<CreditRatingAgencyEnum>map("getReferenceAgency", _multipleCreditNotations -> _multipleCreditNotations.getReferenceAgency()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MultipleCreditNotationsReferenceAgency {
	
		@Override
		public ValidationResult<MultipleCreditNotations> validate(RosettaPath path, MultipleCreditNotations multipleCreditNotations) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleCreditNotations", path, DEFINITION);
		}
	}
}
