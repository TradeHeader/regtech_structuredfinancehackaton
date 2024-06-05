package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.EconomicTerms;
import cdm.product.template.MandatoryEarlyTermination;
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
@RosettaDataRule("TradableProductCalculationAgentMandatoryEarlyTermination")
@ImplementedBy(TradableProductCalculationAgentMandatoryEarlyTermination.Default.class)
public interface TradableProductCalculationAgentMandatoryEarlyTermination extends Validator<TradableProduct> {
	
	String NAME = "TradableProductCalculationAgentMandatoryEarlyTermination";
	String DEFINITION = "if product -> contractualProduct -> economicTerms -> terminationProvision -> earlyTerminationProvision -> mandatoryEarlyTermination -> calculationAgent -> calculationAgentParty exists then ancillaryParty -> role contains AncillaryRoleEnum -> CalculationAgentMandatoryEarlyTermination and if ancillaryParty -> role contains AncillaryRoleEnum -> CalculationAgentMandatoryEarlyTermination then product -> contractualProduct -> economicTerms -> terminationProvision -> earlyTerminationProvision -> mandatoryEarlyTermination -> calculationAgent -> calculationAgentParty exists";
	
	ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct);
	
	class Default implements TradableProductCalculationAgentMandatoryEarlyTermination {
	
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
				if (exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<TerminationProvision>map("getTerminationProvision", economicTerms -> economicTerms.getTerminationProvision()).<EarlyTerminationProvision>map("getEarlyTerminationProvision", terminationProvision -> terminationProvision.getEarlyTerminationProvision()).<MandatoryEarlyTermination>map("getMandatoryEarlyTermination", earlyTerminationProvision -> earlyTerminationProvision.getMandatoryEarlyTermination()).<CalculationAgent>map("getCalculationAgent", mandatoryEarlyTermination -> mandatoryEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.CALCULATION_AGENT_MANDATORY_EARLY_TERMINATION)).getOrDefault(false)) {
						ifThenElseResult = exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<TerminationProvision>map("getTerminationProvision", economicTerms -> economicTerms.getTerminationProvision()).<EarlyTerminationProvision>map("getEarlyTerminationProvision", terminationProvision -> terminationProvision.getEarlyTerminationProvision()).<MandatoryEarlyTermination>map("getMandatoryEarlyTermination", earlyTerminationProvision -> earlyTerminationProvision.getMandatoryEarlyTermination()).<CalculationAgent>map("getCalculationAgent", mandatoryEarlyTermination -> mandatoryEarlyTermination.getCalculationAgent()).<AncillaryRoleEnum>map("getCalculationAgentParty", calculationAgent -> calculationAgent.getCalculationAgentParty()));
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.CALCULATION_AGENT_MANDATORY_EARLY_TERMINATION)).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradableProductCalculationAgentMandatoryEarlyTermination {
	
		@Override
		public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION);
		}
	}
}
