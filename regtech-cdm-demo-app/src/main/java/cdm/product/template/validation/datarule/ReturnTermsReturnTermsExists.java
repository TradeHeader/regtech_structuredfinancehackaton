package cdm.product.template.validation.datarule;

import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.ReturnTypeEnum;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ReturnTermsReturnTermsExists")
@ImplementedBy(ReturnTermsReturnTermsExists.Default.class)
public interface ReturnTermsReturnTermsExists extends Validator<ReturnTerms> {
	
	String NAME = "ReturnTermsReturnTermsExists";
	String DEFINITION = "if priceReturnTerms -> returnType = ReturnTypeEnum -> Total then (priceReturnTerms, dividendReturnTerms) only exists else if priceReturnTerms -> returnType = ReturnTypeEnum -> Price then priceReturnTerms only exists else priceReturnTerms only exists or dividendReturnTerms only exists or varianceReturnTerms only exists or volatilityReturnTerms only exists or correlationReturnTerms only exists";
	
	ValidationResult<ReturnTerms> validate(RosettaPath path, ReturnTerms returnTerms);
	
	class Default implements ReturnTermsReturnTermsExists {
	
		@Override
		public ValidationResult<ReturnTerms> validate(RosettaPath path, ReturnTerms returnTerms) {
			ComparisonResult result = executeDataRule(returnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ReturnTerms returnTerms) {
			try {
				if (areEqual(MapperS.of(returnTerms).<PriceReturnTerms>map("getPriceReturnTerms", _returnTerms -> _returnTerms.getPriceReturnTerms()).<ReturnTypeEnum>map("getReturnType", priceReturnTerms -> priceReturnTerms.getReturnType()), MapperS.of(ReturnTypeEnum.TOTAL), CardinalityOperator.All).getOrDefault(false)) {
					return onlyExists(Arrays.asList(MapperS.of(returnTerms).<PriceReturnTerms>map("getPriceReturnTerms", _returnTerms -> _returnTerms.getPriceReturnTerms()), MapperS.of(returnTerms).<DividendReturnTerms>map("getDividendReturnTerms", _returnTerms -> _returnTerms.getDividendReturnTerms())));
				}
				if (areEqual(MapperS.of(returnTerms).<PriceReturnTerms>map("getPriceReturnTerms", _returnTerms -> _returnTerms.getPriceReturnTerms()).<ReturnTypeEnum>map("getReturnType", priceReturnTerms -> priceReturnTerms.getReturnType()), MapperS.of(ReturnTypeEnum.PRICE), CardinalityOperator.All).getOrDefault(false)) {
					return onlyExists(Arrays.asList(MapperS.of(returnTerms).<PriceReturnTerms>map("getPriceReturnTerms", _returnTerms -> _returnTerms.getPriceReturnTerms())));
				}
				return onlyExists(Arrays.asList(MapperS.of(returnTerms).<PriceReturnTerms>map("getPriceReturnTerms", _returnTerms -> _returnTerms.getPriceReturnTerms()))).or(onlyExists(Arrays.asList(MapperS.of(returnTerms).<DividendReturnTerms>map("getDividendReturnTerms", _returnTerms -> _returnTerms.getDividendReturnTerms())))).or(onlyExists(Arrays.asList(MapperS.of(returnTerms).<VarianceReturnTerms>map("getVarianceReturnTerms", _returnTerms -> _returnTerms.getVarianceReturnTerms())))).or(onlyExists(Arrays.asList(MapperS.of(returnTerms).<VolatilityReturnTerms>map("getVolatilityReturnTerms", _returnTerms -> _returnTerms.getVolatilityReturnTerms())))).or(onlyExists(Arrays.asList(MapperS.of(returnTerms).<CorrelationReturnTerms>map("getCorrelationReturnTerms", _returnTerms -> _returnTerms.getCorrelationReturnTerms()))));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ReturnTermsReturnTermsExists {
	
		@Override
		public ValidationResult<ReturnTerms> validate(RosettaPath path, ReturnTerms returnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReturnTerms", path, DEFINITION);
		}
	}
}
