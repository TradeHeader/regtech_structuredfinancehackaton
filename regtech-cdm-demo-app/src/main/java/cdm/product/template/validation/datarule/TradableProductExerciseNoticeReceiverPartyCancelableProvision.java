package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.template.CancelableProvision;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.Product;
import cdm.product.template.TerminationProvision;
import cdm.product.template.TradableProduct;
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
@RosettaDataRule("TradableProductExerciseNoticeReceiverPartyCancelableProvision")
@ImplementedBy(TradableProductExerciseNoticeReceiverPartyCancelableProvision.Default.class)
public interface TradableProductExerciseNoticeReceiverPartyCancelableProvision extends Validator<TradableProduct> {
	
	String NAME = "TradableProductExerciseNoticeReceiverPartyCancelableProvision";
	String DEFINITION = "if product -> contractualProduct -> economicTerms -> terminationProvision -> cancelableProvision -> exerciseNotice -> exerciseNoticeReceiver exists then ancillaryParty -> role contains AncillaryRoleEnum -> ExerciseNoticeReceiverPartyCancelableProvision and if ancillaryParty -> role contains AncillaryRoleEnum -> ExerciseNoticeReceiverPartyCancelableProvision then product -> contractualProduct -> economicTerms -> terminationProvision -> cancelableProvision -> exerciseNotice -> exerciseNoticeReceiver exists";
	
	ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct);
	
	class Default implements TradableProductExerciseNoticeReceiverPartyCancelableProvision {
	
		@Override
		public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct) {
			ComparisonResult result = executeDataRule(tradableProduct);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TradableProduct tradableProduct) {
			try {
				if (exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<TerminationProvision>map("getTerminationProvision", economicTerms -> economicTerms.getTerminationProvision()).<CancelableProvision>map("getCancelableProvision", terminationProvision -> terminationProvision.getCancelableProvision()).<ExerciseNotice>map("getExerciseNotice", cancelableProvision -> cancelableProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_CANCELABLE_PROVISION)).getOrDefault(false)) {
						ifThenElseResult = exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<TerminationProvision>map("getTerminationProvision", economicTerms -> economicTerms.getTerminationProvision()).<CancelableProvision>map("getCancelableProvision", terminationProvision -> terminationProvision.getCancelableProvision()).<ExerciseNotice>map("getExerciseNotice", cancelableProvision -> cancelableProvision.getExerciseNotice()).<AncillaryRoleEnum>map("getExerciseNoticeReceiver", exerciseNotice -> exerciseNotice.getExerciseNoticeReceiver()));
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.EXERCISE_NOTICE_RECEIVER_PARTY_CANCELABLE_PROVISION)).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradableProductExerciseNoticeReceiverPartyCancelableProvision {
	
		@Override
		public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION);
		}
	}
}
