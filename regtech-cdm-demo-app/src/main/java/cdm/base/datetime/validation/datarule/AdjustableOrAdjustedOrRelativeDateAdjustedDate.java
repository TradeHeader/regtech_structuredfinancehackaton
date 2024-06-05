package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.RelativeDateOffset;
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
@RosettaDataRule("AdjustableOrAdjustedOrRelativeDateAdjustedDate")
@ImplementedBy(AdjustableOrAdjustedOrRelativeDateAdjustedDate.Default.class)
public interface AdjustableOrAdjustedOrRelativeDateAdjustedDate extends Validator<AdjustableOrAdjustedOrRelativeDate> {
	
	String NAME = "AdjustableOrAdjustedOrRelativeDateAdjustedDate";
	String DEFINITION = "adjustedDate exists or relativeDate exists or unadjustedDate exists or (unadjustedDate exists and dateAdjustments exists and adjustedDate is absent)";
	
	ValidationResult<AdjustableOrAdjustedOrRelativeDate> validate(RosettaPath path, AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate);
	
	class Default implements AdjustableOrAdjustedOrRelativeDateAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableOrAdjustedOrRelativeDate> validate(RosettaPath path, AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			ComparisonResult result = executeDataRule(adjustableOrAdjustedOrRelativeDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrAdjustedOrRelativeDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdjustableOrAdjustedOrRelativeDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			try {
				return exists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<FieldWithMetaDate>map("getAdjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue())).or(exists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<RelativeDateOffset>map("getRelativeDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getRelativeDate()))).or(exists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<Date>map("getUnadjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getUnadjustedDate()))).or(exists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<Date>map("getUnadjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getUnadjustedDate())).and(exists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<BusinessDayAdjustments>map("getDateAdjustments", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getDateAdjustments()))).and(notExists(MapperS.of(adjustableOrAdjustedOrRelativeDate).<FieldWithMetaDate>map("getAdjustedDate", _adjustableOrAdjustedOrRelativeDate -> _adjustableOrAdjustedOrRelativeDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()))));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdjustableOrAdjustedOrRelativeDateAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableOrAdjustedOrRelativeDate> validate(RosettaPath path, AdjustableOrAdjustedOrRelativeDate adjustableOrAdjustedOrRelativeDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrAdjustedOrRelativeDate", path, DEFINITION);
		}
	}
}
