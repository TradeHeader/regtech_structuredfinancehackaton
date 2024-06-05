package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.CommodityProductDefinition;
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
@RosettaDataRule("CommodityProductDefinitionCommodityProductDefinitionChoice")
@ImplementedBy(CommodityProductDefinitionCommodityProductDefinitionChoice.Default.class)
public interface CommodityProductDefinitionCommodityProductDefinitionChoice extends Validator<CommodityProductDefinition> {
	
	String NAME = "CommodityProductDefinitionCommodityProductDefinitionChoice";
	String DEFINITION = "optional choice exchangeId, priceSource";
	
	ValidationResult<CommodityProductDefinition> validate(RosettaPath path, CommodityProductDefinition commodityProductDefinition);
	
	class Default implements CommodityProductDefinitionCommodityProductDefinitionChoice {
	
		@Override
		public ValidationResult<CommodityProductDefinition> validate(RosettaPath path, CommodityProductDefinition commodityProductDefinition) {
			ComparisonResult result = executeDataRule(commodityProductDefinition);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityProductDefinition", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CommodityProductDefinition", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CommodityProductDefinition commodityProductDefinition) {
			try {
				return choice(MapperS.of(commodityProductDefinition), Arrays.asList("exchangeId", "priceSource"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityProductDefinitionCommodityProductDefinitionChoice {
	
		@Override
		public ValidationResult<CommodityProductDefinition> validate(RosettaPath path, CommodityProductDefinition commodityProductDefinition) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CommodityProductDefinition", path, DEFINITION);
		}
	}
}
