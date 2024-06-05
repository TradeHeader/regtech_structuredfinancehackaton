package cdm.product.common.settlement.validation.datarule;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.product.common.settlement.DeliverableObligations;
import cdm.product.common.settlement.LoanParticipation;
import cdm.product.common.settlement.PCDeliverableObligationCharac;
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
@RosettaDataRule("DeliverableObligationsFpML_cd_34")
@ImplementedBy(DeliverableObligationsFpMLCd34.Default.class)
public interface DeliverableObligationsFpMLCd34 extends Validator<DeliverableObligations> {
	
	String NAME = "DeliverableObligationsFpML_cd_34";
	String DEFINITION = "if category exists and category = ObligationCategoryEnum -> ReferenceObligationsOnly then acceleratedOrMatured is absent and accruedInterest is absent and assignableLoan is absent and consentRequiredLoan is absent and directLoanParticipation is absent and excluded is absent and fullFaithAndCreditObLiability is absent and generalFundObligationLiability is absent and indirectLoanParticipation is absent and listed is absent and maximumMaturity is absent and notBearer is absent and notContingent is absent and notDomesticCurrency is absent and notDomesticIssuance is absent and notDomesticLaw is absent and notSovereignLender is absent and notSubordinated is absent and othReferenceEntityObligations is absent and revenueObligationLiability is absent and specifiedCurrency is absent and transferable is absent";
	
	ValidationResult<DeliverableObligations> validate(RosettaPath path, DeliverableObligations deliverableObligations);
	
	class Default implements DeliverableObligationsFpMLCd34 {
	
		@Override
		public ValidationResult<DeliverableObligations> validate(RosettaPath path, DeliverableObligations deliverableObligations) {
			ComparisonResult result = executeDataRule(deliverableObligations);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliverableObligations", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DeliverableObligations", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DeliverableObligations deliverableObligations) {
			try {
				if (exists(MapperS.of(deliverableObligations).<ObligationCategoryEnum>map("getCategory", _deliverableObligations -> _deliverableObligations.getCategory())).and(areEqual(MapperS.of(deliverableObligations).<ObligationCategoryEnum>map("getCategory", _deliverableObligations -> _deliverableObligations.getCategory()), MapperS.of(ObligationCategoryEnum.REFERENCE_OBLIGATIONS_ONLY), CardinalityOperator.All)).getOrDefault(false)) {
					return notExists(MapperS.of(deliverableObligations).<Boolean>map("getAcceleratedOrMatured", _deliverableObligations -> _deliverableObligations.getAcceleratedOrMatured())).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getAccruedInterest", _deliverableObligations -> _deliverableObligations.getAccruedInterest()))).and(notExists(MapperS.of(deliverableObligations).<PCDeliverableObligationCharac>map("getAssignableLoan", _deliverableObligations -> _deliverableObligations.getAssignableLoan()))).and(notExists(MapperS.of(deliverableObligations).<PCDeliverableObligationCharac>map("getConsentRequiredLoan", _deliverableObligations -> _deliverableObligations.getConsentRequiredLoan()))).and(notExists(MapperS.of(deliverableObligations).<LoanParticipation>map("getDirectLoanParticipation", _deliverableObligations -> _deliverableObligations.getDirectLoanParticipation()))).and(notExists(MapperS.of(deliverableObligations).<String>map("getExcluded", _deliverableObligations -> _deliverableObligations.getExcluded()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getFullFaithAndCreditObLiability", _deliverableObligations -> _deliverableObligations.getFullFaithAndCreditObLiability()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getGeneralFundObligationLiability", _deliverableObligations -> _deliverableObligations.getGeneralFundObligationLiability()))).and(notExists(MapperS.of(deliverableObligations).<LoanParticipation>map("getIndirectLoanParticipation", _deliverableObligations -> _deliverableObligations.getIndirectLoanParticipation()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getListed", _deliverableObligations -> _deliverableObligations.getListed()))).and(notExists(MapperS.of(deliverableObligations).<Period>map("getMaximumMaturity", _deliverableObligations -> _deliverableObligations.getMaximumMaturity()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotBearer", _deliverableObligations -> _deliverableObligations.getNotBearer()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotContingent", _deliverableObligations -> _deliverableObligations.getNotContingent()))).and(notExists(MapperS.of(deliverableObligations).<NotDomesticCurrency>map("getNotDomesticCurrency", _deliverableObligations -> _deliverableObligations.getNotDomesticCurrency()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotDomesticIssuance", _deliverableObligations -> _deliverableObligations.getNotDomesticIssuance()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotDomesticLaw", _deliverableObligations -> _deliverableObligations.getNotDomesticLaw()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotSovereignLender", _deliverableObligations -> _deliverableObligations.getNotSovereignLender()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getNotSubordinated", _deliverableObligations -> _deliverableObligations.getNotSubordinated()))).and(notExists(MapperS.of(deliverableObligations).<String>map("getOthReferenceEntityObligations", _deliverableObligations -> _deliverableObligations.getOthReferenceEntityObligations()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getRevenueObligationLiability", _deliverableObligations -> _deliverableObligations.getRevenueObligationLiability()))).and(notExists(MapperS.of(deliverableObligations).<SpecifiedCurrency>map("getSpecifiedCurrency", _deliverableObligations -> _deliverableObligations.getSpecifiedCurrency()))).and(notExists(MapperS.of(deliverableObligations).<Boolean>map("getTransferable", _deliverableObligations -> _deliverableObligations.getTransferable())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DeliverableObligationsFpMLCd34 {
	
		@Override
		public ValidationResult<DeliverableObligations> validate(RosettaPath path, DeliverableObligations deliverableObligations) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliverableObligations", path, DEFINITION);
		}
	}
}
