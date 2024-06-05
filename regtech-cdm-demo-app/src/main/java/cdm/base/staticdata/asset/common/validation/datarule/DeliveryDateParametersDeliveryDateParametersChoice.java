package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.DeliveryDateParameters;
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
@RosettaDataRule("DeliveryDateParametersDeliveryDateParametersChoice")
@ImplementedBy(DeliveryDateParametersDeliveryDateParametersChoice.Default.class)
public interface DeliveryDateParametersDeliveryDateParametersChoice extends Validator<DeliveryDateParameters> {
	
	String NAME = "DeliveryDateParametersDeliveryDateParametersChoice";
	String DEFINITION = "optional choice deliveryNearby, deliveryDate";
	
	ValidationResult<DeliveryDateParameters> validate(RosettaPath path, DeliveryDateParameters deliveryDateParameters);
	
	class Default implements DeliveryDateParametersDeliveryDateParametersChoice {
	
		@Override
		public ValidationResult<DeliveryDateParameters> validate(RosettaPath path, DeliveryDateParameters deliveryDateParameters) {
			ComparisonResult result = executeDataRule(deliveryDateParameters);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliveryDateParameters", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DeliveryDateParameters", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DeliveryDateParameters deliveryDateParameters) {
			try {
				return choice(MapperS.of(deliveryDateParameters), Arrays.asList("deliveryNearby", "deliveryDate"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DeliveryDateParametersDeliveryDateParametersChoice {
	
		@Override
		public ValidationResult<DeliveryDateParameters> validate(RosettaPath path, DeliveryDateParameters deliveryDateParameters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliveryDateParameters", path, DEFINITION);
		}
	}
}
