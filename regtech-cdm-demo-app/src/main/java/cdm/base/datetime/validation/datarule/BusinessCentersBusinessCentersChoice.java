package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.BusinessCenters;
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
@RosettaDataRule("BusinessCentersBusinessCentersChoice")
@ImplementedBy(BusinessCentersBusinessCentersChoice.Default.class)
public interface BusinessCentersBusinessCentersChoice extends Validator<BusinessCenters> {
	
	String NAME = "BusinessCentersBusinessCentersChoice";
	String DEFINITION = "required choice businessCenter, businessCentersReference, commodityBusinessCalendar";
	
	ValidationResult<BusinessCenters> validate(RosettaPath path, BusinessCenters businessCenters);
	
	class Default implements BusinessCentersBusinessCentersChoice {
	
		@Override
		public ValidationResult<BusinessCenters> validate(RosettaPath path, BusinessCenters businessCenters) {
			ComparisonResult result = executeDataRule(businessCenters);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BusinessCenters", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "BusinessCenters", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(BusinessCenters businessCenters) {
			try {
				return choice(MapperS.of(businessCenters), Arrays.asList("businessCenter", "businessCentersReference", "commodityBusinessCalendar"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements BusinessCentersBusinessCentersChoice {
	
		@Override
		public ValidationResult<BusinessCenters> validate(RosettaPath path, BusinessCenters businessCenters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BusinessCenters", path, DEFINITION);
		}
	}
}
