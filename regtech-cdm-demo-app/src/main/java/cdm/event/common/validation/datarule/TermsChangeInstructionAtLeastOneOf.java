package cdm.event.common.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.event.common.TermsChangeInstruction;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
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
@RosettaDataRule("TermsChangeInstructionAtLeastOneOf")
@ImplementedBy(TermsChangeInstructionAtLeastOneOf.Default.class)
public interface TermsChangeInstructionAtLeastOneOf extends Validator<TermsChangeInstruction> {
	
	String NAME = "TermsChangeInstructionAtLeastOneOf";
	String DEFINITION = "(product exists or ancillaryParty exists or adjustment exists)";
	
	ValidationResult<TermsChangeInstruction> validate(RosettaPath path, TermsChangeInstruction termsChangeInstruction);
	
	class Default implements TermsChangeInstructionAtLeastOneOf {
	
		@Override
		public ValidationResult<TermsChangeInstruction> validate(RosettaPath path, TermsChangeInstruction termsChangeInstruction) {
			ComparisonResult result = executeDataRule(termsChangeInstruction);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TermsChangeInstruction", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TermsChangeInstruction", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TermsChangeInstruction termsChangeInstruction) {
			try {
				return exists(MapperS.of(termsChangeInstruction).<Product>map("getProduct", _termsChangeInstruction -> _termsChangeInstruction.getProduct())).or(exists(MapperS.of(termsChangeInstruction).<AncillaryParty>mapC("getAncillaryParty", _termsChangeInstruction -> _termsChangeInstruction.getAncillaryParty()))).or(exists(MapperS.of(termsChangeInstruction).<NotionalAdjustmentEnum>map("getAdjustment", _termsChangeInstruction -> _termsChangeInstruction.getAdjustment())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TermsChangeInstructionAtLeastOneOf {
	
		@Override
		public ValidationResult<TermsChangeInstruction> validate(RosettaPath path, TermsChangeInstruction termsChangeInstruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TermsChangeInstruction", path, DEFINITION);
		}
	}
}
