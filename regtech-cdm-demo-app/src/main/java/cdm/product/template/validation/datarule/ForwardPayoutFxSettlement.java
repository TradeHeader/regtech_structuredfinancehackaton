package cdm.product.template.validation.datarule;

import cdm.product.asset.ForeignExchange;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.Product;
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
@RosettaDataRule("ForwardPayoutFxSettlement")
@ImplementedBy(ForwardPayoutFxSettlement.Default.class)
public interface ForwardPayoutFxSettlement extends Validator<ForwardPayout> {
	
	String NAME = "ForwardPayoutFxSettlement";
	String DEFINITION = "if underlier -> foreignExchange exists then settlementTerms -> physicalSettlementTerms is absent";
	
	ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout);
	
	class Default implements ForwardPayoutFxSettlement {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			ComparisonResult result = executeDataRule(forwardPayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ForwardPayout forwardPayout) {
			try {
				if (exists(MapperS.of(forwardPayout).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())).getOrDefault(false)) {
					return notExists(MapperS.of(forwardPayout).<SettlementTerms>map("getSettlementTerms", payoutBase -> payoutBase.getSettlementTerms()).<PhysicalSettlementTerms>map("getPhysicalSettlementTerms", settlementTerms -> settlementTerms.getPhysicalSettlementTerms()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ForwardPayoutFxSettlement {
	
		@Override
		public ValidationResult<ForwardPayout> validate(RosettaPath path, ForwardPayout forwardPayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ForwardPayout", path, DEFINITION);
		}
	}
}
