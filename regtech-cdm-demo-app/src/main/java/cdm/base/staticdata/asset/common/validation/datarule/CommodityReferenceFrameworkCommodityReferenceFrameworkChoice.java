package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
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
@RosettaDataRule("CommodityReferenceFrameworkCommodityReferenceFrameworkChoice")
@ImplementedBy(CommodityReferenceFrameworkCommodityReferenceFrameworkChoice.Default.class)
public interface CommodityReferenceFrameworkCommodityReferenceFrameworkChoice extends Validator<CommodityReferenceFramework> {
	
	String NAME = "CommodityReferenceFrameworkCommodityReferenceFrameworkChoice";
	String DEFINITION = "optional choice capacityUnit, weatherUnit";
	
	ValidationResult<CommodityReferenceFramework> validate(RosettaPath path, CommodityReferenceFramework commodityReferenceFramework);
	
	class Default implements CommodityReferenceFrameworkCommodityReferenceFrameworkChoice {
	
		@Override
		public ValidationResult<CommodityReferenceFramework> validate(RosettaPath path, CommodityReferenceFramework commodityReferenceFramework) {
			ComparisonResult result = executeDataRule(commodityReferenceFramework);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityReferenceFramework", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CommodityReferenceFramework", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CommodityReferenceFramework commodityReferenceFramework) {
			try {
				return choice(MapperS.of(commodityReferenceFramework), Arrays.asList("capacityUnit", "weatherUnit"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityReferenceFrameworkCommodityReferenceFrameworkChoice {
	
		@Override
		public ValidationResult<CommodityReferenceFramework> validate(RosettaPath path, CommodityReferenceFramework commodityReferenceFramework) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityReferenceFramework", path, DEFINITION);
		}
	}
}
