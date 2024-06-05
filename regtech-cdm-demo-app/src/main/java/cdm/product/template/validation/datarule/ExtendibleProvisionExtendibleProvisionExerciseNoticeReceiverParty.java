package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.ExtendibleProvision;
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
@RosettaDataRule("ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty")
@ImplementedBy(ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty.Default.class)
public interface ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty extends Validator<ExtendibleProvision> {
	
	String NAME = "ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty";
	String DEFINITION = "if exerciseNotice -> exerciseNoticeReceiver exists then exerciseNotice -> exerciseNoticeReceiver = AncillaryRoleEnum -> ExerciseNoticeReceiverPartyExtendibleProvision";
	
	ValidationResult<ExtendibleProvision> validate(RosettaPath path, ExtendibleProvision extendibleProvision);
	
	class Default implements ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<ExtendibleProvision> validate(RosettaPath path, ExtendibleProvision extendibleProvision) {
			ComparisonResult result = executeDataRule(extendibleProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtendibleProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ExtendibleProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ExtendibleProvision extendibleProvision) {
			try {
				if (exists(MapperS.of(extendibleProvision).<ExerciseNotice>map("getExerciseNotice", _extendibleProvision -> _extendibleProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver())).getOrDefault(false)) {
					return areEqual(MapperS.of(extendibleProvision).<ExerciseNotice>map("getExerciseNotice", _extendibleProvision -> _extendibleProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_EXTENDIBLE_PROVISION), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ExtendibleProvisionExtendibleProvisionExerciseNoticeReceiverParty {
	
		@Override
		public ValidationResult<ExtendibleProvision> validate(RosettaPath path, ExtendibleProvision extendibleProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtendibleProvision", path, DEFINITION);
		}
	}
}
