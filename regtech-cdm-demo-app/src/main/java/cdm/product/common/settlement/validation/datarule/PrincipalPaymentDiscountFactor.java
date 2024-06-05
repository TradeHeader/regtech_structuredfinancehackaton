package cdm.product.common.settlement.validation.datarule;

import cdm.observable.asset.Money;
import cdm.product.common.settlement.PrincipalPayment;
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
@RosettaDataRule("PrincipalPaymentDiscountFactor")
@ImplementedBy(PrincipalPaymentDiscountFactor.Default.class)
public interface PrincipalPaymentDiscountFactor extends Validator<PrincipalPayment> {
	
	String NAME = "PrincipalPaymentDiscountFactor";
	String DEFINITION = "if presentValuePrincipalAmount exists then discountFactor exists";
	
	ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment);
	
	class Default implements PrincipalPaymentDiscountFactor {
	
		@Override
		public ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment) {
			ComparisonResult result = executeDataRule(principalPayment);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PrincipalPayment principalPayment) {
			try {
				if (exists(MapperS.of(principalPayment).<Money>map("getPresentValuePrincipalAmount", _principalPayment -> _principalPayment.getPresentValuePrincipalAmount())).getOrDefault(false)) {
					return exists(MapperS.of(principalPayment).<BigDecimal>map("getDiscountFactor", _principalPayment -> _principalPayment.getDiscountFactor()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PrincipalPaymentDiscountFactor {
	
		@Override
		public ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION);
		}
	}
}
