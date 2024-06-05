package cdm.product.common.settlement.validation.datarule;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
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
@RosettaDataRule("PriceQuantityActualSettlement")
@ImplementedBy(PriceQuantityActualSettlement.Default.class)
public interface PriceQuantityActualSettlement extends Validator<PriceQuantity> {
	
	String NAME = "PriceQuantityActualSettlement";
	String DEFINITION = "if buyerSeller exists then settlementTerms exists";
	
	ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity);
	
	class Default implements PriceQuantityActualSettlement {
	
		@Override
		public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity) {
			ComparisonResult result = executeDataRule(priceQuantity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceQuantity priceQuantity) {
			try {
				if (exists(MapperS.of(priceQuantity).<BuyerSeller>map("getBuyerSeller", _priceQuantity -> _priceQuantity.getBuyerSeller())).getOrDefault(false)) {
					return exists(MapperS.of(priceQuantity).<SettlementTerms>map("getSettlementTerms", _priceQuantity -> _priceQuantity.getSettlementTerms()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceQuantityActualSettlement {
	
		@Override
		public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity priceQuantity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceQuantity", path, DEFINITION);
		}
	}
}
