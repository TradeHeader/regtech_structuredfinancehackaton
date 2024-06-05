package cdm.product.template.validation.datarule;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
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
@RosettaDataRule("TradableProductPerformancePayout_ExtraordinaryDividendsParty")
@ImplementedBy(TradableProductPerformancePayoutExtraordinaryDividendsParty.Default.class)
public interface TradableProductPerformancePayoutExtraordinaryDividendsParty extends Validator<TradableProduct> {
	
	String NAME = "TradableProductPerformancePayout_ExtraordinaryDividendsParty";
	String DEFINITION = "if product -> contractualProduct -> economicTerms -> payout -> performancePayout -> returnTerms -> dividendReturnTerms -> extraordinaryDividendsParty exists then ancillaryParty -> role contains AncillaryRoleEnum -> ExtraordinaryDividendsParty and if ancillaryParty -> role contains AncillaryRoleEnum -> ExtraordinaryDividendsParty then product -> contractualProduct -> economicTerms -> payout -> performancePayout -> returnTerms -> dividendReturnTerms -> extraordinaryDividendsParty exists";
	
	ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct);
	
	class Default implements TradableProductPerformancePayoutExtraordinaryDividendsParty {
	
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
				if (exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).<ReturnTerms>map("getReturnTerms", performancePayout -> performancePayout.getReturnTerms()).<DividendReturnTerms>map("getDividendReturnTerms", returnTerms -> returnTerms.getDividendReturnTerms()).<AncillaryRoleEnum>map("getExtraordinaryDividendsParty", dividendReturnTerms -> dividendReturnTerms.getExtraordinaryDividendsParty())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.EXTRAORDINARY_DIVIDENDS_PARTY)).getOrDefault(false)) {
						ifThenElseResult = exists(MapperS.of(tradableProduct).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).<ReturnTerms>map("getReturnTerms", performancePayout -> performancePayout.getReturnTerms()).<DividendReturnTerms>map("getDividendReturnTerms", returnTerms -> returnTerms.getDividendReturnTerms()).<AncillaryRoleEnum>map("getExtraordinaryDividendsParty", dividendReturnTerms -> dividendReturnTerms.getExtraordinaryDividendsParty()));
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return contains(MapperS.of(tradableProduct).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).<AncillaryRoleEnum>map("getRole", ancillaryParty -> ancillaryParty.getRole()), MapperS.of(AncillaryRoleEnum.EXTRAORDINARY_DIVIDENDS_PARTY)).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradableProductPerformancePayoutExtraordinaryDividendsParty {
	
		@Override
		public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct tradableProduct) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TradableProduct", path, DEFINITION);
		}
	}
}
