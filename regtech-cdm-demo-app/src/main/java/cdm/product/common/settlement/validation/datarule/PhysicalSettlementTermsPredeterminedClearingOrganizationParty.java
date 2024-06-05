package cdm.product.common.settlement.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.common.settlement.PhysicalSettlementTerms;
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
@RosettaDataRule("PhysicalSettlementTermsPredeterminedClearingOrganizationParty")
@ImplementedBy(PhysicalSettlementTermsPredeterminedClearingOrganizationParty.Default.class)
public interface PhysicalSettlementTermsPredeterminedClearingOrganizationParty extends Validator<PhysicalSettlementTerms> {
	
	String NAME = "PhysicalSettlementTermsPredeterminedClearingOrganizationParty";
	String DEFINITION = "if predeterminedClearingOrganizationParty exists then predeterminedClearingOrganizationParty = AncillaryRoleEnum -> PredeterminedClearingOrganizationParty";
	
	ValidationResult<PhysicalSettlementTerms> validate(RosettaPath path, PhysicalSettlementTerms physicalSettlementTerms);
	
	class Default implements PhysicalSettlementTermsPredeterminedClearingOrganizationParty {
	
		@Override
		public ValidationResult<PhysicalSettlementTerms> validate(RosettaPath path, PhysicalSettlementTerms physicalSettlementTerms) {
			ComparisonResult result = executeDataRule(physicalSettlementTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PhysicalSettlementTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PhysicalSettlementTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PhysicalSettlementTerms physicalSettlementTerms) {
			try {
				if (exists(MapperS.of(physicalSettlementTerms).<AncillaryRoleEnum>map("getPredeterminedClearingOrganizationParty", _physicalSettlementTerms -> _physicalSettlementTerms.getPredeterminedClearingOrganizationParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(physicalSettlementTerms).<AncillaryRoleEnum>map("getPredeterminedClearingOrganizationParty", _physicalSettlementTerms -> _physicalSettlementTerms.getPredeterminedClearingOrganizationParty()), MapperS.of(AncillaryRoleEnum.PREDETERMINED_CLEARING_ORGANIZATION_PARTY), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PhysicalSettlementTermsPredeterminedClearingOrganizationParty {
	
		@Override
		public ValidationResult<PhysicalSettlementTerms> validate(RosettaPath path, PhysicalSettlementTerms physicalSettlementTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PhysicalSettlementTerms", path, DEFINITION);
		}
	}
}
