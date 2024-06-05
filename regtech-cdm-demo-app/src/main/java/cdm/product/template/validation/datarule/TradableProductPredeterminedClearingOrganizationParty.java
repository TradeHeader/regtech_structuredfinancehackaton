package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.Product;
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
@RosettaDataRule("TradableProductPredeterminedClearingOrganizationParty")
@ImplementedBy(TradableProductPredeterminedClearingOrganizationParty.Default.class)
public interface TradableProductPredeterminedClearingOrganizationParty extends Validator<TradableProduct> {
	
	String NAME = "TradableProductPredeterminedClearingOrganizationParty";
	String DEFINITION = "if ancillaryParty -> role contains AncillaryRoleEnum -> PredeterminedClearingOrganizationParty then product -> contractualProduct -> economicTerms -> payout -> forwardPayout -> settlementTerms -> physicalSettlementTerms -> predeterminedClearingOrganizationParty exists or product -> contractualProduct -> economicTerms -> payout -> optionPayout -> settlementTerms -> physicalSettlementTerms -> predeterminedClearingOrganizationParty exists";
	
	ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct);
	
	class Default implements TradableProductPredeterminedClearingOrganizationParty {
	
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
				if (contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.PREDETERMINED_CLEARING_ORGANIZATION_PARTY)).getOrDefault(false)) {
					return exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<PhysicalSettlementTerms>map("getPhysicalSettlementTerms", settlementTerms -> settlementTerms.getPhysicalSettlementTerms()).<AncillaryRoleEnum>map("getPredeterminedClearingOrganizationParty", physicalSettlementTerms -> physicalSettlementTerms.getPredeterminedClearingOrganizationParty())).or(exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<PhysicalSettlementTerms>map("getPhysicalSettlementTerms", settlementTerms -> settlementTerms.getPhysicalSettlementTerms()).<AncillaryRoleEnum>map("getPredeterminedClearingOrganizationParty", physicalSettlementTerms -> physicalSettlementTerms.getPredeterminedClearingOrganizationParty())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradableProductPredeterminedClearingOrganizationParty {
	
		@Override
		public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION);
		}
	}
}
