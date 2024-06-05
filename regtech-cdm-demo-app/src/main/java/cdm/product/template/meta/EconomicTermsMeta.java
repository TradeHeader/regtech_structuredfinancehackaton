package cdm.product.template.meta;

import cdm.product.qualification.functions.Qualify_Commodity_Forward;
import cdm.product.qualification.functions.Qualify_Commodity_Option;
import cdm.product.qualification.functions.Qualify_Commodity_Swap_Basis;
import cdm.product.qualification.functions.Qualify_Commodity_Swap_FixedFloat;
import cdm.product.qualification.functions.Qualify_Commodity_Swaption;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwap_Basket;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwap_Index;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwap_IndexTranche;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwap_Loan;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwap_SingleName;
import cdm.product.qualification.functions.Qualify_CreditDefaultSwaption;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnCorrelation_Basket;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnDividend_Basket;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnDividend_Index;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnDividend_SingleName;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVariance_Basket;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVariance_Index;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVariance_SingleName;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVolatility_Basket;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVolatility_Index;
import cdm.product.qualification.functions.Qualify_EquityOption_ParameterReturnVolatility_SingleName;
import cdm.product.qualification.functions.Qualify_EquityOption_PriceReturnBasicPerformance_Basket;
import cdm.product.qualification.functions.Qualify_EquityOption_PriceReturnBasicPerformance_Index;
import cdm.product.qualification.functions.Qualify_EquityOption_PriceReturnBasicPerformance_SingleName;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnCorrelation_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnDispersion;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnDividend_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnDividend_Index;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnDividend_SingleName;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVariance_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVariance_Index;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVariance_SingleName;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVolatility_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVolatility_Index;
import cdm.product.qualification.functions.Qualify_EquitySwap_ParameterReturnVolatility_SingleName;
import cdm.product.qualification.functions.Qualify_EquitySwap_PriceReturnBasicPerformance_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_PriceReturnBasicPerformance_Index;
import cdm.product.qualification.functions.Qualify_EquitySwap_PriceReturnBasicPerformance_SingleName;
import cdm.product.qualification.functions.Qualify_EquitySwap_TotalReturnBasicPerformance_Basket;
import cdm.product.qualification.functions.Qualify_EquitySwap_TotalReturnBasicPerformance_Index;
import cdm.product.qualification.functions.Qualify_EquitySwap_TotalReturnBasicPerformance_SingleName;
import cdm.product.qualification.functions.Qualify_ForeignExchange_NDF;
import cdm.product.qualification.functions.Qualify_ForeignExchange_NDS;
import cdm.product.qualification.functions.Qualify_ForeignExchange_ParameterReturnCorrelation;
import cdm.product.qualification.functions.Qualify_ForeignExchange_ParameterReturnVariance;
import cdm.product.qualification.functions.Qualify_ForeignExchange_ParameterReturnVolatility;
import cdm.product.qualification.functions.Qualify_ForeignExchange_Spot_Forward;
import cdm.product.qualification.functions.Qualify_ForeignExchange_Swap;
import cdm.product.qualification.functions.Qualify_ForeignExchange_VanillaOption;
import cdm.product.qualification.functions.Qualify_InterestRate_CapFloor;
import cdm.product.qualification.functions.Qualify_InterestRate_CrossCurrency_Basis;
import cdm.product.qualification.functions.Qualify_InterestRate_CrossCurrency_FixedFixed;
import cdm.product.qualification.functions.Qualify_InterestRate_CrossCurrency_FixedFloat;
import cdm.product.qualification.functions.Qualify_InterestRate_Forward_Debt;
import cdm.product.qualification.functions.Qualify_InterestRate_Fra;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_Basis;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_Basis_OIS;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_FixedFixed;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_FixedFloat;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_FixedFloat_OIS;
import cdm.product.qualification.functions.Qualify_InterestRate_IRSwap_FixedFloat_ZeroCoupon;
import cdm.product.qualification.functions.Qualify_InterestRate_InflationSwap_Basis_YearOn_Year;
import cdm.product.qualification.functions.Qualify_InterestRate_InflationSwap_Basis_ZeroCoupon;
import cdm.product.qualification.functions.Qualify_InterestRate_InflationSwap_FixedFloat_YearOn_Year;
import cdm.product.qualification.functions.Qualify_InterestRate_InflationSwap_FixedFloat_ZeroCoupon;
import cdm.product.qualification.functions.Qualify_InterestRate_Option_DebtOption;
import cdm.product.qualification.functions.Qualify_InterestRate_Option_Swaption;
import cdm.product.qualification.functions.Qualify_SecuritiesFinance;
import cdm.product.template.EconomicTerms;
import cdm.product.template.validation.EconomicTermsTypeFormatValidator;
import cdm.product.template.validation.EconomicTermsValidator;
import cdm.product.template.validation.exists.EconomicTermsOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=EconomicTerms.class)
public class EconomicTermsMeta implements RosettaMetaData<EconomicTerms> {

