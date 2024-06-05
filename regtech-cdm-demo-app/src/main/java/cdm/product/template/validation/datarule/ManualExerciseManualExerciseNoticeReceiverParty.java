package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.ManualExercise;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("ManualExerciseManualExerciseNoticeReceiverParty")
@ImplementedBy(ManualExerciseManualExerciseNoticeReceiverParty.Default.class)
public interface ManualExerciseManualExerciseNoticeReceiverParty extends Validator<ManualExercise> {
	
	String NAME = "ManualExerciseManualExerciseNoticeReceiverParty";
	String DEFINITION = "if exerciseNotice -> exerciseNoticeReceiver exists then exerciseNotice -> exerciseNoticeReceiver = AncillaryRoleEnum -> ExerciseNoticeReceiverPartyManual";
	
	ValidationResult<ManualExercise> validate(RosettaPath path, ManualExercise manualExercise);
	
	class Default implements ManualExerciseManualExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<ManualExercise> validate(RosettaPath path, ManualExercise manualExercise) {
			ComparisonResult result = executeDataRule(manualExercise);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ManualExercise", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ManualExercise", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ManualExercise manualExercise) {
			try {
				if (exists(MapperS.of(manualExercise).<ExerciseNotice>map("getExerciseNotice", _manualExercise -> _manualExercise.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver())).getOrDefault(false)) {
					return areEqual(MapperS.of(manualExercise).<ExerciseNotice>map("getExerciseNotice", _manualExercise -> _manualExercise.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_MANUAL), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ManualExerciseManualExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<ManualExercise> validate(RosettaPath path, ManualExercise manualExercise) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ManualExercise", path, DEFINITION);
		}
	}
}
