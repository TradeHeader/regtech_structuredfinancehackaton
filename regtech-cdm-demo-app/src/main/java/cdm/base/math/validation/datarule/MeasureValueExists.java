package cdm.base.math.validation.datarule;

import cdm.base.math.Measure;
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
@RosettaDataRule("MeasureValueExists")
@ImplementedBy(MeasureValueExists.Default.class)
public interface MeasureValueExists extends Validator<Measure> {
	
	String NAME = "MeasureValueExists";
	String DEFINITION = "value exists";
	
	ValidationResult<Measure> validate(RosettaPath path, Measure measure);
	
	class Default implements MeasureValueExists {
	
		@Override
		public ValidationResult<Measure> validate(RosettaPath path, Measure measure) {
			ComparisonResult result = executeDataRule(measure);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Measure", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Measure", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Measure measure) {
			try {
				return exists(MapperS.of(measure).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MeasureValueExists {
	
		@Override
		public ValidationResult<Measure> validate(RosettaPath path, Measure measure) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Measure", path, DEFINITION);
		}
	}
}