	@Override
	public List<Validator<? super EconomicTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.EconomicTermsFpMLCd2628.class),
			factory.create(cdm.product.template.validation.datarule.EconomicTermsFpMLCd27.class),
			factory.create(cdm.product.template.validation.datarule.EconomicTermsFpMLCd30.class),
			factory.create(cdm.product.template.validation.datarule.EconomicTermsIndependentCalculationAgent.class),
			factory.create(cdm.product.template.validation.datarule.EconomicTermsAssetPayoutDividendTermsValidation.class),
			factory.create(cdm.product.template.validation.datarule.EconomicTermsExtendibleProvisionExerciseDetails.class)
		);
	}
	
	@Override
	public List<Function<? super EconomicTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Arrays.asList(
			factory.create(Qualify_CreditDefaultSwap_SingleName.class),
			factory.create(Qualify_CreditDefaultSwap_Index.class),
			factory.create(Qualify_CreditDefaultSwap_IndexTranche.class),
			factory.create(Qualify_CreditDefaultSwap_Loan.class),
			factory.create(Qualify_CreditDefaultSwap_Basket.class),
			factory.create(Qualify_CreditDefaultSwaption.class),
			factory.create(Qualify_EquitySwap_PriceReturnBasicPerformance_SingleName.class),
			factory.create(Qualify_EquitySwap_TotalReturnBasicPerformance_SingleName.class),
			factory.create(Qualify_EquitySwap_PriceReturnBasicPerformance_Index.class),
			factory.create(Qualify_EquitySwap_TotalReturnBasicPerformance_Index.class),
			factory.create(Qualify_EquitySwap_PriceReturnBasicPerformance_Basket.class),
			factory.create(Qualify_EquitySwap_TotalReturnBasicPerformance_Basket.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVariance_SingleName.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVariance_Index.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVariance_Basket.class),
			factory.create(Qualify_EquitySwap_ParameterReturnDispersion.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVolatility_SingleName.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVolatility_Index.class),
			factory.create(Qualify_EquitySwap_ParameterReturnVolatility_Basket.class),
			factory.create(Qualify_EquitySwap_ParameterReturnCorrelation_Basket.class),
			factory.create(Qualify_EquitySwap_ParameterReturnDividend_SingleName.class),
			factory.create(Qualify_EquitySwap_ParameterReturnDividend_Index.class),
			factory.create(Qualify_EquitySwap_ParameterReturnDividend_Basket.class),
			factory.create(Qualify_EquityOption_PriceReturnBasicPerformance_SingleName.class),
			factory.create(Qualify_EquityOption_PriceReturnBasicPerformance_Index.class),
			factory.create(Qualify_EquityOption_PriceReturnBasicPerformance_Basket.class),
			factory.create(Qualify_EquityOption_ParameterReturnVariance_SingleName.class),
			factory.create(Qualify_EquityOption_ParameterReturnVariance_Index.class),
			factory.create(Qualify_EquityOption_ParameterReturnVariance_Basket.class),
			factory.create(Qualify_EquityOption_ParameterReturnVolatility_SingleName.class),
			factory.create(Qualify_EquityOption_ParameterReturnVolatility_Index.class),
			factory.create(Qualify_EquityOption_ParameterReturnVolatility_Basket.class),
			factory.create(Qualify_EquityOption_ParameterReturnCorrelation_Basket.class),
			factory.create(Qualify_EquityOption_ParameterReturnDividend_SingleName.class),
			factory.create(Qualify_EquityOption_ParameterReturnDividend_Index.class),
			factory.create(Qualify_EquityOption_ParameterReturnDividend_Basket.class),
			factory.create(Qualify_InterestRate_IRSwap_FixedFloat.class),
			factory.create(Qualify_InterestRate_IRSwap_FixedFixed.class),
			factory.create(Qualify_InterestRate_IRSwap_Basis.class),
			factory.create(Qualify_InterestRate_IRSwap_FixedFloat_ZeroCoupon.class),
			factory.create(Qualify_InterestRate_IRSwap_FixedFloat_OIS.class),
			factory.create(Qualify_InterestRate_IRSwap_Basis_OIS.class),
			factory.create(Qualify_InterestRate_CrossCurrency_FixedFloat.class),
			factory.create(Qualify_InterestRate_CrossCurrency_Basis.class),
			factory.create(Qualify_InterestRate_CrossCurrency_FixedFixed.class),
			factory.create(Qualify_InterestRate_InflationSwap_FixedFloat_YearOn_Year.class),
			factory.create(Qualify_InterestRate_InflationSwap_FixedFloat_ZeroCoupon.class),
			factory.create(Qualify_InterestRate_InflationSwap_Basis_YearOn_Year.class),
			factory.create(Qualify_InterestRate_InflationSwap_Basis_ZeroCoupon.class),
			factory.create(Qualify_InterestRate_Fra.class),
			factory.create(Qualify_InterestRate_CapFloor.class),
			factory.create(Qualify_InterestRate_Option_Swaption.class),
			factory.create(Qualify_InterestRate_Option_DebtOption.class),
			factory.create(Qualify_InterestRate_Forward_Debt.class),
			factory.create(Qualify_ForeignExchange_Spot_Forward.class),
			factory.create(Qualify_ForeignExchange_Swap.class),
			factory.create(Qualify_ForeignExchange_NDF.class),
			factory.create(Qualify_ForeignExchange_NDS.class),
			factory.create(Qualify_ForeignExchange_ParameterReturnVariance.class),
			factory.create(Qualify_ForeignExchange_ParameterReturnVolatility.class),
			factory.create(Qualify_ForeignExchange_ParameterReturnCorrelation.class),
			factory.create(Qualify_ForeignExchange_VanillaOption.class),
			factory.create(Qualify_SecuritiesFinance.class),
			factory.create(Qualify_Commodity_Swap_FixedFloat.class),
			factory.create(Qualify_Commodity_Swap_Basis.class),
			factory.create(Qualify_Commodity_Option.class),
			factory.create(Qualify_Commodity_Swaption.class),
			factory.create(Qualify_Commodity_Forward.class)
		);
	}

	@Override
	public Validator<? super EconomicTerms> validator() {
		return new EconomicTermsValidator();
	}

	@Override
	public Validator<? super EconomicTerms> typeFormatValidator() {
		return new EconomicTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EconomicTerms, Set<String>> onlyExistsValidator() {
		return new EconomicTermsOnlyExistsValidator();
	}
}
