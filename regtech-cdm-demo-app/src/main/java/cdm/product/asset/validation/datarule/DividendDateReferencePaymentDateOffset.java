package cdm.product.asset.validation.datarule;

import cdm.base.datetime.Offset;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendDateReferenceEnum;
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
@RosettaDataRule("DividendDateReferencePaymentDateOffset")
@ImplementedBy(DividendDateReferencePaymentDateOffset.Default.class)
public interface DividendDateReferencePaymentDateOffset extends Validator<DividendDateReference> {
	
	String NAME = "DividendDateReferencePaymentDateOffset";
	String DEFINITION = "if paymentDateOffset exists then dateReference = DividendDateReferenceEnum -> SharePayment";
	
	ValidationResult<DividendDateReference> validate(RosettaPath path, DividendDateReference dividendDateReference);
	
	class Default implements DividendDateReferencePaymentDateOffset {
	
		@Override
		public ValidationResult<DividendDateReference> validate(RosettaPath path, DividendDateReference dividendDateReference) {
			ComparisonResult result = executeDataRule(dividendDateReference);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendDateReference", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DividendDateReference", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DividendDateReference dividendDateReference) {
			try {
				if (exists(MapperS.of(dividendDateReference).<Offset>map("getPaymentDateOffset", _dividendDateReference -> _dividendDateReference.getPaymentDateOffset())).getOrDefault(false)) {
					return areEqual(MapperS.of(dividendDateReference).<DividendDateReferenceEnum>map("getDateReference", _dividendDateReference -> _dividendDateReference.getDateReference()), MapperS.of(DividendDateReferenceEnum.SHARE_PAYMENT), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendDateReferencePaymentDateOffset {
	
		@Override
		public ValidationResult<DividendDateReference> validate(RosettaPath path, DividendDateReference dividendDateReference) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendDateReference", path, DEFINITION);
		}
	}
}
