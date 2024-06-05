package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.CashPriceTypeEnum;
import cdm.observable.asset.PremiumExpression;
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
@RosettaDataRule("CashPricePremiumType")
@ImplementedBy(CashPricePremiumType.Default.class)
public interface CashPricePremiumType extends Validator<CashPrice> {
	
	String NAME = "CashPricePremiumType";
	String DEFINITION = "if premiumExpression exists then cashPriceType = CashPriceTypeEnum -> Premium";
	
	ValidationResult<CashPrice> validate(RosettaPath path, CashPrice cashPrice);
	
	class Default implements CashPricePremiumType {
	
		@Override
		public ValidationResult<CashPrice> validate(RosettaPath path, CashPrice cashPrice) {
			ComparisonResult result = executeDataRule(cashPrice);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashPrice", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CashPrice", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CashPrice cashPrice) {
			try {
				if (exists(MapperS.of(cashPrice).<PremiumExpression>map("getPremiumExpression", _cashPrice -> _cashPrice.getPremiumExpression())).getOrDefault(false)) {
					return areEqual(MapperS.of(cashPrice).<CashPriceTypeEnum>map("getCashPriceType", _cashPrice -> _cashPrice.getCashPriceType()), MapperS.of(CashPriceTypeEnum.PREMIUM), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CashPricePremiumType {
	
		@Override
		public ValidationResult<CashPrice> validate(RosettaPath path, CashPrice cashPrice) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CashPrice", path, DEFINITION);
		}
	}
}
