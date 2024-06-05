package cdm.product.qualification.functions;

import cdm.product.asset.ForeignExchange;
import cdm.product.template.AmericanExercise;
import cdm.product.template.AveragingCalculation;
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

@ImplementedBy(Qualify_ForeignExchange_VanillaOption.Qualify_ForeignExchange_VanillaOptionDefault.class)
public abstract class Qualify_ForeignExchange_VanillaOption implements RosettaFunction,IQualifyFunctionExtension<EconomicTerms> {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_AssetClass_ForeignExchange qualify_AssetClass_ForeignExchange;

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

	public static class Qualify_ForeignExchange_VanillaOptionDefault extends Qualify_ForeignExchange_VanillaOption {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_AssetClass_ForeignExchange.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()))).and(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<Product>map("getUnderlier", optionPayout -> optionPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())))).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionExercise>map("getExerciseTerms", optionPayout -> optionPayout.getExerciseTerms()).<OptionStyle>map("getOptionStyle", optionExercise -> optionExercise.getOptionStyle()).<AmericanExercise>map("getAmericanExercise", optionStyle -> optionStyle.getAmericanExercise())).or(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionExercise>map("getExerciseTerms", optionPayout -> optionPayout.getExerciseTerms()).<OptionStyle>map("getOptionStyle", optionExercise -> optionExercise.getOptionStyle()).<EuropeanExercise>map("getEuropeanExercise", optionStyle -> optionStyle.getEuropeanExercise())))).and(notExists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionFeature>map("getFeature", optionPayout -> optionPayout.getFeature())).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<OptionFeature>map("getFeature", optionPayout -> optionPayout.getFeature()).<AveragingCalculation>map("getAveragingFeature", optionFeature -> optionFeature.getAveragingFeature()))))).get();
			
			return is_product;
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
