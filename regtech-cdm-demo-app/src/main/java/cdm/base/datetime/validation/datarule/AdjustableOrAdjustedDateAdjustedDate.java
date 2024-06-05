package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.BusinessDayAdjustments;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaDate;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("AdjustableOrAdjustedDateAdjustedDate")
@ImplementedBy(AdjustableOrAdjustedDateAdjustedDate.Default.class)
public interface AdjustableOrAdjustedDateAdjustedDate extends Validator<AdjustableOrAdjustedDate> {
	
	String NAME = "AdjustableOrAdjustedDateAdjustedDate";
	String DEFINITION = "if adjustedDate is absent then unadjustedDate exists and dateAdjustments exists";
	
	ValidationResult<AdjustableOrAdjustedDate> validate(RosettaPath path, AdjustableOrAdjustedDate adjustableOrAdjustedDate);
	
	class Default implements AdjustableOrAdjustedDateAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableOrAdjustedDate> validate(RosettaPath path, AdjustableOrAdjustedDate adjustableOrAdjustedDate) {
			ComparisonResult result = executeDataRule(adjustableOrAdjustedDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrAdjustedDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdjustableOrAdjustedDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdjustableOrAdjustedDate adjustableOrAdjustedDate) {
			try {
				if (notExists(MapperS.of(adjustableOrAdjustedDate).<FieldWithMetaDate>map("getAdjustedDate", _adjustableOrAdjustedDate -> _adjustableOrAdjustedDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(adjustableOrAdjustedDate).<Date>map("getUnadjustedDate", _adjustableOrAdjustedDate -> _adjustableOrAdjustedDate.getUnadjustedDate())).and(exists(MapperS.of(adjustableOrAdjustedDate).<BusinessDayAdjustments>map("getDateAdjustments", _adjustableOrAdjustedDate -> _adjustableOrAdjustedDate.getDateAdjustments())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdjustableOrAdjustedDateAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableOrAdjustedDate> validate(RosettaPath path, AdjustableOrAdjustedDate adjustableOrAdjustedDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrAdjustedDate", path, DEFINITION);
		}
	}
}
