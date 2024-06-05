package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.SettlementTypeEnum;
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
@RosettaDataRule("SettlementTermsOptionSettlementChoice")
@ImplementedBy(SettlementTermsOptionSettlementChoice.Default.class)
public interface SettlementTermsOptionSettlementChoice extends Validator<SettlementTerms> {
	
	String NAME = "SettlementTermsOptionSettlementChoice";
	String DEFINITION = "if cashSettlementTerms exists and physicalSettlementTerms exists then settlementType = SettlementTypeEnum -> Election or settlementType = SettlementTypeEnum -> CashOrPhysical";
	
	ValidationResult<SettlementTerms> validate(RosettaPath path, SettlementTerms settlementTerms);
	
	class Default implements SettlementTermsOptionSettlementChoice {
	
		@Override
		public ValidationResult<SettlementTerms> validate(RosettaPath path, SettlementTerms settlementTerms) {
			ComparisonResult result = executeDataRule(settlementTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SettlementTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SettlementTerms settlementTerms) {
			try {
				if (exists(MapperS.of(settlementTerms).<CashSettlementTerms>mapC("getCashSettlementTerms", _settlementTerms -> _settlementTerms.getCashSettlementTerms())).and(exists(MapperS.of(settlementTerms).<PhysicalSettlementTerms>map("getPhysicalSettlementTerms", _settlementTerms -> _settlementTerms.getPhysicalSettlementTerms()))).getOrDefault(false)) {
					return areEqual(MapperS.of(settlementTerms).<SettlementTypeEnum>map("getSettlementType", settlementBase -> settlementBase.getSettlementType()), MapperS.of(SettlementTypeEnum.ELECTION), CardinalityOperator.All).or(areEqual(MapperS.of(settlementTerms).<SettlementTypeEnum>map("getSettlementType", settlementBase -> settlementBase.getSettlementType()), MapperS.of(SettlementTypeEnum.CASH_OR_PHYSICAL), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SettlementTermsOptionSettlementChoice {
	
		@Override
		public ValidationResult<SettlementTerms> validate(RosettaPath path, SettlementTerms settlementTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SettlementTerms", path, DEFINITION);
		}
	}
}
