package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.AdjustableDates;
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
@RosettaDataRule("AdjustableDatesAdjustedDate")
@ImplementedBy(AdjustableDatesAdjustedDate.Default.class)
public interface AdjustableDatesAdjustedDate extends Validator<AdjustableDates> {
	
	String NAME = "AdjustableDatesAdjustedDate";
	String DEFINITION = "if adjustedDate is absent then unadjustedDate exists and dateAdjustments exists";
	
	ValidationResult<AdjustableDates> validate(RosettaPath path, AdjustableDates adjustableDates);
	
	class Default implements AdjustableDatesAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableDates> validate(RosettaPath path, AdjustableDates adjustableDates) {
			ComparisonResult result = executeDataRule(adjustableDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdjustableDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdjustableDates adjustableDates) {
			try {
				if (notExists(MapperS.of(adjustableDates).<FieldWithMetaDate>mapC("getAdjustedDate", _adjustableDates -> _adjustableDates.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(adjustableDates).<Date>mapC("getUnadjustedDate", _adjustableDates -> _adjustableDates.getUnadjustedDate())).and(exists(MapperS.of(adjustableDates).<BusinessDayAdjustments>map("getDateAdjustments", _adjustableDates -> _adjustableDates.getDateAdjustments())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdjustableDatesAdjustedDate {
	
		@Override
		public ValidationResult<AdjustableDates> validate(RosettaPath path, AdjustableDates adjustableDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableDates", path, DEFINITION);
		}
	}
}
