package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.OptionalEarlyTermination;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty")
@ImplementedBy(OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty.Default.class)
public interface OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty extends Validator<OptionalEarlyTermination> {
	
	String NAME = "OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty";
	String DEFINITION = "if exerciseNotice -> exerciseNoticeReceiver exists then exerciseNotice -> exerciseNoticeReceiver contains AncillaryRoleEnum -> ExerciseNoticeReceiverPartyOptionalEarlyTermination";
	
	ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination);
	
	class Default implements OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			ComparisonResult result = executeDataRule(optionalEarlyTermination);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionalEarlyTermination optionalEarlyTermination) {
			try {
				if (exists(MapperS.of(optionalEarlyTermination).<ExerciseNotice>mapC("getExerciseNotice", _optionalEarlyTermination -> _optionalEarlyTermination.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver())).getOrDefault(false)) {
					return contains(MapperS.of(optionalEarlyTermination).<ExerciseNotice>mapC("getExerciseNotice", _optionalEarlyTermination -> _optionalEarlyTermination.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_OPTIONAL_EARLY_TERMINATION));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
		}
	}
}
