package cdm.event.common.functions;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.settlement.Cashflow;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.FixedPricePayout;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.Payout.PayoutBuilder;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.Product.ProductBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(NewEquitySwapProduct.NewEquitySwapProductDefault.class)
public abstract class NewEquitySwapProduct implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected NewFloatingPayout newFloatingPayout;
	@Inject protected NewSingleNameEquityPerformancePayout newSingleNameEquityPerformancePayout;

	/**
	* @param security The underlying Equity asset for the swap.
	* @param masterConfirmation An (optional) pointer to the Master Confirmation Agreement, if any, that holds further inputs to the Equity Swap
	* @return product 
	*/
	public Product evaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
		// pre-conditions
		conditionValidator.validate(() -> areEqual(MapperS.of(security).<SecurityTypeEnum>map("getSecurityType", _security -> _security.getSecurityType()), MapperS.of(SecurityTypeEnum.EQUITY), CardinalityOperator.All),
			"Security must be equity (single name).");
		
		Product.ProductBuilder productBuilder = doEvaluate(security, masterConfirmation);
		
		final Product product;
		if (productBuilder == null) {
			product = null;
		} else {
			product = productBuilder.build();
			objectValidator.validate(Product.class, product);
		}
		
		// post-conditions
		conditionValidator.validate(() -> {
			if (notExists(MapperS.of(masterConfirmation)).getOrDefault(false)) {
				return notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout())).and(notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<Cashflow>mapC("getCashflow", _payout -> _payout.getCashflow()))).and(notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<CreditDefaultPayout>map("getCreditDefaultPayout", _payout -> _payout.getCreditDefaultPayout()))).and(notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<ForwardPayout>mapC("getForwardPayout", _payout -> _payout.getForwardPayout()))).and(notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<FixedPricePayout>mapC("getFixedPricePayout", _payout -> _payout.getFixedPricePayout()))).and(notExists(MapperS.of(payout(product.toBuilder(), security, masterConfirmation).build()).<OptionPayout>mapC("getOptionPayout", _payout -> _payout.getOptionPayout())));
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"Other payout types must be absent.");
		
		conditionValidator.validate(() -> onlyExists(Arrays.asList(MapperS.of(product).<ContractualProduct>map("getContractualProduct", _product -> _product.getContractualProduct()))),
			"Non-contractual product types must be absent.");
		
		return product;
	}

	protected abstract Product.ProductBuilder doEvaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation);

	protected abstract Payout.PayoutBuilder payout(Product.ProductBuilder product, Security security, EquitySwapMasterConfirmation2018 masterConfirmation);

	public static class NewEquitySwapProductDefault extends NewEquitySwapProduct {
		@Override
		protected Product.ProductBuilder doEvaluate(Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
			Product.ProductBuilder product = Product.builder();
			return assignOutput(product, security, masterConfirmation);
		}
		
		protected Product.ProductBuilder assignOutput(Product.ProductBuilder product, Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
			final PerformancePayout performancePayout = newSingleNameEquityPerformancePayout.evaluate(security, masterConfirmation);
			product
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.getOrCreatePayout()
				.addPerformancePayout((performancePayout == null ? Collections.<PerformancePayout>emptyList() : Collections.singletonList(performancePayout)));
			
			final List<InterestRatePayout> ifThenElseResult;
			if (exists(MapperS.of(masterConfirmation)).getOrDefault(false)) {
				final InterestRatePayout interestRatePayout = newFloatingPayout.evaluate(masterConfirmation);
				ifThenElseResult = interestRatePayout == null ? Collections.<InterestRatePayout>emptyList() : Collections.singletonList(interestRatePayout);
			} else {
				ifThenElseResult = Collections.<InterestRatePayout>emptyList();
			}
			product
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.getOrCreatePayout()
				.addInterestRatePayout(ifThenElseResult);
			
			return Optional.ofNullable(product)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected Payout.PayoutBuilder payout(Product.ProductBuilder product, Security security, EquitySwapMasterConfirmation2018 masterConfirmation) {
			return toBuilder(MapperS.of(product).<ContractualProduct>map("getContractualProduct", _product -> _product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).get());
		}
	}
}
