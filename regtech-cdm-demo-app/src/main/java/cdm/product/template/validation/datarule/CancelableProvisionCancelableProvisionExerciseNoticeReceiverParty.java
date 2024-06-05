package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.CancelableProvision;
import cdm.product.template.ExerciseNotice;
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
@RosettaDataRule("CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty")
@ImplementedBy(CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty.Default.class)
public interface CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty extends Validator<CancelableProvision> {
	
	String NAME = "CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty";
	String DEFINITION = "if exerciseNotice -> exerciseNoticeReceiver exists then exerciseNotice -> exerciseNoticeReceiver = AncillaryRoleEnum -> ExerciseNoticeReceiverPartyCancelableProvision";
	
	ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision);
	
	class Default implements CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision) {
			ComparisonResult result = executeDataRule(cancelableProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CancelableProvision cancelableProvision) {
			try {
				if (exists(MapperS.of(cancelableProvision).<ExerciseNotice>map("getExerciseNotice", _cancelableProvision -> _cancelableProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver())).getOrDefault(false)) {
					return areEqual(MapperS.of(cancelableProvision).<ExerciseNotice>map("getExerciseNotice", _cancelableProvision -> _cancelableProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_CANCELABLE_PROVISION), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CancelableProvisionCancelableProvisionExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION);
		}
	}
}
