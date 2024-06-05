package cdm.product.qualification.functions;

import cdm.product.template.AmericanExercise;
import cdm.product.template.AveragingCalculation;
import cdm.product.template.Basket;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EconomicTerms;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionFeature;
import cdm.product.template.OptionPayout;
import cdm.product.template.OptionStyle;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_EquityOption_PriceReturnBasicPerformance_Basket.Qualify_EquityOption_PriceReturnBasicPerformance_BasketDefault.class)
public abstract class Qualify_EquityOption_PriceReturnBasicPerformance_Basket implements RosettaFunction,IQualifyFunctionExtension<EconomicTerms> {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_AssetClass_Equity qualify_AssetClass_Equity;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	@Override
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	public static class Qualify_EquityOption_PriceReturnBasicPerformance_BasketDefault extends Qualify_EquityOption_PriceReturnBasicPerformance_Basket {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_AssetClass_Equity.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout())))).and(exists(MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).get()).<Product>map("getUnderlier", optionPayout -> optionPayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()))).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionExercise>map("getExerciseTerms", optionPayout -> optionPayout.getExerciseTerms()).<OptionStyle>map("getOptionStyle", optionExercise -> optionExercise.getOptionStyle()).<AmericanExercise>map("getAmericanExercise", optionStyle -> optionStyle.getAmericanExercise())).or(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionExercise>map("getExerciseTerms", optionPayout -> optionPayout.getExerciseTerms()).<OptionStyle>map("getOptionStyle", optionExercise -> optionExercise.getOptionStyle()).<EuropeanExercise>map("getEuropeanExercise", optionStyle -> optionStyle.getEuropeanExercise()))).or(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionExercise>map("getExerciseTerms", optionPayout -> optionPayout.getExerciseTerms()).<OptionStyle>map("getOptionStyle", optionExercise -> optionExercise.getOptionStyle()).<BermudaExercise>map("getBermudaExercise", optionStyle -> optionStyle.getBermudaExercise())))).and(notExists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionFeature>map("getFeature", optionPayout -> optionPayout.getFeature())).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionFeature>map("getFeature", optionPayout -> optionPayout.getFeature()).<AveragingCalculation>map("getAveragingFeature", optionFeature -> optionFeature.getAveragingFeature()))))).get();
			
			return is_product;
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
