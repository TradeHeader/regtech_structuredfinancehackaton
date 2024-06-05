package cdm.product.asset.validation.datarule;

import cdm.product.asset.FloatingRateDefinition;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("FloatingRateDefinitionFloatingRateMultiplier")
@ImplementedBy(FloatingRateDefinitionFloatingRateMultiplier.Default.class)
public interface FloatingRateDefinitionFloatingRateMultiplier extends Validator<FloatingRateDefinition> {
	
	String NAME = "FloatingRateDefinitionFloatingRateMultiplier";
	String DEFINITION = "if floatingRateMultiplier exists then floatingRateMultiplier <> 1";
	
	ValidationResult<FloatingRateDefinition> validate(RosettaPath path, FloatingRateDefinition floatingRateDefinition);
	
	class Default implements FloatingRateDefinitionFloatingRateMultiplier {
	
		@Override
		public ValidationResult<FloatingRateDefinition> validate(RosettaPath path, FloatingRateDefinition floatingRateDefinition) {
			ComparisonResult result = executeDataRule(floatingRateDefinition);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FloatingRateDefinition", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FloatingRateDefinition", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FloatingRateDefinition floatingRateDefinition) {
			try {
				if (exists(MapperS.of(floatingRateDefinition).<BigDecimal>map("getFloatingRateMultiplier", _floatingRateDefinition -> _floatingRateDefinition.getFloatingRateMultiplier())).getOrDefault(false)) {
					return notEqual(MapperS.of(floatingRateDefinition).<BigDecimal>map("getFloatingRateMultiplier", _floatingRateDefinition -> _floatingRateDefinition.getFloatingRateMultiplier()), MapperS.of(BigDecimal.valueOf(1)), CardinalityOperator.Any);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FloatingRateDefinitionFloatingRateMultiplier {
	
		@Override
		public ValidationResult<FloatingRateDefinition> validate(RosettaPath path, FloatingRateDefinition floatingRateDefinition) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FloatingRateDefinition", path, DEFINITION);
		}
	}
}
